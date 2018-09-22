import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/feedback")
public class Feedback extends HttpServlet {


    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp){
        String message = req.getParameter("message");
        String type = req.getParameter("utype");
        String action = req.getParameter("action");

        try {
            if(!isEmpty(message) && action.equals("list")) {
                DataBaseMethods dbm = new DataBaseMethods();
                HttpSession session = req.getSession();
                Object o = session.getAttribute("userid");
                if (o != null) {
                    dbm.feedback(dbm.toLong(o), message, type);
                    System.out.println("feedback sent");
                    resp.sendRedirect("feedback.jsp");
                } else {
                    System.out.println("FeedbackServlet: object is null, redirecting");
                    resp.sendRedirect("feedback.jsp");
                }
            } else if(action.equals("delete")){
                deleteAction(req, resp);
            } else{
                System.out.println("FeedbackServlet: message is null");
                resp.sendRedirect("feedback.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void deleteAction(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession(true);

        String idS = request.getParameter("idfeedback");
        int id = Integer.parseInt(idS);

        DataBaseMethods.deleteFeedback(id);
        try {
            response.sendRedirect("feedback.jsp");
        } catch (IOException e) {
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
