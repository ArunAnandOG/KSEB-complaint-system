package com.kseb.material;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kseb.DBConnection;

@WebServlet("/approveRequestMaterial")
public class ApproveRequestMaterial extends HttpServlet{

	/**
	 * @author ARUN
	 */
	private static final long serialVersionUID = 1L;

	Connection con=null;
	PreparedStatement pst=null;
	DBConnection dbCon = new DBConnection();
	RequestDispatcher dispatcher=null;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response){
	try {
		response.setContentType("text/html");
		
		HttpSession session = request.getSession();
		String message = session.getAttribute("username").toString();
		
		con=dbCon.getConnection();
		PrintWriter out = response.getWriter();
		
		int requestID = Integer.parseInt(request.getParameter("requestID"));
		Date date = new Date( );
	    SimpleDateFormat ft = new SimpleDateFormat ("yyyy.MM.dd");

		pst=con.prepareStatement("update material_request_status set material_request_status_updated_by=? , material_request_status_updated_date=? , material_request_status_details='waiting for approval from ee' where fk_material_request_id=?");
		pst.setString(1, message);
		pst.setString(2, ft.format(date));
		pst.setInt(3, requestID);
		int result=pst.executeUpdate();
		
		if(result>0) {
			dispatcher=request.getRequestDispatcher("materialRequests");
			dispatcher.forward(request, response);
		}
		else {
			out.print("<html>");
			out.print("<head><link rel=\"stylesheet\" type=\"text/css\" href=\"css/addComplaint.css\" /></head>");
			out.print("<body>");	
			out.print("<h1>ERROR\nREQUEST NOT APPROVED</h1>");
			out.print("</body>");
			out.print("</html>");
		}

	} catch (SQLException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	} catch (ServletException e) {
		e.printStackTrace();
	}

	}
}
