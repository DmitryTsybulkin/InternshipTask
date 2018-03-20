$(document).ready(function() {
    $.ajax({
        url: "localhost:8080/users"
    }).then(function(data) {
        $('#ident').append(data.id);
        $('#login').append(data.login);
    });
});

//проблема с 403
//проблема с ролями
//проблема с дропом бд
//проблема с js
//проблема с токенами