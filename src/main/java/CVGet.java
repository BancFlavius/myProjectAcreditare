import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/cvget")
public class CVGet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        String action = req.getParameter("action");
        final String LIST = "list";

        try {
            if (action != null && action.equals(LIST)) {
                DataBaseMethods.listActionCV(req, resp);
                resp.sendRedirect("cv.jsp");
            } else {
                resp.sendRedirect("cv.jsp");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
