function showFeedback (lista) {
    var randuri = "";
    var counter = 0;
    lista.forEach(function (obiect) {
        counter++;
        if (counter <= 1) {
            var style = "";
            if (obiect.feedbackType == 1) {
                style = "style=\"background: green;\"";
            } else if (obiect.feedbackType == 0) {
                style = "style=\"background: red;\"";
            }

            randuri += `<div class="page-block"><div class="cv-block" ${style} >
        <div id="parent_div_1">
            Name: ${obiect.firstn}
            ${obiect.lastn}
        </div>

        <div id="parent_div_2" style="float: right">
            Date: ${obiect.date}
        </div>
        <div class="message_div"><p>${obiect.message}</p></div>
    </div><div style="font-weight: 900;">Click <a href="feedback.jsp">here</a> to send or delete feedback.</div></div>`;
        }
    });
    $("#obiect").html(randuri);}


function getListFeedback(cautaText) {
    $.ajax("feedbackget?action=list", {
        cache: false,
        dataType: "json",
        data: {
            search: cautaText
        }
    }).done(function (lista) {
        console.info("a venit lista", lista);
        showFeedback(lista.feedbacks);
    });
}

getListFeedback();

function showCV (lista) {
    $(document).ready(function(){
        $("#edit").click(function(){
            $("#toggle").fadeToggle("slow");
            $("#out").fadeOut();
        });
        $("#cancel").click(function(){
            $("#toggle").fadeToggle("slow");
            $("#out").fadeIn();
        });
    });
    var randuri = "";
    lista.forEach(function (object) {
        var iduser = object.iduser;
        randuri += `<div id="out"><div class="page-block"><div class="cv-block">
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
    <div>
    If you would like to edit your CV, please click <a href="cv.jsp">here</a>.
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




