<%@ page import="java.sql.*" %>
<%
    HttpSession s = request.getSession();
    Object o = s.getAttribute("userid");
    Object o2 = s.getAttribute("verified");

    if(o==null || o2==null) {
        response.sendRedirect("login.jsp");
    } else if(o!=null){
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
    <h2>Send Curriculum Vitae</h2>
    <form action="cv" method="POST">
        <div class="cv-block">
            About me:
            <div>
                <textarea name="question1" id="question1" maxlength="500"  required="required"  cols="30" rows="10"></textarea>
            </div>
        </div>
        <div class="cv-block">
            Professional experience:
            <div>
                <textarea name="question2" id="question2" maxlength="500" cols="30" rows="10" ></textarea>
            </div>
        </div>

        <div class="cv-block">
            Education:
            <div>
                <textarea name="question3" id="question3" maxlength="500" required="required" cols="30" rows="10" ></textarea>
            </div>
        </div>

        <div class="cv-block">
            Foreign languages:
            <div>
                <textarea name="question4" id="question4" maxlength="500" cols="30" rows="10" ></textarea>
            </div>
        </div>

        <div class="cv-block">
            Skills:
            <div>
                <textarea name="question5" id="question5" maxlength="500" required="required" cols="30" rows="10" ></textarea>
            </div>
        </div>

        <div class="cv-block">
            Other info:
            <div>
                <textarea name="question6" id="quesiton6" maxlength="500" cols="30" rows="10" ></textarea>
            </div>
        </div>

        <div>
            <button type="submit">Submit</button>
        </div>
    </form>
</div>

<%--ends if statement from line 58--%>
<%
    } else {
%>

    <div id="object">

    </div>

<%
    }
    }
%>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="CVscripts.js"></script>

</body>
</html>
