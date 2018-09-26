function showCV (lista) {
    $(document).ready(function(){
        $("button").click(function(){
            $("#div1").fadeToggle("slow");
        });
    });
    var randuri = "";
    lista.forEach(function (object) {
        var iduser = object.iduser;
        var deleteF = '<div style="text-align: right"><a href="cv?action=delete&iduser='+iduser+'">X</a></div>';
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
    </div>
        <button>Contact</button>
     <div id="div1" style="display: none">
      <form action="contact" method="post">
        <textarea name="message" id="umessage" cols="30" rows="10" maxlength="450" placeholder="Type your message here..." style="height:115px;width: 620px"></textarea>
        <input type="hidden" name="email" id="umail" value="`+object.email+`">
        <input type="hidden" name="action" value="cv">
        <div><input type="submit" value="Send Email"></div>
      </form>
     </div>
    </div>`;

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

