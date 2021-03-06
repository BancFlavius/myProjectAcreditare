import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/feedbackget")
public class FeedbackGet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        String action = req.getParameter("action");
        final String LIST = "list";

            if (action != null && action.equals(LIST)) {
                DataBaseMethods.listActionFeedback(req, resp);
            }
    }
}
