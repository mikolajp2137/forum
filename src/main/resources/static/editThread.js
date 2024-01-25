document.addEventListener('DOMContentLoaded', function () {
    const urlParams = new URLSearchParams(window.location.search);
    const threadId = urlParams.get('id');

    // Fetch thread details for editing
    fetch(`http://localhost:8080/api/threads/${threadId}`)
        .then(response => response.json())
        .then(data => {
            // Populate the form fields with thread details
            document.getElementById('title').value = data.title;
            document.getElementById('contents').value = data.contents;

            // Check if the logged-in user created this thread
            const loggedInUserId = getUserIdFromSessionStorage();
            if (loggedInUserId !== data.creatorId.toString()) {
                console.log('User does not have permission to edit this thread. Redirecting to home.');
                window.location.href = 'index.html';
            }
        })
        .catch(error => console.error('Error fetching thread data for editing:', error));
});

function getUserIdFromSessionStorage() {
    return sessionStorage.getItem('userId');
}

function editThread() {
    const urlParams = new URLSearchParams(window.location.search);
    const threadId = urlParams.get('id');

    const updatedTitle = document.getElementById('title').value;
    const updatedContents = document.getElementById('contents').value;

    fetch(`http://localhost:8080/api/threads/${threadId}`)
        .then(response => response.json())
        .then(originalData => {
            const loggedInUserId = getUserIdFromSessionStorage();
            if (loggedInUserId !== originalData.creatorId.toString()) {
                console.log('User does not have permission to edit this thread. Redirecting to home.');
                window.location.href = 'index.html';
                return;
            }

            const editedThreadData = {
                creatorId: originalData.creatorId,
                contents: updatedContents,
                categoryId: originalData.categoryId,
                attachmentId: originalData.attachmentId,
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
}
