$(document).ready(function() {

    $("#registrationForm").validate({
    rules: {
        name: "required",
        username: {
            required: true
        },
        password: {
            required: true,
            minlength: 6
        },
        confirmPassword: {
            required: true,
            minlength: 6,
            equalTo: "#password"
        },
        email: {
           required: true,
           email: true
        },
        dob : "required",
        address: "required",
        pin: "required",
        city: "required",
        country: "required"
    },
    messages: {
        name: "Name field cannot be blank!",
        username: {
            required: "Username field cannot be blank!",
        },
        password: {
            required: "Password field cannot be blank!",
            minlength: "Password must have at least 6 characters"
        },
        confirmPassword: {
            required: "Confirm Password field cannot be blank!",
            minlength: "Password must have at least 6 characters",
            equalTo: "Please enter the same password as above"
        },
        email: {
            required: "Email field cannot be blank!",
            email: "Please enter a valid email address"
         },
        address: "Address field cannot be blank!",
        dob: "Date of Birth field cannot be blank!",
        pin: "Pin field cannot be blank!",
        city: "City field cannot be blank!",
        country: "Please select your country"
    }});

    $(function() {
            $("#birthdate").datepicker({
                changeMonth: true,
                changeYear: true,
                yearRange: "-100:+0",
                maxDate: 0,
                dateFormat: 'dd-M-yy'
              });
        });

        $("#back").click(function () {
            window.location.href = '/currency-converter/';
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
});