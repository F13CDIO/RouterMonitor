package logbean;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import server.data.mySQLConnector.MySQLConnector;

public class connect extends HttpServlet{


	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private HttpSession session;
	private MySQLConnector con;
	private String query;
	
	private void setCon()
	{
		try 
		{
//			con = new MySQLConnector();
			MySQLConnector.connect();
		} 
		catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) 
		{
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException
	{
		this.request = req;
		session = req.getSession();
		setCon();
		RequestDispatcher dispatcher;
		if (areCredentialsValid(request))
		{
			session.setAttribute("username", request.getParameter("username"));
			System.out.println("session.getAttribute(\"username\")" + session.getAttribute("username"));
			dispatcher = req.getRequestDispatcher("home.jsp"); 
		
			dispatcher.forward(req, resp);
		}
		else
		{
			dispatcher = req.getRequestDispatcher("index.jsp"); 
			dispatcher.forward(req, resp);
		}
		// Redirects to home if 	
//		System.out.println("doPOST = "+request.getParameter("username"));
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException{
		System.out.println("doGET = "+req.getAttributeNames());
		this.request = req;
	}

	private boolean areCredentialsValid(HttpServletRequest request)
	{
		//query returns 1 if there is a record in the database with the provided username and password
		query = "Select 1 FROM login where username = \"" + request.getParameter("username") + "\" AND password = \"" + request.getParameter("password") + "\"";
		
		ResultSet rs;
		try 
		{
			rs = MySQLConnector.execQuery(query);
			
//			System.out.println("rs.next()" + rs.next());
			//if beforeFirst() = true, list is empty, therefore no valid string in db mathches query
			return (rs.next());//returns false if rs is empty, true if rs contains 1. (if rs is 1 it means database contains credentials)
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return false;
		
	}
}
