<%
    HttpSession s = request.getSession();
    Object o = s.getAttribute("userid");
    Object o2 = s.getAttribute("admin");
    if(o==null && o2==null)
    {
        response.sendRedirect("login.html");
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

        <ol style="padding-left: 73%">
            <li><a href="signout">Sign Out</a></li>
        </ol>
    </ul>
</div>

<div class="page-block">
    You can see one cv and click show more to redirect you to admincv.jsp
</div>

<div class="page-block">
    You can see one feedback and click show more to redirect you to adminfeedback.jsp
</div>


</body>
</html>
