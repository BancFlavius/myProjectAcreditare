import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/signout")
public class SignOut extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String back = "login.jsp";
        HttpSession session = req.getSession();
        session.removeAttribute("userid");
        session.removeAttribute("verified");
        resp.sendRedirect(back);
    }
}
