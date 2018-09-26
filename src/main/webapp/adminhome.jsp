<%
    HttpSession s = request.getSession();
    Object o = s.getAttribute("userid");
    Object o2 = s.getAttribute("admin");
    if(o==null && o2==null)
    {
        response.sendRedirect("login.jsp");
    }
%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin Home</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>

<div>
    <ul id="top-menu-bar">
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

<div id="obiect">

</div>


<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="AdminHomeScripts.js"></script>

</body>
</html>
