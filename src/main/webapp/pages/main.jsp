<html>
<head>
    <title>Main Page</title>
    <link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <link rel="stylesheet" type="text/css" href="css/main.css"/>
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
</head>
<body>
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
  </script>
  <div class="user-info">
    <span>Welcome, Deepak Kulkarni <br> <a href="#">Logout</a></span>
  </div>
    <div id="convert" class="main-page">
        <div id="inner">
            <form action="#" method="post">
                <input type="text" id="datepicker" placeholder="Date of exchange" style="font-weight:bold; text-align=center">
                <select name="fromCurrency" required>
                  <option value="value1">EUR</option>
                  <option value="value2" selected>USD</option>
                  <option value="value3">INR</option>
                </select>
                <select name="toCurrency" required>
                  <option value="value1" selected>EUR</option>
                  <option value="value2">USD</option>
                  <option value="value3">INR</option>
                 </select>
                <input type="submit" value="Get Exchange Rate">
                <span id="result">50.50</span>
            </form>
         </div>
    </div>
    <div id="history" class="history">
         <table>
            <tr>
                <th height="50px" width="30%">From Currency</th>
                <th>To Currency</th>
                <th>Rate</th>
                <th>Date</th>
            </tr>
            <tr style="color: #205081;">
                <td height="30px">USD</td>
                <td>INR</td>
                <td>66.67</td>
                <td>10-DEC-2016</td>
            </tr>

         </table>
    </div>
</body>
</html>