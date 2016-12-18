<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Main Page</title>
    <link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <link rel="stylesheet" type="text/css" href="css/main.css"/>
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
</head>
<body>
  <div class="user-info">
    <span>Welcome, Deepak Kulkarni <br> <a href="/currency-convertor/invalidate">Logout</a></span>
  </div>
    <div id="convert" class="main-page">
        <div id="inner">
            <form action="convert" method="post">
                <input type="text" name="exchangeDate" id="datepicker" placeholder="Date of exchange" required>
                <select name="fromCurrency" required>
                  <c:forEach var="currency" items="${currencyTypes}">
                      <option value="${currency}" ${currency == 'USD' ? 'selected' : ''}>${currency}</option>
                  </c:forEach>
                </select>
                <select name="toCurrency" required>
                  <c:forEach var="currency" items="${currencyTypes}">
                    <option value="${currency}" ${currency == 'GBP' ? 'selected' : ''}>${currency}</option>
                  </c:forEach>
                 </select>
                <input type="submit" value="Get Exchange Rate">
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
            <c:forEach var="conversion" items="${conversions}">
                <tr style="color: #205081;">
                    <td height="30px">${conversion.fromCurrency}</td>
                    <td>${conversion.toCurrency}</td>
                    <td>${conversion.rate}</td>
                    <td>${conversion.exchangeDate}</td>
                </tr>
            </c:forEach>

         </table>
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
      </script>
</body>
</html>