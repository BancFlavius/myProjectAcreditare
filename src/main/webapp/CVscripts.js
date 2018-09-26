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
        
    </div><button id="edit">edit</button></div></div>

<div id="toggle" style="display: none">
<div class="page-block">
    <h2>Update CV</h2>
    <form action="cv" method="POST">
        <div class="cv-block">
            About me:
            <div>
                <textarea name="question1" id="question1" maxlength="500"  required="required"  cols="30" rows="10" >`+ object.q1 +`</textarea>
            </div>
        </div>
        <div class="cv-block">
            Professional experience:
            <div>
                <textarea name="question2" id="question2" maxlength="500" cols="30" rows="10" >`+ object.q2 +`</textarea>
            </div>
        </div>

        <div class="cv-block">
            Education:
            <div>
                <textarea name="question3" id="question3" maxlength="500" required="required" cols="30" rows="10" >`+ object.q3 +`</textarea>
            </div>
        </div>

        <div class="cv-block">
            Foreign languages:
            <div>
                <textarea name="question4" id="question4" maxlength="500" cols="30" rows="10" >`+ object.q4 +`</textarea>
            </div>
        </div>

        <div class="cv-block">
            Skills:
            <div>
                <textarea name="question5" id="question5" maxlength="500" required="required" cols="30" rows="10" >`+ object.q5 +`</textarea>
            </div>
        </div>

        <div class="cv-block">
            Other info:
            <div>
                <textarea name="question6" id="quesiton6" maxlength="500" cols="30" rows="10" >`+ object.q6 +`</textarea>
            </div>
        </div>

        <div style="text-align: center">
        <input type="hidden" name="action" value="update">
            <button type="submit" style="margin-right: 30px">Update</button>
            <button id="cancel" style="margin-left: 30px">Cancel</button>
        </div>
    </form>
</div>
</div>

`;

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


