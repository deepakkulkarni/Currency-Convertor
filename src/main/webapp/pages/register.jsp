<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>User Registration</title>
    <link rel="stylesheet" type="text/css" href="css/main.css" />
    <link rel="stylesheet" href="css/jquery-ui.css">
    <script src="js/jquery-3.1.1.js"></script>
    <script src="js/jquery-ui.js"></script>
    <script src="js/validation/registration-validation.js"></script>
    <script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.13.0/jquery.validate.min.js"></script>
</head>
<body>
    <div class="register">
        <form action="register" id="registrationForm" method="post">
        <span id="error">${error}</span>
            <input type="text" placeholder="First Name/Last Name" name="name">
            <input type="text" placeholder="Username" id="username" name="username">
            <input type="password" id="password" placeholder="Password" name="password">
            <input type="password" placeholder="Confirm Password" name="confirmPassword">
            <input type="text" placeholder="Email" name="email">
            <input type="text" placeholder="Date of birth" name="dob" id="datepicker" required>
            <input type="text" placeholder="Building/Street" name="address">
            <input type="text" placeholder="Pin" name="pin">
            <input type="text" placeholder="City" name="city">
            <input type="text" placeholder="Country" name="country" list="allCountries">
            <datalist id="allCountries">
                  <c:forEach var="country" items="${countries}">
                      <option value="${country}">${country}</option>
                  </c:forEach>
            </datalist>
            <input type="submit" value="Register">
            <input type="button" value="Back" id="back">
        </form>
    </div>
    <script>
    $(function() {
        $("#datepicker").datepicker({
            changeMonth: true,
            changeYear: true,
            yearRange: "-100:+0",
            maxDate: 0,
            dateFormat: 'dd-M-yy'
          });
    });

    $("#back").click(function () {
        window.location.href = '/currency-convertor/';
    });

    $('#username').focusout(function(){
        var username = $('#username').val();
        $.post("check-username",{username: username}).done(function(result) {
            if(result == true){
                alert("Username already taken. Please try with some other username.");
                $('#username').val("");
                $('#username').focus();
             }
        });
    });

    </script>
</body>
</html>