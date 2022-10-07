import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Validation extends HttpServlet 
{
public void service(HttpServletRequest req,HttpServletResponse res)
{
try
{

String regno=req.getParameter("reg");
String pass=req.getParameter("pass");
	 
if(regno.length()!=10 || pass.length()==0)
{
res.sendRedirect("/Universityresults/invalid.html");
}
else
{
req.getServletContext().getRequestDispatcher("/GetResult").forward(req,res);
}
}
catch(Exception e)
{
	e.printStackTrace();
}
}
}
