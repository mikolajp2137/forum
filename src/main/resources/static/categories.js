document.addEventListener('DOMContentLoaded', function () {
    fetch('http://localhost:8080/api/categories')
        .then(response => response.json())
        .then(categories => {
            const categoryDropdown = document.getElementById('categoryDropdown');

            categories.forEach(category => {
                const option = document.createElement('option');
                option.value = category.categoryId;
                option.textContent = category.categoryName;
                categoryDropdown.appendChild(option);
            });
        })
        .catch(error => console.error('Error fetching categories:', error));
});

function filterThreads() {
    const selectedCategoryId = document.getElementById('categoryDropdown').value;

    fetch(`http://localhost:8080/api/threads/byCategory/${selectedCategoryId}`)
        .then(response => response.json())
        .then(results => displayFilteredResults(results))
        .catch(error => console.error('Error fetching filtered results:', error));
}

function displayFilteredResults(results) {
    const filteredResultsContainer = document.getElementById('filteredResults');
    filteredResultsContainer.innerHTML = '';

    if (results.length === 0) {
        filteredResultsContainer.innerHTML = '<p>No results found for the selected category.</p>';
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

        filteredResultsContainer.appendChild(resultList);
    }
}
