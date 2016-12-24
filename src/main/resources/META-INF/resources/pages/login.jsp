<html>
<head>
    <link rel="stylesheet" type="text/css" href="css/main.css"/>
    <script src="js/jquery-3.1.1.js"></script>
    <script src="js/validation/login-validation.js"></script>
    <script src="js/jquery.validate.min.js"></script>
    <title>Login form</title>
</head>
<body>
    <div id="login" class="login">
        <span id="error">${authenticationError}</span>
        <form action="authenticate" method="post" id="loginForm">
            <input type="text" placeholder="Username" name="username">
            <input type="password" placeholder="Password" name="password">
            <input type="submit" value="Login">
            <input type="button" value="Register" id="register">
        </form>
    </div>

    <script>
        $("#register").click(function () {
            window.location.href = '/currency-convertor/register';
        });
    </script>
</body>
</html>