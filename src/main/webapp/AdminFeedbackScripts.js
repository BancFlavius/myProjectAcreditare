function showFeedback (lista) {
    $(document).ready(function(){
        $("button").click(function(){
            $("#div1").fadeToggle("slow");
        });
    });
    var randuri = "";
    var counter = 0;
    lista.forEach(function (obiect) {
        counter++;
        var deleteF = obiect.idfeedback;
        var style = "";
        if(obiect.feedbackType == 1){
            style = "style=\"background: green;\"";
        } else if(obiect.feedbackType == 0){
            style = "style=\"background: red;\"";
        }

        randuri += `<div class="page-block"><div style="text-align: right"><a href="feedback?action=delete&idfeedback=`+deleteF+`">X</a></div><div class="cv-block" ${style} >
        <div id="parent_div_1">
            Name: ${obiect.firstn}
            ${obiect.lastn}
        </div>

        <div id="parent_div_2" style="float: right">
            Date: ${obiect.date}
        </div>
        <div class="message_div"><p>${obiect.message}</p></div>
    </div>
    <button>Contact</button>
    <div id="div1" style="display: none">
      <form action="contact" method="post">
        <textarea name="message" id="umessage" cols="30" rows="10" maxlength="450" placeholder="Type your message here..." style="height:115px;width: 620px"></textarea>
        <input type="hidden" name="email" id="umail" value="`+obiect.email+`">
        <input type="hidden" name="action" value="feedback">
        <div><input type="submit" value="Send Email"></div>
      </form>
    </div>
    </div>`;
    }); if(counter==0){
        randuri+= `<div class="page-block">No feedbacks to review.</div>`;
    }
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

getListFeedback('asc');




function sorteazaNume(th){
    if(sortareaAnterioara == "asc"){
        cereLista();
        th.innerHTML = "obiecte &uArr;";
        sortareaAnterioara = "desc";
    } else {
        cereLista();
        th.innerHTML = "obiecte &dArr;";
        sortareaAnterioara = "asc";
    }
}


function cauta(cautaText) {
    console.info("cauta ", cautaText);
    cereLista(sortareaAnterioara, cautaText);
}
