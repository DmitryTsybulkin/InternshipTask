$(document).ready(function() {
    $.ajax({
        url: "localhost:8080/users"
    }).then(function(data) {
        $('#ident').append(data.id);
        $('#login').append(data.login);
    });
});