function showFeedback (list) {
    var lines = "<div class=\"page-block\">";
    var counter = 0;
    list.forEach(function (obiect) {
        counter++;
        var style = "";
        if(obiect.feedbackType == 1){
            style = "style=\"background: green;\"";
        } else if(obiect.feedbackType == 0){
            style = "style=\"background: red;\"";
        }

        lines += `<div style="text-align: right"><a href="feedback?action=delete&idfeedback=`+obiect.idfeedback+`">X</a></div><div class="cv-block" ${style} >
        <div id="parent_div_1">
            Name: ${obiect.firstn}
            ${obiect.lastn}
        </div>

        <div id="parent_div_2" style="float: right">
            Date: ${obiect.date}
        </div>
        <div class="message_div"><p>${obiect.message}</p></div>
    </div>
    <button class="button`+ obiect.idfeedback +`">Contact</button>
    <div id="div`+ obiect.idfeedback +`" style="display: none">
      <form action="contact" method="post">
        <textarea name="message" id="umessage" cols="30" rows="10" maxlength="450" placeholder="Type your message here..." style="height:115px;width: 620px"></textarea>
        <input type="hidden" name="email" id="umail" value="`+obiect.email+`">
        <input type="hidden" name="action" value="feedback">
        <div><button type="submit">Send Email</button></div>
      </form>
    </div>
    <hr>
    `;

    });lines += `</div>`;
    if(counter==0){
        lines = "";
        lines+= `<div class="page-block">No feedbacks to review.</div>`;
    }

    $("#obiect").html(lines);}


function getListFeedback(cautaText) {
    $.ajax("feedbackget?action=list", {
        cache: false,
        dataType: "json",
        data: {
            search: cautaText
        }
    }).done(function (list) {
        console.info("a venit list", list);
        showFeedback(list.feedbacks);
        $('button').click(function(){
            $(this).next().fadeToggle("slow");
        })
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
