document.addEventListener('DOMContentLoaded', function () {
    const urlParams = new URLSearchParams(window.location.search);
    const threadId = urlParams.get('id');

    if (threadId) {
        const apiUrl = `http://localhost:8080/api/threads/${threadId}`;

        fetch(apiUrl)
            .then(response => response.json())
            .then(data => displayThreadContent(data))
            .catch(error => console.error('Error fetching thread data:', error));

        function displayThreadContent(thread) {
            console.log(thread)
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
    } else {
        console.error('Thread ID not provided in the URL');
    }
});
