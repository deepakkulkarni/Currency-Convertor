$(document).ready(function() {

    $("#convertForm").validate({
    rules: {
        exchangeDate: "required"
    },
    messages: {
        exchangeDate: "Exchange Date field cannot be blank!"
        }
    })

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

 });