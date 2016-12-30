<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
    <title>Main Page</title>
    <link rel="stylesheet" href="css/jquery-ui.css">
    <link rel="stylesheet" type="text/css" href="css/main.css"/>
    <script src="js/jquery-3.1.1.js"></script>
    <script src="js/jquery-ui.js"></script>
    <script src="js/validation/main-validation.js"></script>
    <script src="js/jquery.validate.min.js"></script>
</head>
<body>
  <div class="user-info">
    <span>Welcome, ${name} <br> <a href="/currency-convertor/invalidate">Logout</a></span>
  </div>
    <div id="convert" class="main-page">
        <div id="inner">
            <form action="convert" method="post" id="convertForm">
                <input type="text" name="exchangeDate" id="conversionDate" placeholder="Date of exchange" required readonly>
                <select name="fromCurrency" required>
                  <c:forEach var="currency" items="${currencyTypes}">
                      <option value="${currency}" ${currency == 'EUR' ? 'selected' : ''}>${currency}</option>
                  </c:forEach>
                </select>
                <select name="toCurrency" required>
                  <c:forEach var="currency" items="${currencyTypes}">
                    <option value="${currency}" ${currency == 'USD' ? 'selected' : ''}>${currency}</option>
                  </c:forEach>
                 </select>
                <input type="submit" value="Get Exchange Rate">
            </form>
         </div>
    </div>

   <c:if test="${!empty(conversions)}">
        <div id="history" class="history">
             <table>
                <tr>
                    <th height="50px" width="30%">From Currency</th>
                    <th>To Currency</th>
                    <th>Rate</th>
                    <th>Date</th>
                </tr>
                <c:forEach var="conversion" items="${conversions}">
                    <tr class="row">
                        <td height="30px">${conversion.fromCurrency}</td>
                        <td>${conversion.toCurrency}</td>
                        <td>${conversion.rate}</td>
                        <td><fmt:formatDate value="${conversion.exchangeDate}" pattern="dd-MMM-yyyy"/></td>
                    </tr>
                </c:forEach>
             </table>
        </div>
    </c:if>

    <script>
        $(function() {
            var currentDate = new Date();
            $("#conversionDate").datepicker({
                changeMonth: true,
                changeYear: true,
                yearRange: "-10:+0",
                maxDate: 0,
                dateFormat: 'dd-M-yy'
              });
              $("#conversionDate").datepicker("setDate", currentDate);
        });
      </script>
</body>
</html>