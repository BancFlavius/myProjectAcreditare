import java.sql.*;

public class DataBaseMethods {
    static void register(String name, String password, String email, String age){

        try {
            Class.forName("org.postgresql.Driver");

            final String URL = "jdbc:postgresql://54.93.65.5:5432/flavius8";
            final String USERNAME = "fasttrackit_dev";
            final String PASSWORD = "fasttrackit_dev";

            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            PreparedStatement pSt = conn.prepareStatement("INSERT INTO utilizatori(uname, upassword, email, age, uadmin) VALUES (?,?,?,Cast(? as date), 0)");
            pSt.setString(1, name);
            pSt.setString(2, password);
            pSt.setString(3, email);
            pSt.setString(4, age);

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

    static void feedback(long id, String mesaj){
        try {
            Class.forName("org.postgresql.Driver");

            final String URL = "jdbc:postgresql://54.93.65.5:5432/flavius8";
            final String USERNAME = "fasttrackit_dev";
            final String PASSWORD = "fasttrackit_dev";

            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            PreparedStatement pSt = conn.prepareStatement("INSERT INTO feedback(idutilizator, mesaj) VALUES (?,?) ");
            pSt.setLong(1, id);
            pSt.setString(2, mesaj);

            pSt.executeUpdate();

            pSt.close();
            conn.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
