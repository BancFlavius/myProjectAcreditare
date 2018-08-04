<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <meta charset="UTF-8">
    <title>Feedbak</title>
</head>
<body>

<%
HttpSession s = request.getSession();
Object o = s.getAttribute("userid");
if(o==null) { response.sendRedirect("signup.jsp"); }
    int value = Integer.parseInt(o.toString());
%>
</body>

hai salut

<p>
    <a href="signup.jsp">Logout</a>
</p>
