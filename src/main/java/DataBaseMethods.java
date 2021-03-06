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
import java.util.UUID;


public class DataBaseMethods {
    static void register(String first, String last, String password, String email, String age){

        try {
            Class.forName("org.postgresql.Driver");

            final String URL = "jdbc:postgresql://54.93.65.5:5432/flavius8";
            final String USERNAME = "fasttrackit_dev";
            final String PASSWORD = "fasttrackit_dev";

            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            PreparedStatement pSt = conn.prepareStatement("INSERT INTO utilizatori(firstn, lastn, upassword, email, age, uadmin, verified, ukey) VALUES (?,?,?,?,Cast(? as date), 0, 0, ?)");
            pSt.setString(1, first);
            pSt.setString(2, last);
            pSt.setString(3, password);
            pSt.setString(4, email);
            pSt.setString(5, age);
            pSt.setString(6, generateKey());

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


    static boolean isAdmin(long id){
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

            PreparedStatement pSt = conn.prepareStatement("SELECT utilizatori.email, feedback.idf, feedback.idutilizator, feedback.mesaj, feedback.udate, utilizatori.lastn, utilizatori.firstn, feedback.utype FROM feedback,utilizatori WHERE utilizatori.id = ? AND feedback.idutilizator = ?");
            pSt.setLong(1, id);
            pSt.setLong(2, id);

            ResultSet rs = pSt.executeQuery();

            while (rs.next()){
                if(rs.getLong("idutilizator") == id){
                    String umessage = rs.getString("mesaj").trim();
                    String firstn = rs.getString("firstn").trim();
                    String lastn = rs.getString("lastn").trim();
                    String date = rs.getString("udate").trim();

                    Person p = new Person();
                    p.message = umessage;
                    p.firstn = firstn;
                    p.lastn = lastn;
                    p.date = date;
                    p.setIduser(rs.getLong("idutilizator"));
                    p.setIdfeedback(rs.getLong("idf"));
                    if(rs.getString("utype").trim().equals("suggestion")){
                        p.setFeedbackType(1);
                    } else if(rs.getString("utype").trim().equals("issue")){
                        p.setFeedbackType(0);
                    }
                    p.setEmail(rs.getString("email").trim());

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

            PreparedStatement pSt = conn.prepareStatement("SELECT utilizatori.email, utilizatori.id, feedback.idf, feedback.idutilizator, feedback.mesaj, feedback.udate, utilizatori.lastn, utilizatori.firstn, feedback.utype FROM feedback,utilizatori");

            ResultSet rs = pSt.executeQuery();

            while (rs.next()){
                if (rs.getLong("idutilizator") == rs.getLong("id")) {
                    Person p = new Person();

                    p.message = rs.getString("mesaj").trim();
                    p.firstn = rs.getString("firstn").trim();
                    p.lastn = rs.getString("lastn").trim();
                    p.date = rs.getString("udate").trim();
                    p.setIduser(rs.getLong("idutilizator"));
                    p.setIdfeedback(rs.getLong("idf"));
                    if(rs.getString("utype").trim().equals("suggestion")){
                        p.setFeedbackType(1);
                    } else if(rs.getString("utype").trim().equals("issue")){
                        p.setFeedbackType(0);
                    }
                    p.setEmail(rs.getString("email").trim());

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

            PreparedStatement pSt = conn.prepareStatement("SELECT utilizatori.email, cv.idc, cv.idutilizator, cv.question1, cv.question2, cv.question3, cv.question4, cv.question5, cv.question6, cv.udate, utilizatori.lastn, utilizatori.firstn, utilizatori.uadmin FROM cv, utilizatori WHERE utilizatori.id = ? AND cv.idutilizator=?");
            pSt.setLong(1, id);
            pSt.setLong(2, id);

            ResultSet rs = pSt.executeQuery();
            while (rs.next()) {
                if (rs.getLong("idutilizator") == id) {
                    Person p = new Person();
                    p.date = rs.getString("udate").trim();
                    p.lastn = rs.getString("lastn").trim();
                    p.firstn = rs.getString("firstn").trim();
                    p.q1 = rs.getString("question1").trim();
                    p.q2 = rs.getString("question2").trim();
                    p.q3 = rs.getString("question3").trim();
                    p.q4 = rs.getString("question4").trim();
                    p.q5 = rs.getString("question5").trim();
                    p.q6 = rs.getString("question6").trim();
                    p.setIduser(rs.getLong("idutilizator"));
                    p.setIdcv(rs.getLong("idc"));
                    p.setIsAdmin(rs.getLong("uadmin"));
                    p.setEmail(rs.getString("email").trim());

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

            PreparedStatement pSt = conn.prepareStatement("SELECT utilizatori.email, utilizatori.id, cv.idc, cv.idutilizator, cv.question1, cv.question2, cv.question3, cv.question4, cv.question5, cv.question6, cv.udate, utilizatori.lastn, utilizatori.firstn, utilizatori.uadmin FROM cv, utilizatori");

            ResultSet rs = pSt.executeQuery();

            while (rs.next()) {
                if (rs.getLong("idutilizator") == rs.getLong("id")) {
                    Person p = new Person();
                    p.date = rs.getString("udate").trim();
                    p.lastn = rs.getString("lastn").trim();
                    p.firstn = rs.getString("firstn").trim();
                    p.setQ1(rs.getString("question1").trim());
                    p.setQ2(rs.getString("question2").trim());
                    p.setQ3(rs.getString("question3").trim());
                    p.setQ4(rs.getString("question4").trim());
                    p.setQ5(rs.getString("question5").trim());
                    p.setQ6(rs.getString("question6").trim());
                    p.setIduser(rs.getLong("idutilizator"));
                    p.setIdcv(rs.getLong("idc"));
                    p.setIsAdmin(rs.getLong("uadmin"));
                    p.setEmail(rs.getString("email").trim());

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

            PreparedStatement pSt = conn.prepareStatement("UPDATE cv SET question1=?, question2=?, question3=?, question4=?, question5=?, question6=? WHERE idutilizator=? ");
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

        if(isAdmin(id)){
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

        if(isAdmin(id)){
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

    static boolean isVerified(String email){
        boolean verified = false;

        try {
            Class.forName("org.postgresql.Driver");

            final String URL = "jdbc:postgresql://54.93.65.5:5432/flavius8";
            final String USERNAME = "fasttrackit_dev";
            final String PASSWORD = "fasttrackit_dev";

            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            PreparedStatement pSt = conn.prepareStatement("SELECT verified FROM utilizatori WHERE email=?");
            pSt.setString(1, email);

            ResultSet rs = pSt.executeQuery();

            while (rs.next()){
                if(rs.getLong("verified") == 0){
                    System.out.println("user is not verified");
                } else {
                    verified = true;
                    System.out.println("user is verified");
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
        return verified;
    }

    static void verify(String email, String key){
        try {
            Class.forName("org.postgresql.Driver");

            final String URL = "jdbc:postgresql://54.93.65.5:5432/flavius8";
            final String USERNAME = "fasttrackit_dev";
            final String PASSWORD = "fasttrackit_dev";

            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            PreparedStatement pSt = conn.prepareStatement("UPDATE utilizatori SET verified=1 WHERE email=? AND ukey=?");
            pSt.setString(1, email);
            pSt.setString(2, key);

            pSt.executeUpdate();

            pSt.close();
            conn.close();
            System.out.println("user verified");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static void deleteFeedback(long id){
        try {
            Class.forName("org.postgresql.Driver");

            final String URL = "jdbc:postgresql://54.93.65.5:5432/flavius8";
            final String USERNAME = "fasttrackit_dev";
            final String PASSWORD = "fasttrackit_dev";

            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            PreparedStatement pSt = conn.prepareStatement("DELETE FROM feedback WHERE idf=?");
            pSt.setLong(1, id);

            pSt.executeUpdate();

            pSt.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    static void deleteUserFeedback(long id){
        try {
            Class.forName("org.postgresql.Driver");

            final String URL = "jdbc:postgresql://54.93.65.5:5432/flavius8";
            final String USERNAME = "fasttrackit_dev";
            final String PASSWORD = "fasttrackit_dev";

            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            PreparedStatement pSt = conn.prepareStatement("DELETE FROM feedback WHERE idutilizator=?");
            pSt.setLong(1, id);

            pSt.executeUpdate();

            pSt.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static void deleteCV(long id){
        try {
            Class.forName("org.postgresql.Driver");

            final String URL = "jdbc:postgresql://54.93.65.5:5432/flavius8";
            final String USERNAME = "fasttrackit_dev";
            final String PASSWORD = "fasttrackit_dev";

            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            PreparedStatement pSt = conn.prepareStatement("DELETE  FROM cv WHERE idutilizator=?");
            pSt.setLong(1, id);

            pSt.executeUpdate();

            pSt.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static void deleteUser(long id){
        deleteUserFeedback(id);
        deleteCV(id);
        try {
            Class.forName("org.postgresql.Driver");

            final String URL = "jdbc:postgresql://54.93.65.5:5432/flavius8";
            final String USERNAME = "fasttrackit_dev";
            final String PASSWORD = "fasttrackit_dev";

            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            PreparedStatement pSt = conn.prepareStatement("DELETE FROM utilizatori WHERE id=?");
            pSt.setLong(1, id);

            pSt.executeUpdate();

            pSt.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String generateKey() {
        String uuid = UUID.randomUUID().toString();
        return uuid.replaceAll("-","");
    }

    static String getKey(String email){
        String key = "";

        try {
            Class.forName("org.postgresql.Driver");

            final String URL = "jdbc:postgresql://54.93.65.5:5432/flavius8";
            final String USERNAME = "fasttrackit_dev";
            final String PASSWORD = "fasttrackit_dev";

            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            PreparedStatement pSt = conn.prepareStatement("SELECT ukey FROM utilizatori WHERE email=?");
            pSt.setString(1, email);

            ResultSet rs = pSt.executeQuery();

            while (rs.next()){
                key = rs.getString("ukey").trim();
            }

            rs.close();
            pSt.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return key;
    }

    static long toLong(Object o){
        String stringToConvert = String.valueOf(o);
        Long convertedLong = Long.parseLong(stringToConvert);
        return convertedLong;
    }
}
