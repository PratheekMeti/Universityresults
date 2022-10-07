

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class PrintPercentage extends HttpServlet 
{
HttpSession hs=null;
PrintWriter pw=null;

public void service(HttpServletRequest req, HttpServletResponse res)
{
try
{
pw=res.getWriter();
hs=req.getSession();
int m1=(int)hs.getAttribute("marks1");
int m2=(int)hs.getAttribute("marks2");
int m3=(int)hs.getAttribute("marks3");
float per=((float)(m1+m2+m3)/300)*100;
pw.println("The Percentage is:"+per);
}
catch(Exception e)
{
e.printStackTrace();
}
}
}
