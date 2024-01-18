document.addEventListener('DOMContentLoaded', function () {
    const addThreadFormContainer = document.getElementById('addThreadFormContainer');

    const userId = sessionStorage.getItem('userId');
    const username = sessionStorage.getItem('username');

    if (!userId || !username) {
        window.location.href = 'index.html';
    } else {
        displayAddThreadForm();
    }

    function displayAddThreadForm() {
        const form = document.createElement('form');
        form.id = 'addThreadForm';

        const titleLabel = document.createElement('label');
        titleLabel.textContent = 'Title:';
        const titleInput = document.createElement('input');
        titleInput.type = 'text';
        titleInput.name = 'title';
        titleLabel.appendChild(titleInput);

        const contentsLabel = document.createElement('label');
        contentsLabel.textContent = 'Contents:';
        const contentsInput = document.createElement('textarea');
        contentsInput.name = 'contents';
        contentsLabel.appendChild(contentsInput);

        const addButton = document.createElement('button');
        addButton.type = 'button';
        addButton.textContent = 'Add Thread';
        addButton.addEventListener('click', addThread);

        form.appendChild(titleLabel);
        form.appendChild(document.createElement('br'));
        form.appendChild(contentsLabel);
        form.appendChild(document.createElement('br'));
        form.appendChild(addButton);

        addThreadFormContainer.appendChild(form);
    }

    function addThread() {
        const title = document.getElementsByName('title')[0].value;
        const contents = document.getElementsByName('contents')[0].value;

        const threadData = {
            creatorId: userId,
            contents: contents,
            categoryId: 1,
            title: title
        };

        // Make a POST request to add the thread
        fetch('http://localhost:8080/api/threads', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(threadData)
        })
            .then(response => {
                if (response.status === 204) {
                    console.log('Thread added successfully.');
                    window.location.href = 'index.html';
                } else if (response.ok) {
                    return response.json();
                } else {
                    throw new Error(`HTTP error! Status: ${response.status}`);
                }
            })
            .then(data => {
                if (data) {
                    window.location.href = `thread.html?id=${data.threadId}`;
                }
            })
            .catch(error => {
                console.error('Error adding thread:', error);
            });
    }

});
