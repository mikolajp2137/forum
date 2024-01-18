const apiUrl = 'http://localhost:8080/api/users?name=';

function login() {
    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;

    fetch(apiUrl + username)
        .then(response => response.json())
        .then(data => {
            if (data.length > 0 && data[0].password === password) {
                saveToSessionStorage(data[0].id, username);
                window.location.href = 'index.html'; // Redirect to home page
            } else {
                alert('Incorrect username or password. Please try again.');
            }
        })
        .catch(error => console.error('Error checking credentials:', error));
}

function saveToSessionStorage(userId, username) {
    sessionStorage.setItem('userId', userId);
    sessionStorage.setItem('username', username);
}
