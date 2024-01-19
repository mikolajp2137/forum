if (getUsernameFromSessionStorage() === null) {
    console.error('User not logged in.');
    window.location.href = 'index.html';
}

window.addThread = function () {
    const title = document.getElementById('title').value;
    const contents = document.getElementById('contents').value;

    const threadData = {
        creatorId: getUserIdFromSessionStorage(),
        contents: contents,
        categoryId: 1,
        title: title
    };

    fetch('http://localhost:8080/api/threads', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(threadData)
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

function getUserIdFromSessionStorage() {
    return sessionStorage.getItem('userId');
}

function getUsernameFromSessionStorage() {
    return sessionStorage.getItem('username');
}
