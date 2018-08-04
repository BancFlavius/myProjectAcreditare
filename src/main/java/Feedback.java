import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/feedback")
public class Feedback extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp){
        final String message = req.getParameter("message");

        if(!isEmpty(message)){
            DataBaseMethods.feedback(SignUp.getEmailAndPassword(), message);
            System.out.println(SignUp.getEmailAndPassword());
            System.out.println(SignUp.getEmailAndPassword());
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
