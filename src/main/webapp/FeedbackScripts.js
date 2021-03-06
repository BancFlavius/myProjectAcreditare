function showFeedback (lista) {
    var randuri = "";
    lista.forEach(function (obiect) {
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
    </div></div>`;
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
