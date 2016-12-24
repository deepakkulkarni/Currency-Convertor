<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>User Registration</title>
    <link rel="stylesheet" type="text/css" href="css/main.css" />
    <link rel="stylesheet" href="css/jquery-ui.css">
    <script src="js/jquery-3.1.1.js"></script>
    <script src="js/jquery-ui.js"></script>
    <script src="js/validation/registration-validation.js"></script>
    <script src="js/jquery.validate.min.js"></script>
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
            <input type="text" placeholder="Date of birth" name="dob" id="birthdate" readonly required>
            <input type="text" placeholder="Building/Street" name="address">
            <input type="text" placeholder="Pin" name="pin">
            <input type="text" placeholder="City" name="city">
            <input type="text" placeholder="Country" name="country" id="country" list="allCountries">
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
        var currentDate = new Date();
        $("#birthdate").datepicker({
            changeMonth: true,
            changeYear: true,
            yearRange: "-100:+0",
            maxDate: 0,
            dateFormat: 'dd-M-yy'
          });
          $("#birthdate").datepicker("setDate", currentDate);
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

    $('#registrationForm').submit(function(){
            var selectedCountry = $('#country').val();
            var obj = $("#allCountries").find("option[value='"+selectedCountry+"']")
             if(obj !=null && obj.length<=0){
                 alert("Please enter a valid country.");
                 $('#country').val("");
                 $('#country').focus();
             }
    });

    </script>
</body>
</html>