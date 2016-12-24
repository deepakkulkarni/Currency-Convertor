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
        pin: "Pin field cannot be blank!",
        city: "City field cannot be blank!",
        country: "Please select your country"
    }});
});