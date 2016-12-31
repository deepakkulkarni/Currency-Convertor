<html>
<head>
    <title>Login form</title>
    <jsp:include page="common/header.jsp"/>
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
</body>
</html>