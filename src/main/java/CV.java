import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/cv")
public class CV extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp){
        String q1 = req.getParameter("question1");
        String q2 = req.getParameter("question2");
        String q3 = req.getParameter("question3");
        String q4 = req.getParameter("question4");
        String q5 = req.getParameter("question5");
        String q6 = req.getParameter("question6");
        String action = req.getParameter("action");
        final String UPDATE = "update";

        DataBaseMethods dbm = new DataBaseMethods();
        HttpSession session = req.getSession();
        Object o = session.getAttribute("userid");

        try {
            if(o==null) {
                System.out.println("CVServlet: object is null, redirecting");
                resp.sendRedirect("cv.jsp");
            } else if(action!=null && action.equals("delete")){
                deleteAction(req,resp);
            } else {
                if(action!= null && action.equals(UPDATE)){
                    dbm.updateCV(dbm.hasCV(o), q1, q2, q3, q4, q5, q6);
                } else {
                    dbm.cv(dbm.toLong(o), q1, q2, q3, q4, q5, q6);
                    System.out.println("cv sent");
                    resp.sendRedirect("cv.jsp");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void deleteAction(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession(true);

        String idS = request.getParameter("iduser");
        int id = Integer.parseInt(idS);

        DataBaseMethods.deleteUser(id);
        try {
            response.sendRedirect("admincv.jsp");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
