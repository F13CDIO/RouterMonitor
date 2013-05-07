package logbean;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class connect extends HttpServlet {


	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		
			System.out.println("doPOST    ="+request.getParameter("username"));
		
		
		this.request = request;
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		System.out.println("doGET ="+request.getAttributeNames());
		this.request = request;
	}
}
