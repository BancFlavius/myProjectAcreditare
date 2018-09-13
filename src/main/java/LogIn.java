import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LogIn extends HttpServlet {
    static String action;
    static String email;
    static String password;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) {

        try {
            action = req.getParameter("action");
            password = req.getParameter("password");
            email = req.getParameter("email");
            System.out.println("trying login method");

            if (action!=null && action.equals("login")) {
                if (!isEmpty(email) || !isEmpty(password)) {
                    DataBaseMethods dbm = new DataBaseMethods();
                    long value = dbm.login(email, password);

                    if (value != -1) {
                        if(dbm.admin((int)value)) {
                            System.out.println("admin logging in");

                            HttpSession session = req.getSession();
                            session.setAttribute("userid", value);

                            resp.sendRedirect("adminhome.jsp");
                        } else {
                            System.out.println("logging in");

                            HttpSession session = req.getSession();
                            session.setAttribute("userid", value);

                            if(dbm.isVerified(value)) session.setAttribute("verified", 1);
                            resp.sendRedirect("home.jsp");
                        }

                    } else {
                        System.out.println("LoginServlet: user/pwd NOT FOUND in db, retry a new one on the UI ");
                        String back = "/login.jsp";
                        HttpSession session = req.getSession();
                        session.removeAttribute("userid");
                        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(back);
                        dispatcher.forward(req, resp);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

    private static boolean isEmpty(String str){
        boolean empty = false;
        if(str !=null) {
            if (str.equals("") || str.equals(" ")) empty =  true;
        }
        return empty;
    }
}
