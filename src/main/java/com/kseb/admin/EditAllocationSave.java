package com.kseb.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kseb.DBConnection;

@WebServlet("/editAllocationSave")
public class EditAllocationSave extends HttpServlet{

	/**
	 * @author ARUN
	 */
	private static final long serialVersionUID = 1L;

	Connection con=null;
	PreparedStatement pst=null;
	DBConnection dbCon = new DBConnection();
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
	try {
		response.setContentType("text/html");
		
		con=dbCon.getConnection();
		PrintWriter out = response.getWriter();
		
		int allocationID = Integer.parseInt(request.getParameter("allocationID"));
		int lineManID = Integer.parseInt(request.getParameter("lineManID"));
		
		out.print("<html>");
		out.print("<head><link rel=\"stylesheet\" type=\"text/css\" href=\"css/addComplaint.css\" /></head>");
		out.print("<body>");	
		
		pst=con.prepareStatement("update work_allocation set staff_id='"+lineManID+"' where work_alloc_id='"+allocationID+"'");
		int result=pst.executeUpdate();
		
		if(result>0) {
			out.print("<h1>Work Allocation Edited</h1>");
		}
		
		out.print("</body>");
		out.print("</html>");
	    
	} catch (SQLException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}

	}
}
