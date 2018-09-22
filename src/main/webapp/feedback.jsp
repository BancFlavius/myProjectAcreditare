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
        <li><a href="">Contact</a></li>

        <ol>
            <li><a href="signout">Sign Out</a></li>
        </ol>
    </ul>
</div>

<div class="page-block">
    <h2>Send feedback</h2>
    <form action="feedback" method="POST">
        <textarea name="message" id="umessage" cols="30" rows="10" maxlength="1000" placeholder="Type your message here..." required="required" style="height:200px;width: 700px"></textarea>
        <div style="text-align-all: center; padding-right: 50px">
            Suggestion<input type="radio" name="utype" value="suggestion" required>
            <input type="radio" name="utype" value="issue" required> Issue
        </div>
        <div style="text-align: center">
            <input type="hidden" name="action" value="list">
            <input type="submit">
        </div>
    </form>
</div>

<div id="obiect">

</div>


<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="FeedbackScripts.js"></script>

</body>
</html>
