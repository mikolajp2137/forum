window.registerUser = function () {
    const username = document.getElementById('username').value;
    const email = document.getElementById('email').value;
    const password = document.getElementById('password').value;

    const userData = {
        username: username,
        email: email,
        password: password
    };

    fetch('http://localhost:8080/api/users', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(userData)
    })
        .then(response => {
            if (response.status === 204) {
                console.log('User registered successfully');
                window.location.href = 'login.html';
            } else if (!response.ok) {
                throw new Error(`HTTP error! Status: ${response.status}`);
            } else {
                return response.json();
            }
        })
        .then(data => {
            if (data) {
                console.log('Response data:', data);
            }
        })
        .catch(error => {
            console.error('Error registering user:', error);
        });
};
