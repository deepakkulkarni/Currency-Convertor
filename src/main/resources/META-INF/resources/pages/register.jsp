<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>User Registration</title>
    <jsp:include page="common/header.jsp"/>
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
</body>
</html>