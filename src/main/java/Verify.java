import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/verify")
public class Verify extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) {
        String action = req.getParameter("action");
        String email = req.getParameter("email");
        DataBaseMethods dbm = new DataBaseMethods();
        HttpSession session = req.getSession();

        if(action!=null && action.equals("verify")){
            if(dbm.isVerified(email)){
                try {
                    resp.sendRedirect("login.jsp");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                dbm.verify(email);
                long value = dbm.getIDbyEmail(email);
                System.out.println(value);
                session.setAttribute("userid", value);
                session.setAttribute("verified", 1);
                try {
                    resp.sendRedirect("home.jsp");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
