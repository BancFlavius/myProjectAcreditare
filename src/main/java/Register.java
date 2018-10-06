import javax.mail.MessagingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/register")
public class Register extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {

        try {
            String action = req.getParameter("action");
            String first = req.getParameter("first");
            String last = req.getParameter("last");
            String password = req.getParameter("password");
            String password2 = req.getParameter("password2");
            String email = req.getParameter("email");
            String age = req.getParameter("age");
            HttpSession session = req.getSession();

            if(action!=null && action.equals("register")){
                if(!isEmpty(first) || !isEmpty(last) || !isEmpty(password) || !isEmpty(email) || !isEmpty(age)) {
                    DataBaseMethods dbm = new DataBaseMethods();
                    if(dbm.exists(email)) {
                        System.out.println("RegisterServlet: email already exists");
                        String back = "/register.jsp";
                        session.removeAttribute("userid");
                        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(back);
                        dispatcher.forward(req, resp);
                    } else if (!isEmpty(password) || !isEmpty(password2) || password.equals(password2)){
                        dbm.register(first, last, password, email, age);
                        System.out.println("creating new user");
                        dbm.login(email, password);
                        session.setAttribute("verified", null);


                            Runnable runnable = new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        EmailSender.send(email, "Welcome", "Welcome to our app. Please verify your account: http://localhost:8080/verify?action=verify&email="+email+"&key="+dbm.getKey(email));
                                    } catch (MessagingException e) {
                                        e.printStackTrace();
                                    }
                                }
                            };

                            runnable.run();

                        resp.sendRedirect("login.jsp");
                    }
                } else  { System.out.println("RegisterServlet: registration failed");
                    String back = "/register.jsp";
                    session.removeAttribute("userid");
                    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(back);
                    dispatcher.forward(req, resp);
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
