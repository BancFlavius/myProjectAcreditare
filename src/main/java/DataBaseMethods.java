import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class DataBaseMethods {
    static void register(String first, String last, String password, String email, String age){

        try {
            Class.forName("org.postgresql.Driver");

            final String URL = "jdbc:postgresql://54.93.65.5:5432/flavius8";
            final String USERNAME = "fasttrackit_dev";
            final String PASSWORD = "fasttrackit_dev";

            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            PreparedStatement pSt = conn.prepareStatement("INSERT INTO utilizatori(firstn, lastn, upassword, email, age, uadmin, verified) VALUES (?,?,?,?,Cast(? as date), 0, 0)");
            pSt.setString(1, first);
            pSt.setString(2, last);
            pSt.setString(3, password);
            pSt.setString(4, email);
            pSt.setString(5, age);

            pSt.executeUpdate();
            pSt.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    static boolean exists(String email){
        boolean exists = false;
        try {
            Class.forName("org.postgresql.Driver");

            final String URL = "jdbc:postgresql://54.93.65.5:5432/flavius8";
            final String USERNAME = "fasttrackit_dev";
            final String PASSWORD = "fasttrackit_dev";

            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            PreparedStatement pSt = conn.prepareStatement("SELECT email FROM utilizatori WHERE email=?");
            pSt.setString(1,email);

            ResultSet rs = pSt.executeQuery();

            int temp=0;
            while (rs.next()){ temp++; }
            if(temp>=1){ exists=true; }

            rs.close();
            pSt.close();
            conn.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exists;
    }


    static long login(String email, String password) {
        long temp = -1;
        try {
            Class.forName("org.postgresql.Driver");

            final String URL = "jdbc:postgresql://54.93.65.5:5432/flavius8";
            final String USERNAME = "fasttrackit_dev";
            final String PASSWORD = "fasttrackit_dev";

            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            PreparedStatement pSt = conn.prepareStatement("SELECT id FROM utilizatori WHERE email=? AND upassword=?");
            pSt.setString(1, email);
            pSt.setString(2, password);

            ResultSet rs = pSt.executeQuery();

            while(rs.next()){  temp=(rs.getLong("id")); }

            rs.close();
            pSt.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return temp;
    }


    static void feedback(long id, String mesaj, String type){
        try {
            Class.forName("org.postgresql.Driver");

            final String URL = "jdbc:postgresql://54.93.65.5:5432/flavius8";
            final String USERNAME = "fasttrackit_dev";
            final String PASSWORD = "fasttrackit_dev";

            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            PreparedStatement pSt = conn.prepareStatement("INSERT INTO feedback(idutilizator, mesaj, udate, utype) VALUES (?,?, now(), ?) ");
            pSt.setLong(1, id);
            pSt.setString(2, mesaj);
            pSt.setString(3, type);

            pSt.executeUpdate();

            pSt.close();
            conn.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static void cv(long id, String q1, String q2, String q3, String q4, String q5, String q6){
        try {
            Class.forName("org.postgresql.Driver");

            final String URL = "jdbc:postgresql://54.93.65.5:5432/flavius8";
            final String USERNAME = "fasttrackit_dev";
            final String PASSWORD = "fasttrackit_dev";

            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            PreparedStatement pSt = conn.prepareStatement("INSERT INTO cv(idutilizator, question1, question2, question3, question4, question5, question6, udate) VALUES(?,?,?,?,?,?,?, now())");
            pSt.setLong(1, id);
            pSt.setString(2, q1);
            pSt.setString(3, q2);
            pSt.setString(4, q3);
            pSt.setString(5, q4);
            pSt.setString(6, q5);
            pSt.setString(7, q6);

            pSt.executeUpdate();

            pSt.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    static boolean admin(long id){
        boolean admin = false;
        try {

            Class.forName("org.postgresql.Driver");

            final String URL = "jdbc:postgresql://54.93.65.5:5432/flavius8";
            final String USERNAME = "fasttrackit_dev";
            final String PASSWORD = "fasttrackit_dev";

            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            PreparedStatement pSt = conn.prepareStatement("SELECT uadmin FROM utilizatori WHERE id=?");
            pSt.setLong(1, id);

            ResultSet rs = pSt.executeQuery();
            while(rs.next()){
                if(rs.getInt("uadmin") == 1){ admin = true; }
            }

            rs.close();
            pSt.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return admin;
    }


    static List<Person> getFeedback(long id){
        List PersonList = new ArrayList<Person>();
        try {
            Class.forName("org.postgresql.Driver");

            final String URL = "jdbc:postgresql://54.93.65.5:5432/flavius8";
            final String USERNAME = "fasttrackit_dev";
            final String PASSWORD = "fasttrackit_dev";

            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            PreparedStatement pSt = conn.prepareStatement("SELECT feedback.id, feedback.idutilizator, feedback.mesaj, feedback.udate, utilizatori.lastn, utilizatori.firstn, feedback.utype FROM feedback,utilizatori WHERE utilizatori.id = ? AND feedback.idutilizator = ?");
            pSt.setLong(1, id);
            pSt.setLong(2, id);

            ResultSet rs = pSt.executeQuery();

            while (rs.next()){
                if(rs.getLong("idutilizator") == id){
                    String umessage = rs.getString("mesaj");
                    String firstn = rs.getString("firstn");
                    String lastn = rs.getString("lastn");
                    String date = rs.getString("udate");

                    Person p = new Person();
                    p.message = umessage;
                    p.firstn = firstn;
                    p.lastn = lastn;
                    p.date = date;
                    p.setIdfeedback(rs.getLong("id"));
                    if(rs.getString("utype").equals("suggestion")){
                        p.setFeedbackType(1);
                    } else if(rs.getString("utype").equals("issue")){
                        p.setFeedbackType(0);
                    }
                    PersonList.add(p);
                }
            }

            rs.close();
            pSt.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return PersonList;
    }


    static List<Person> getFeedback(){
        List PersonList = new ArrayList<Person>();
        try {
            Class.forName("org.postgresql.Driver");

            final String URL = "jdbc:postgresql://54.93.65.5:5432/flavius8";
            final String USERNAME = "fasttrackit_dev";
            final String PASSWORD = "fasttrackit_dev";

            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            PreparedStatement pSt = conn.prepareStatement("SELECT utilizatori.id, feedback.id, feedback.idutilizator, feedback.mesaj, feedback.udate, utilizatori.lastn, utilizatori.firstn, feedback.utype FROM feedback,utilizatori");

            ResultSet rs = pSt.executeQuery();

            while (rs.next()){
                if (rs.getLong("idutilizator") == rs.getLong("id")) {
                    Person p = new Person();

                    p.message = rs.getString("mesaj");
                    p.firstn = rs.getString("firstn");
                    p.lastn = rs.getString("lastn");
                    p.date = rs.getString("udate");
                    p.setIdfeedback(rs.getLong("id"));
                    if(rs.getString("utype").equals("suggestion")){
                        p.setFeedbackType(1);
                    } else if(rs.getString("utype").equals("issue")){
                        p.setFeedbackType(0);
                    }

                    PersonList.add(p);
                }
            }

            rs.close();
            pSt.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return PersonList;
    }


    static List<Person> getCV(long id){
        List PersonList = new ArrayList<Person>();

        try {
            Class.forName("org.postgresql.Driver");

            final String URL = "jdbc:postgresql://54.93.65.5:5432/flavius8";
            final String USERNAME = "fasttrackit_dev";
            final String PASSWORD = "fasttrackit_dev";

            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            PreparedStatement pSt = conn.prepareStatement("SELECT cv.id, cv.idutilizator, cv.question1, cv.question2, cv.question3, cv.question4, cv.question5, cv.question6, cv.udate, utilizatori.lastn, utilizatori.firstn FROM cv, utilizatori WHERE utilizatori.id = ? AND cv.idutilizator=?");
            pSt.setLong(1, id);
            pSt.setLong(2, id);

            ResultSet rs = pSt.executeQuery();
            while (rs.next()) {
                if (rs.getLong("idutilizator") == id) {
                    Person p = new Person();
                    p.date = rs.getString("udate");
                    p.lastn = rs.getString("lastn");
                    p.firstn = rs.getString("firstn");
                    p.q1 = rs.getString("question1");
                    p.q2 = rs.getString("question2");
                    p.q3 = rs.getString("question3");
                    p.q4 = rs.getString("question4");
                    p.q5 = rs.getString("question5");
                    p.q6 = rs.getString("question6");
                    p.setIdcv(rs.getLong("id"));
                    p.setIsAdmin(rs.getLong("uadmin"));


                    PersonList.add(p);
                }
            }

            rs.close();
            pSt.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return PersonList;
    }


    static List<Person> getCV(){
        List PersonList = new ArrayList<Person>();

        try {
            Class.forName("org.postgresql.Driver");

            final String URL = "jdbc:postgresql://54.93.65.5:5432/flavius8";
            final String USERNAME = "fasttrackit_dev";
            final String PASSWORD = "fasttrackit_dev";

            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            PreparedStatement pSt = conn.prepareStatement("SELECT utilizatori.id, cv.id, cv.idutilizator, cv.question1, cv.question2, cv.question3, cv.question4, cv.question5, cv.question6, cv.udate, utilizatori.lastn, utilizatori.firstn, utilizatori.uadmin FROM cv, utilizatori");

            ResultSet rs = pSt.executeQuery();

            while (rs.next()) {
                System.out.println(rs.getString("firstn"));
                if (rs.getLong("idutilizator") == rs.getLong("id")) {
                    System.out.println("inside if");
                    Person p = new Person();
                    p.date = rs.getString("udate");
                    p.lastn = rs.getString("lastn");
                    p.firstn = rs.getString("firstn");
                    p.setQ1(rs.getString("question1"));
                    p.setQ2(rs.getString("question2"));
                    p.setQ3(rs.getString("question3"));
                    p.setQ4(rs.getString("question4"));
                    p.setQ5(rs.getString("question5"));
                    p.setQ6(rs.getString("question6"));
                    p.setIdcv(rs.getLong("id"));
                    p.setIsAdmin(rs.getLong("uadmin"));

                    PersonList.add(p);
                }
            }

            rs.close();
            pSt.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return PersonList;
    }


    static void updateCV(long id, String q1, String q2, String q3, String q4, String q5, String q6){
        try {
            Class.forName("org.postgresql.Driver");

            final String URL = "jdbc:postgresql://54.93.65.5:5432/flavius8";
            final String USERNAME = "fasttrackit_dev";
            final String PASSWORD = "fasttrackit_dev";

            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            PreparedStatement pSt = conn.prepareStatement("UPDATE cv SET question1=?, question2=?, question3=?, question4=?, question5=?, question6=? WHERE id=? ");
            pSt.setString(1, q1);
            pSt.setString(2, q2);
            pSt.setString(3, q3);
            pSt.setString(4, q4);
            pSt.setString(5, q5);
            pSt.setString(6, q6);
            pSt.setLong(7, id);

            pSt.executeUpdate();

            pSt.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static long hasCV(Object o){
        long idcv = -1;

        long convertedLong = toLong(o);

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
                if(rs.getLong("idutilizator") == convertedLong) idcv = rs.getLong("idutilizator");
            }

            rs.close();
            pSt.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return idcv;
    }


    static void returnJsonResponse(HttpServletResponse response, String jsonResponse) {
        response.setContentType("application/json");
        PrintWriter pr = null;
        try {
            pr = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert pr != null;
        pr.write(jsonResponse);
        pr.close();
    }


    public static void listActionFeedback(HttpServletRequest request, HttpServletResponse response) {

        System.out.println("list action");
        HttpSession session = request.getSession(true);
        long id = DataBaseMethods.toLong(session.getAttribute("userid"));
        String order = request.getParameter("order");

        List PersonList;

        if(admin(id)){
            PersonList = getFeedback();
        } else{
            PersonList = getFeedback(id);
        }

        JSONObject json = new JSONObject();
        try {
            json.put("feedbacks", PersonList);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        returnJsonResponse(response, json.toString());
        System.out.println("end list action");
    }


    public static void listActionCV(HttpServletRequest request, HttpServletResponse response) {

        System.out.println("list action");
        HttpSession session = request.getSession(true);
        long id = toLong(session.getAttribute("userid"));
        String order = request.getParameter("order");

        List PersonList;

        if(admin(id)){
            PersonList = getCV();
        } else {
            PersonList  = getCV(id);
        }

        JSONObject json = new JSONObject();
        try {
            json.put("cvs", PersonList);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        returnJsonResponse(response, json.toString());
        System.out.println("end list action");
    }

    static long getIDbyEmail(String email){
        long id = 0;
        try {
            Class.forName("org.postgresql.Driver");

            final String URL = "jdbc:postgresql://54.93.65.5:5432/flavius8";
            final String USERNAME = "fasttrackit_dev";
            final String PASSWORD = "fasttrackit_dev";

            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            PreparedStatement pSt = conn.prepareStatement("SELECT id FROM utilizatori WHERE email=?");
            pSt.setString(1, email);

            ResultSet rs = pSt.executeQuery();

            while (rs.next()){
                id = rs.getLong("id");
            }

            pSt.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return id;
    }

    static boolean isVerified(long id){
        boolean verified = false;

        try {
            Class.forName("org.postgresql.Driver");

            final String URL = "jdbc:postgresql://54.93.65.5:5432/flavius8";
            final String USERNAME = "fasttrackit_dev";
            final String PASSWORD = "fasttrackit_dev";

            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            PreparedStatement pSt = conn.prepareStatement("SELECT verified FROM utilizatori WHERE id=?");
            pSt.setLong(1, id);

            ResultSet rs = pSt.executeQuery();

            while (rs.next()){
                verified = true;
            }

            rs.close();
            pSt.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return verified;
    }

    static void verify(String email){
        try {
            Class.forName("org.postgresql.Driver");

            final String URL = "jdbc:postgresql://54.93.65.5:5432/flavius8";
            final String USERNAME = "fasttrackit_dev";
            final String PASSWORD = "fasttrackit_dev";

            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            PreparedStatement pSt = conn.prepareStatement("UPDATE utilizatori SET verified=1 WHERE email=? ");
            pSt.setString(1, email);

            pSt.executeUpdate();

            pSt.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    static long toLong(Object o){
        String stringToConvert = String.valueOf(o);
        Long convertedLong = Long.parseLong(stringToConvert);
        return convertedLong;
    }
}
