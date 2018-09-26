import javax.mail.MessagingException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/contact")
public class Contact extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res){
        String action = req.getParameter("action");
        String email = req.getParameter("email");
        String message = req.getParameter("message");

        if(action.equals("cv")) {
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        EmailSender.send(email, "In Regards with your CV", "Thank you for sending your CV.\r\n\n\n"+ message +"\r\n\n\n Best regards,\r\n Our team.");
                    } catch (MessagingException e) {
                        e.printStackTrace();
                    }
                }
            };

            runnable.run();
            try {
                res.sendRedirect("admincv.jsp");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if(action.equals("feedback")){
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        EmailSender.send(email, "In Regards with your Feedback", "Thank you for your feedback.\r\n\n\n"+ message +"\r\n\n\n Best regards,\r\n Our team.");
                    } catch (MessagingException e) {
                        e.printStackTrace();
                    }
                }
            };
            runnable.run();
            try {
                res.sendRedirect("adminfeedback.jsp");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
