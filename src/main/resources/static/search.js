function searchThreads() {
    const searchTitle = document.getElementById('searchTitle').value;

    fetch(`http://localhost:8080/api/threads?title=${encodeURIComponent(searchTitle)}`)
        .then(response => response.json())
        .then(results => displaySearchResults(results))
        .catch(error => console.error('Error fetching search results:', error));
}

function displaySearchResults(results) {
    const searchResultsContainer = document.getElementById('searchResults');
    searchResultsContainer.innerHTML = '';

    if (results.length === 0) {
        searchResultsContainer.innerHTML = '<p>No results found.</p>';
    } else {
        const resultList = document.createElement('ul');
        resultList.classList.add('list-group');
        resultList.style.marginTop = '50px';

        results.forEach(thread => {
            const resultItem = document.createElement('li');
            resultItem.classList.add('list-group-item');

            const link = document.createElement('a');
            link.href = `thread.html?id=${thread.threadId}`;
            link.textContent = thread.title;

            const contentsPreview = document.createElement('div');
            contentsPreview.classList.add('contents-preview');
            contentsPreview.textContent = thread.contents.length > 100 ?
                thread.contents.substring(0, 100) + '...' :
                thread.contents;

            resultItem.appendChild(link);
            resultItem.appendChild(contentsPreview);
            resultList.appendChild(resultItem);
        });

        searchResultsContainer.appendChild(resultList);
    }
}