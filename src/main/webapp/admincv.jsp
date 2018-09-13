<%@ page import="java.sql.*" %>
<%
    HttpSession s = request.getSession();
    Object o = s.getAttribute("userid");
    if(o==null)
    {
        response.sendRedirect("login.html");
    }
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>CV</title>
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

    <div id="object">

    </div>


<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="CVscripts.js"></script>

</body>
</html>