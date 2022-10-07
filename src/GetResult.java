import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class GetResult extends HttpServlet 
{
String path="oracle.jdbc.driver.OracleDriver";
String url="jdbc:oracle:thin:@localhost:1521:XE";
String user="SYSTEM";
String password="SYSTEM";
Connection con=null;
PreparedStatement pstm=null;
ResultSet rs=null;
PrintWriter pw=null;
HttpSession hs=null;
String sql="Select * FROM VTU where regno=? and password=?"; 

public void init()
{
try
{
Class.forName(path);
System.out.println("Driver loaded sucesfully");

con=DriverManager.getConnection(url,user,password);
System.out.println("Connection establsihed sucesfully");
}
catch(Exception e)
{
e.printStackTrace();
}
}
public void service(HttpServletRequest req, HttpServletResponse res)
{
try
{
String regno=req.getParameter("reg");
String pass=req.getParameter("pass");

pstm=con.prepareStatement(sql);

pstm.setString(1,regno);
pstm.setString(2,pass);

rs=pstm.executeQuery();

pw=res.getWriter();

if(rs.next()==true)
{
	String name=rs.getString(1);
	String password=rs.getString(2);
	String reg=rs.getString(3);
	int marks1=rs.getInt(4);
	int marks2=rs.getInt(5);
	int marks3=rs.getInt(6);
	int avg=rs.getInt(7);
	
	hs=req.getSession(true);
	hs.setAttribute("marks1",marks1);
	hs.setAttribute("marks2",marks2);
	hs.setAttribute("marks3",marks3);
	pw.println(name+" "+password+" "+reg+" "+marks1+" "+marks2+" "+marks3+" "+avg);
}
else
{
res.sendRedirect("/Universityresults/incorrect.html");
}
req.getServletContext().getRequestDispatcher("/PrintPercentage").include(req,res);
System.out.println("Query is excuted sucessfully");
}
catch(Exception e)
{
e.printStackTrace();
}
}
public void destroy()
{
try
{
con.close();
pstm.close();
rs.close();
pw.close();
}
catch(Exception e)
{
e.printStackTrace();
}
}
}

