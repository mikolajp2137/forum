document.addEventListener('DOMContentLoaded', function () {
    const apiUrl = 'http://localhost:8080/api/threads';
    const threadListDiv = document.getElementById('threadList');

    fetch(apiUrl)
        .then(response => response.json())
        .then(data => displayThreads(data))
        .catch(error => console.error('Error fetching data:', error));

    function displayThreads(threads) {
        threads.forEach(thread => {
            const threadDiv = document.createElement('div');
            threadDiv.classList.add('thread');

            const titleLink = document.createElement('div');
            titleLink.classList.add('threadLink');
            titleLink.textContent = thread.title;
            titleLink.addEventListener('click', () => redirectToThreadPage(thread.threadId));

            const contentsDiv = document.createElement('div');
            contentsDiv.classList.add('threadContents');
            contentsDiv.textContent = thread.contents;

            threadDiv.appendChild(titleLink);
            threadDiv.appendChild(contentsDiv);

            threadListDiv.appendChild(threadDiv);
        });
    }

    function redirectToThreadPage(threadId) {
        window.location.href = `thread.html?id=${threadId}`;
    }
});
