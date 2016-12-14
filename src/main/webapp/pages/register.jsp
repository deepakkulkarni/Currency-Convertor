<html>
<head>
    <title>User Registration</title>
    <link rel="stylesheet" type="text/css" href="css/main.css" />
    <link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
</head>
<body>
    <div class="register">
        <form action="#" method="post">
            <input type="text" placeholder="Name" name="name" required>
            <input type="text" placeholder="Username" name="username" required>
            <input type="password" placeholder="Password" name="password" required>
            <input type="text" placeholder="Email" name="email" required>
            <input type="text" id="datepicker" placeholder="Date of birth" name="dob">
            <input type="text" placeholder="Building/Street" name="address" required>
            <input type="text" placeholder="Pin" name="pin" required>
            <input type="text" placeholder="City" name="city" required>
            <select name="country" required>
                  <option value="India">India</option>
                  <option value="Germany" selected>Germany</option>
                  <option value="USA">USA</option>
            </select>
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
        window.location.href = '';
    });
    </script>
</body>
</html>