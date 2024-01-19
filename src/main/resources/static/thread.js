document.addEventListener('DOMContentLoaded', function () {
    const urlParams = new URLSearchParams(window.location.search);
    const threadId = urlParams.get('id');

    if (threadId) {
        const threadUrl = `http://localhost:8080/api/threads/${threadId}`;
        const userUrl = 'http://localhost:8080/api/users/';

        let threadData;

        fetch(threadUrl)
            .then(response => response.json())
            .then(thread => {
                threadData = thread;

                return fetch(userUrl + thread.creatorId);
            })
            .then(response => response.json())
            .then(creator => {
                displayThreadContent(threadData, creator.username);
            })
            .catch(error => console.error('Error fetching thread data:', error));

        function displayThreadContent(thread, creatorUsername) {
            console.log(thread);
            const threadContentDiv = document.getElementById('threadContent');

            const titleDiv = document.createElement('div');
            titleDiv.classList.add('threadTitle');
            titleDiv.innerHTML = `<h2>${thread.title} by ${creatorUsername}</h2>`;

            const contentsDiv = document.createElement('div');
            contentsDiv.classList.add('threadContents');
            contentsDiv.textContent = thread.contents;

            threadContentDiv.appendChild(titleDiv);
            threadContentDiv.appendChild(contentsDiv);
        }
    } else {
        console.error('Thread ID not provided in the URL');
    }
});
