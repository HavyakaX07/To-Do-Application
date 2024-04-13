<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login to Your TO-DO App</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f0f0;
            text-align: center;
            margin-top: 100px;
        }
        h1 {
            color: #333;
        }
        .container {
            max-width: 400px;
            margin: auto;
        }
        input[type="text"], input[type="password"] {
            width: 100%;
            padding: 10px;
            margin: 10px 0;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        input[type="submit"] {
            background-color: #4CAF50;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        input[type="submit"]:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
    <div class="container">
        <span id="loginPageError" style="color: red;">${error}</span>
        <h1>Login to Your TO-DO App</h1>
        <form id="loginUserForm" onsubmit="login(event)">
            <input type="text" id="userName" placeholder="Username" required>
            <br>
            <input type="password" id="password" placeholder="Password" required>
            <br>
            <input type="submit" value="Login">
        </form>
        <p>Don't have an account? <a href="/register">Register here</a></p>
    </div>

    <script>
        function login(event) {
            event.preventDefault(); // Prevent default form submission
            
            var username = document.getElementById('userName').value;
            var password = document.getElementById('password').value;

            var data = {
                "userName": username,
                "password": password
            };

            // Send POST request with JSON data
            fetch('/loginUser', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(data)
            })
            .then(response => response.json())
            .then(data => {
                if(data.loginStatus === "success") {
                    // Send request to the manageTodos endpoint
                    console.log("Redirecting to todos home page");
                    console.log(data);
                    fetch('/manageTodos', {
                        method: 'POST',
                        headers: {
                            'Content-Type': 'application/json'
                        },
                        body: JSON.stringify({ "userName": username })
                    })
                    .then(response => {
                        // Handle response accordingly
                        // For demonstration, you can console.log the response
                        console.log(response);
                    })
                    .catch(error => {
                        console.error('Error:', error);
                    });
                } else {
                    document.getElementById('loginPageError').innerText = data.loginPageError;
                }
            })
            .catch(error => {
                console.error('Error:', error);
            });
        }
    </script>
</body>
</html>
