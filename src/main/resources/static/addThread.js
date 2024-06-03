function getUsername() {
    const apiUrl = 'http://localhost:8080/username';

    return fetch(apiUrl)
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.text();
        })
        .catch(error => {
            console.error('Error fetching username:', error);
            throw error;
        });
}

function getUserId(username) {
    const apiUrl = 'http://localhost:8080/api/users?name=';

    return fetch(apiUrl + username)
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data => {
            const userId = data[0].id;
            return userId;
        })
        .catch(error => {
            console.error('Error fetching user data:', error);
            throw error;
        });
}

window.addThread = function () {
    const title = document.getElementById('title').value;
    const contents = document.getElementById('contents').value;
    const categoryId = document.getElementById('category').value;

    getUsername()
        .then(username => getUserId(username))
        .then(userId => {
            const threadData = {
                creatorId: userId,
                contents: contents,
                categoryId: categoryId,
                title: title
            };

            return fetch('http://localhost:8080/api/threads', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(threadData)
            });
        })
        .then(response => {
            if (response.status === 204) {
                console.log('Thread added successfully');
                window.location.href = 'index.html';
            } else if (!response.ok) {
                throw new Error(`HTTP error! Status: ${response.status}`);
            } else {
                return response.json();
            }
        })
        .then(data => {
            if (data) {
                console.log('Thread added successfully:', data);
                window.location.href = `thread.html?id=${data.threadId}`;
            }
        })
        .catch(error => {
            console.error('Error adding thread:', error);
        });
};
