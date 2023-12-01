package com.kseb.lineman;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kseb.DBConnection;

@WebServlet("/editStatusLinemanSave")
public class EditStatusLinemanSave extends HttpServlet{

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
		
		HttpSession session = request.getSession();
		String message = session.getAttribute("username").toString();
		
		con=dbCon.getConnection();
		PrintWriter out = response.getWriter();
		
		int complaintID = Integer.parseInt(request.getParameter("complaintID"));
		String status = request.getParameter("status");
		
		Date date = new Date( );
	    SimpleDateFormat ft = new SimpleDateFormat ("yyyy.MM.dd");
		
		out.print("<html>");
		out.print("<head><link rel=\"stylesheet\" type=\"text/css\" href=\"css/addComplaint.css\" /></head>");
		out.print("<body>");	
		
		pst=con.prepareStatement("update complaint_status set complaint_status=? , complaint_status_updated_by=? , complaint_status_details='Work Started' , complaint_status_updated_date=? where complaint_id_status=?");
		pst.setString(1, status);
		pst.setString(2, message);
		pst.setString(3, ft.format(date));
		pst.setInt(4, complaintID);
		int result=pst.executeUpdate();
		
		if(result>0) {
			out.print("<h1>Complaint Edited</h1>");
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
