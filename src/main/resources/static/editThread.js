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

document.addEventListener('DOMContentLoaded', function () {
    const urlParams = new URLSearchParams(window.location.search);
    const threadId = urlParams.get('id');

    getUsername()
        .then(username => getUserId(username))
        .then(loggedInUserId => {
            fetch(`http://localhost:8080/api/threads/${threadId}`)
                .then(response => response.json())
                .then(data => {
                    document.getElementById('title').value = data.title;
                    document.getElementById('contents').value = data.contents;

                    if (loggedInUserId !== data.creatorId) {
                        console.log('User does not have permission to edit this thread. Redirecting to home.');
                        window.location.href = 'index.html';
                    }
                })
                .catch(error => console.error('Error fetching thread data for editing:', error));
        })
        .catch(error => console.error('Error getting user data:', error));
});

function editThread() {
    const urlParams = new URLSearchParams(window.location.search);
    const threadId = urlParams.get('id');

    const updatedTitle = document.getElementById('title').value;
    const updatedContents = document.getElementById('contents').value;

    getUsername()
        .then(username => getUserId(username))
        .then(loggedInUserId => {
            fetch(`http://localhost:8080/api/threads/${threadId}`)
                .then(response => response.json())
                .then(originalData => {
                    if (loggedInUserId !== originalData.creatorId) {
                        console.log('User does not have permission to edit this thread. Redirecting to home.');
                        window.location.href = 'index.html';
                        return;
                    }

                    const editedThreadData = {
                        creatorId: originalData.creatorId,
                        contents: updatedContents,
                        categoryId: originalData.categoryId,
                        title: updatedTitle,
                        locked: originalData.locked
                    };

                    return fetch(`http://localhost:8080/api/threads/${threadId}`, {
                        method: 'PUT',
                        headers: {
                            'Content-Type': 'application/json',
                        },
                        body: JSON.stringify(editedThreadData),
                    });
                })
                .then(response => {
                    if (response.status === 200) {
                        console.log('Thread edited successfully');
                        window.location.href = 'index.html';
                    } else {
                        console.error('Error editing thread. HTTP status:', response.status);
                    }
                })
                .catch(error => {
                    console.error('Error editing thread:', error);
                });
        })
        .catch(error => console.error('Error getting user data:', error));
}
