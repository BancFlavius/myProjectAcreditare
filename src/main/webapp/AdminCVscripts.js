function showCV (lista) {
    var randuri = "";
    lista.forEach(function (object) {
        var iduser = object.iduser;
        var deleteF = '<a href="cv?action=delete&iduser='+iduser+'">X</a>';
        randuri += `<div class="page-block">`+deleteF+`<div class="cv-block">
        <div id="parent_div_1">
            Name: ${object.firstn}
            ${object.lastn}
        </div>

        <div id="parent_div_2" style="float: right">
            Date: ${object.date}
        </div>
        <div class="message_div">About me: <p class='editable'> ${object.q1}</p></div><hr>
        <div class="message_div">Professional experience: <p class='editable'> ${object.q2}</p></div><hr>
        <div class="message_div">Education: <p class='editable'>${object.q3}</p></div><hr>
        <div class="message_div">Foreign languages: <p class='editable'>${object.q4}</p></div><hr>
        <div class="message_div">Skills: <p class='editable'>${object.q5}</p></div><hr>
        <div class="message_div">Other info: <p class='editable'>${object.q6}</p></div>
    </div></div>`;

    });
    $("#object").html(randuri);}

function getListCV(cautaText) {
    $.ajax("cvget?action=list", {
        cache: false,
        dataType: "json",
        data: {
            search: cautaText
        }
    }).done(function (lista) {
        console.info("a venit lista", lista);
        showCV(lista.cvs);
    });
}


getListCV();
var sortareaAnterioara = 'asc';

