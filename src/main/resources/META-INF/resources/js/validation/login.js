$(document).ready(function() {
    $("#loginForm").validate({
    rules: {
        username: "required",
        password: "required",
        password: {
            required: true,
            minlength: 6
        }
    },
    messages: {
        username: "Username field cannot be blank!",
        password: {
            required: "Password field cannot be blank!",
            minlength: "Password must have at least 6 characters"
        }
    }});

    $("#register").click(function () {
         window.location.href = 'register';
    });
});