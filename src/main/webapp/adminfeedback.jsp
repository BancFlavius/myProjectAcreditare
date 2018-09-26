<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    HttpSession s = request.getSession();
    Object o = s.getAttribute("userid");
    Object o2 = s.getAttribute("admin");

    if(o==null && o2==null)
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
        <li><a href="adminhome.jsp">Home</a></li>
        <li><a href="admincv.jsp">CV</a></li>
        <li><a href="adminfeedback.jsp">Feedback</a></li>

        <ol>
            <li><a href="signout">Sign Out</a></li>
        </ol>
    </ul>
</div>


    <div id="obiect">

    </div>


<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="AdminFeedbackScripts.js"></script>

</body>
</html>