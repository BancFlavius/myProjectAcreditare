import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/signup")
public class SignUp extends HttpServlet {
    static String action;
    static String name;
    static String password;
    static String email;
    static String age;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) {

        try {
            action = req.getParameter("action");
            name = req.getParameter("name");
            password = req.getParameter("password");
            email = req.getParameter("email");
            age = req.getParameter("age");
            PrintWriter out = resp.getWriter();

            if(action!=null && action.equals("register")){
                if(!isEmpty(name) && !isEmpty(password) && !isEmpty(email) && !isEmpty(age)) {
                    DataBaseMethods dbm = new DataBaseMethods();
                    if(dbm.exists(email)) {
                        out.println("Please choose another email");
                    } else {
                        dbm.register(name, password, email, "1998-08-27");
                        System.out.println("creating new user");
                        out.println("Account registered.");
                    }
                } else  { out.println("Please fill all the fields to continue."); }
            }
            else if (action!=null && action.equals("login")){
                if (!isEmpty(email) || !isEmpty(password)){
                    DataBaseMethods dbm = new DataBaseMethods();
                    long value = dbm.login(email, password);
                    if(value!=-1){
                        System.out.println("logging in");
                        out.println("You logged in successfully");

                        HttpSession session = req.getSession();
                        session.setAttribute("userid", value);
                        resp.sendRedirect("feedback.jsp");
                    } else {
                        out.println("Log in failed.");
                    }
                } else { out.println("Please fill all the fields to continue."); }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected static int getEmailAndPassword(){
        int value = (int)DataBaseMethods.login(email, password);
        return value;
    }

    private static boolean isEmpty(String str){
        boolean empty = false;
        if(str !=null) {
            if (str.equals("") || str.equals(" ")) empty =  true;
        }
        return empty;
    }
}
