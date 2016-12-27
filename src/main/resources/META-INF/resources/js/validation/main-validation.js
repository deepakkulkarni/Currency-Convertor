$(document).ready(function() {
    $("#convertForm").validate({
    rules: {
        exchangeDate: "required"
    },
    messages: {
        exchangeDate: "Exchange Date field cannot be blank!"
        }
 )}});