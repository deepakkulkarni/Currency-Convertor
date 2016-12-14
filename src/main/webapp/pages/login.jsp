<html>
<head>
    <link rel="stylesheet" type="text/css" href="css/main.css"/>
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <title>Login form</title>
</head>
<body>
    <div id="login" class="login">
        <form action="authenticate" method="post">
            <input type="text" placeholder="Username" name="username" required>
            <input type="password" placeholder="Password" name="password" required>
            <input type="submit" value="Login">
            <input type="button" value="Register" id="register">
        </form>
    </div>

    <script>
        $("#register").click(function () {
            window.location.href = 'register';
        });
    </script>
</body>
</html>