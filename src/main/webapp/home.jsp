<%

    HttpSession s = request.getSession();
    Object o = s.getAttribute("userid");
    Object o2 = s.getAttribute("verified");

    if(o==null || o2==null)
    {
        response.sendRedirect("login.jsp");
    }
%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home</title>
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
<%--profile info like name, where the person lives and education--%>
</div>

<div class="page-block">
    <h2 style="text-align: left; padding-left: 5%">CV</h2>
</div>

</body>
</html>
