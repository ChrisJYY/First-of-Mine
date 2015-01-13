package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pojo.Users;
import service.UserService;
import service.impl.UserServiseImpl;

public class UserServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try{
		UserService userService=new UserServiseImpl();
		List userList=userService.getStudentList();
		
		toUsers(resp,userList);
		}catch(Exception e){
			toError(resp,e.getMessage());
		}
	}

	private void toError(HttpServletResponse resp, String message) throws IOException{
		// TODO Auto-generated method stub
		PrintWriter out = resp.getWriter();
		
		out.println("<html>");
		out.println("<head>");
		out.println("	<title>Error</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("	<h2 align=\"center\">Error</h2>");
		out.println("	<hr>");
		out.println("	System Error," + message + "!");
		out.println("</body>");
		out.println("</html>");
		
		out.close();
	}

	private void toUsers(HttpServletResponse resp, List userList) throws IOException{
		// TODO Auto-generated method stub
PrintWriter out = resp.getWriter();
		
		out.println("<html>");
		out.println("<head>");
		out.println("	<title>User List</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("	<h2 align=\"center\">Student List</h2>");
		out.println("	<hr>");
		out.println("	<center>");
		out.println("		<table border=\"1\">");
		out.println("			<tr>");
		out.println("				<th>USERID</th>");
		out.println("				<th>PASSWORD</th>");
		
		out.println("			</tr>");

		for(Iterator<Users> it = userList.iterator();it.hasNext();){
			Users user = it.next();
			
			out.println("			<tr>");
			out.println("				<td>" + user.getUserid() + "</td>");
			out.println("				<td>" + user.getPassword() + "</td>");
			
			out.println("			</tr>");
		}
		
		out.println("		</table>");
		out.println("	</center>");
		out.println("</body>");
		out.println("</html>");
		
		out.close();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
	}

}
