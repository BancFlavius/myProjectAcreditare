<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%

%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Register</title>
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
            <li><a href="login.jsp">Sign In</a></li>
        </ol>
    </ul>
</div>

<div class="page-block">
    <h2>Register</h2>
    <form action="register" method="POST">
        <div style="padding-right: 35px">
            <label for="ufirst">First Name: </label>
            <input type="text" id="ufirst" name="first" required="required">
        </div>
        <div style="padding-right: 35px">
            <label for="ulast">Last Name: </label>
            <input type="text" id="ulast" name="last" required="required">
        </div>
        <div>
            <label for="uemail">E-mail: </label>
            <input type="email" id="uemail" name="email" required="required" placeholder="name@domain.com">
        </div>
        <div>
            <label for="uage">Age: </label>
            <input type="date" id="uage" name="age" required="required">
        </div>
        <div style="padding-right: 30px">
            <label for="upassword"> Password: </label>
            <input type="password" id="upassword" name="password" required="required" placeholder="Password">
            <input type="hidden" name="action" value="register">
        </div>
        <div>
            <button type="submit">Register</button>
        </div>
    </form>

    <p style="font-size:13px">Already have an account? <a href="login.jsp">Sign In</a></p>
</div>

</body>
</html>