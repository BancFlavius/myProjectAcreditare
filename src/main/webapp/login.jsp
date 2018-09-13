<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    HttpSession s = request.getSession();
    Object o = s.getAttribute("userid");
    Object o2 = s.getAttribute("verified");

    if(o!=null && o2!=null)
    {
        response.sendRedirect("home.jsp");
    }
%>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Log in</title>
    <link rel="stylesheet" href="style.css">
</head>
<body style="margin: 0px;">

<div id="top-menu-bar">
    <ul>

        <li><a href="home.jsp">Home</a></li>
        <li><a href="cv.jsp">CV</a></li>
        <li><a href="feedback.jsp">Feedback</a></li>
        <li><a href="">Contact</a></li>

        <ol>
            <li><a href="register.html">Sign Up</a></li>
        </ol>
    </ul>
</div>

<div class="page-block">
    <h2>Log In</h2>
    <form action="login" method="POST">
        <div>
            <label for="email">Email: </label>
            <input type="email" id="email" name="email" required="required" placeholder="name@domain.com">
        </div>
        <div style="padding-right: 33px">
            <label for="password"> Password: </label>
            <input type="password" id="password" name="password" required="required" placeholder="Password">
            <input type="hidden" name="action" value="login">
        </div>
        <div>
            <input type="submit">
        </div>
    </form>

    <p style="font-size:13px">Don't have an account? <a href="register.html">Register</a></p>
</div>

</body>
</html>