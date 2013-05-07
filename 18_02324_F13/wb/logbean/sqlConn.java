package logbean;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class sqlConn extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private HttpServletRequest request;

	private LoginBean lb;

	String URL = "jdbc:mysql://bjqrn88.cloudns.org:3306/login";
	String Driver = "com.mysql.jdbc.Driver";
	String username = "grp18";
	String password = "123123";
	String name = null;
	String status = "";
	Connection conn = null;
	Statement statement = null;
	ResultSet rs = null;

	public sqlConn()
	{

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		
			System.out.println("doPOST ="+request.getAttributeNames());
		
		
		this.request = request;
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		System.out.println("doGET ="+request.getAttributeNames());
		this.request = request;
	}

	public void connect()
	{


		try {
			Class.forName(Driver);
			conn = DriverManager.getConnection(URL, username, password);
			if (!conn.isClosed()) {

				statement = conn.createStatement();
				System.out.println("1");
				System.out.println(request.getAttributeNames());
				
				System.out.println("2");
				
				lb = (LoginBean) (request.getSession().getAttribute("login"));

				System.out.println("3");

				System.out.println("SQL checker username i loginbean er "+lb.getUsername());
				System.out.println("SQL checker password i loginbean er"+lb.getPassword());

				rs = statement.executeQuery("SELECT username FROM login WHERE" +
						" username ='"+ lb.getUsername() +"' and password='"+lb.getPassword()+"'");

				//name = rs.getString(1);
				while(rs.next())
				{
					name = rs.getString("username");
				}

				rs.close();
				conn.close();

			}
		} 
		catch (Exception e) 
		{
			System.out.println(e.getMessage());
			//out.println(e.getMessage());
		}

	}

	public boolean valid()
	{
		connect();
		if(name == null){
			return false;
		}else{
			return true;
		}
	}

	public String req()
	{
		connect();
		return name;
	}
}