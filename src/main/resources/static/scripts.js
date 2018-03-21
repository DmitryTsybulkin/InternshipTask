// $(document).ready(function() {
//     $.ajax({
//         url: "localhost:8080/users"
//     }).then(function(data) {
//         $('#ident').append(data.id);
//         $('#login').append(data.login);
//     });
// });

var service = 'http://localhost';

$(document).ready(function(){

    jQuery.support.cors = false;

    var table = $('#fsd').DataTable

    $.ajax(
        {
            type: "GET",
            url: service + '/users',
            data: "{}",
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            cache: false,
            success: function (data) {

                var trHTML = '';

                $.each(data.Countries, function (i, item) {



                    trHTML += '<tr><td>' + data.id + '</td><td>' + data.login + '</td></tr>';
                });

                $('#location').append(trHTML);

            },

            error: function (msg) {

                alert(msg.responseText);
            }
        });
})