<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    HttpSession s = request.getSession();
    Object o = s.getAttribute("userid");
    Object o2 = s.getAttribute("verified");

    if(o==null || o2==null)
    {
        response.sendRedirect("login.jsp");
    }
%>

<html>
<head>
    <meta charset="UTF-8">
    <title>Feedbak</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>

<div id="top-menu-bar">
    <ul>
        <li><a href="home.jsp">Home</a></li>
        <li><a href="cv.jsp">CV</a></li>
        <li><a href="feedback.jsp">Feedback</a></li>
        <li><a style="cursor: default"><div class="dropdown">Contact <div class="dropdown-content">
            <div class="desc">For any business inquires or additional information please contact us at:<br>
                Phone number: <font color="#9370db">+407321312</font> <br>
                E-mail: <font color="#9370db">myappacreditare@gmail.com</font></div></div></div></a></li>

        <ol>
            <li><a href="signout">Sign Out</a></li>
        </ol>
    </ul>
</div>

<div class="page-block">
    <h2>Send feedback</h2>
    <form action="feedback" method="POST">
        <textarea name="message" id="umessage" cols="30" rows="10" maxlength="1000" placeholder="Type your message here..." required="required" style="height:200px;width: 700px"></textarea>
        <div style="text-align-all: center">
           <div><input class="css-checkbox" type="radio" name="utype" id="suggestion" value="suggestion" required> <label class="css-label" for="suggestion">Suggestion</label>
         <input class="css-checkbox" type="radio" name="utype" value="issue" id="issue" required> <label class="css-label" for="issue">Issue</label></div>
        </div>
        <div>
            <input type="hidden" name="action" value="list">
            <button type="submit">Send</button>
        </div>
    </form>
</div>

<div id="obiect">

</div>


<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="FeedbackScripts.js"></script>

</body>
</html>
