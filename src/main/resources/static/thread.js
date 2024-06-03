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
                    displayThreadContent(data);
                    const threadCreatorId = data.creatorId;
                })
                .catch(error => console.error('Error fetching thread data:', error));

            fetch(`http://localhost:8080/api/replies/${threadId}`)
                .then(response => response.json())
                .then(data => displayReplies(data))
                .catch(error => console.error('Error fetching replies:', error));

            const replyInput = document.getElementById('replyInput');
            const replyButton = document.getElementById('replyButton');

            replyButton.addEventListener('click', function () {
                if (loggedInUserId !== null) {
                    const replyContents = replyInput.value.trim();

                    if (replyContents !== '') {
                        const replyData = {
                            creatorId: Number(loggedInUserId),
                            threadId: Number(threadId),
                            contents: replyContents
                        };

                        fetch('http://localhost:8080/api/replies', {
                            method: 'POST',
                            headers: {
                                'Content-Type': 'application/json'
                            },
                            body: JSON.stringify(replyData)
                        })
                            .then(response => {
                                if (response.status === 204) {
                                    console.log('Reply added successfully');
                                    location.reload();
                                } else if (!response.ok) {
                                    throw new Error(`HTTP error! Status: ${response.status}`);
                                }
                            })
                            .catch(error => console.error('Error adding reply:', error));
                    } else {
                        console.log('Reply contents cannot be empty.');
                    }
                } else {
                    alert('Please log in to reply.');
                }
            });
        })
        .catch(error => console.error('Error getting user data:', error));
});

function displayThreadContent(thread) {
    const threadContentDiv = document.getElementById('threadContent');

    const titleDiv = document.createElement('div');
    titleDiv.classList.add('threadTitle');
    titleDiv.textContent = thread.title;

    const contentsDiv = document.createElement('div');
    contentsDiv.classList.add('threadContents');
    contentsDiv.textContent = thread.contents;

    threadContentDiv.appendChild(titleDiv);
    threadContentDiv.appendChild(contentsDiv);
}

function displayReplies(replies) {
    const repliesListDiv = document.getElementById('repliesList');
    replies.shift();

    replies.forEach(reply => {
        fetch(`http://localhost:8080/api/users/${reply.creatorId}`)
            .then(response => response.json())
            .then(userData => {
                const replyDiv = document.createElement('div');
                replyDiv.classList.add('thread');

                const replyContentDiv = document.createElement('div');
                replyContentDiv.classList.add('threadContents');
                replyContentDiv.textContent = `${userData.username}: ${reply.contents}`;

                replyDiv.appendChild(replyContentDiv);
                repliesListDiv.appendChild(replyDiv);
            })
            .catch(error => console.error('Error fetching user data for reply:', error));
    });
}

function deleteThread() {
    const urlParams = new URLSearchParams(window.location.search);
    const threadId = urlParams.get('id');
    const confirmation = confirm('Are you sure you want to delete this thread?');

    if (confirmation) {
        fetch(`http://localhost:8080/api/threads/${threadId}`, {
            method: 'DELETE',
        })
            .then(response => {
                if (response.status === 204) {
                    console.log('Thread deleted successfully');
                    window.location.href = 'index.html';
                } else {
                    console.error('Error deleting thread. HTTP status:', response.status);
                }
            })
            .catch(error => console.error('Error deleting thread:', error));
    }
}

function editThread() {
    const urlParams = new URLSearchParams(window.location.search);
    const id = urlParams.get('id');
    window.location.href = `http://localhost:8080/editThread.html?id=${id}`;
}
