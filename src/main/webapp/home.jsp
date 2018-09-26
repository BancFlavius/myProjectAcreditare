<%@ page import="java.sql.*" %>
<%

    HttpSession s = request.getSession();
    Object o = s.getAttribute("userid");
    Object o2 = s.getAttribute("verified");

    if(o==null || o2==null)
    {
        response.sendRedirect("login.jsp");
    }else if(o!=null){
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
<%--information about the company--%>
    <p>Our Company is a UK based limited company which has recruited the best
    experienced agents and is run by two experienced directors who have established
    themselves in the UK education market working with many top universities and language
    schools.</p>
    <p>All of our staff have come from either working independently as agents or worked for other
    agencies in the UK or elsewhere in the world and now work under the umbrella of World
    Choice Education only.</p>
    <p>The core of our business is to introduce international students to the UK and other
    worldwide destinations which include, the USA, Australia, New Zealand, Canada and China
    to study at a University or a Language School.</p>
    <p>We currently work in the following countries, and have staff who divide their time between
    these countries and our UK office.</p>
</div>

<%
    String stringToConvert = String.valueOf(o);
    Long convertedLong = Long.parseLong(stringToConvert);
    boolean found = false;
    try {
        Class.forName("org.postgresql.Driver");

        final String URL = "jdbc:postgresql://54.93.65.5:5432/flavius8";
        final String USERNAME = "fasttrackit_dev";
        final String PASSWORD = "fasttrackit_dev";

        Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        PreparedStatement pSt = conn.prepareStatement("SELECT idutilizator FROM cv WHERE idutilizator=?");
        pSt.setLong(1, convertedLong);

        ResultSet rs = pSt.executeQuery();

        while (rs.next()){
            if(rs.getLong("idutilizator") == convertedLong) found = true;
        }
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    } catch (SQLException e) {
        e.printStackTrace();
    }

    if(!found){
%>
<div class="page-block">
    You didn't complete your CV. <br>
    You can submit it by clicking <a href="cv.jsp" style="color: mediumpurple">here.</a>
</div>
<%
} else {
%>

<div id="object">

</div>

<%
    }
    }
%>


<%
    String stringToConvert = String.valueOf(o);
    Long convertedLong = Long.parseLong(stringToConvert);
    boolean found = false;
    try {
        Class.forName("org.postgresql.Driver");

        final String URL = "jdbc:postgresql://54.93.65.5:5432/flavius8";
        final String USERNAME = "fasttrackit_dev";
        final String PASSWORD = "fasttrackit_dev";

        Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        PreparedStatement pSt = conn.prepareStatement("SELECT idutilizator FROM feedback WHERE idutilizator=?");
        pSt.setLong(1, convertedLong);

        ResultSet rs = pSt.executeQuery();

        while (rs.next()){
            if(rs.getLong("idutilizator") == convertedLong) found = true;
        }
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    } catch (SQLException e) {
        e.printStackTrace();
    }

    if(!found){
%>

<div class="page-block">
    If you have any suggestion or issue you would like to make us aware of, please send it <a href="feedback.jsp" style="color: mediumpurple">here.</a>
</div>

<%
} else {
%>

<div id="obiect">

</div>

<%
    }
%>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="HomeScripts.js"></script>


</body>
</html>
