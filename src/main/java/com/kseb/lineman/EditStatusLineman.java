package com.kseb.lineman;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/editStatusLineman")
public class EditStatusLineman extends HttpServlet{

	/**
	 * @author ARUN
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			response.setContentType("text/html");

			PrintWriter out = response.getWriter();

			int complaintID = Integer.parseInt(request.getParameter("complaintID"));

			out.print("<html>");
			out.print("<head><link rel=\"stylesheet\" type=\"text/css\" href=\"css/addComplaint.css\" /></head>");
			out.print("<body>");
			out.print("<div class='add-complaint-box'> ");
			out.print(" <h2>Edit Complaint</h2> ");
			out.print(" <form method='post' action='editStatusLinemanSave'>");
			out.print(" <div class='user-box'> ");
			out.print(" <input type='number' name='complaintID' required='' id='complaintID' readonly='readonly' value='"+ complaintID + "'>");
			out.print(" <label class='selectLabel'>Complaint ID</label> ");
			out.print(" </div> ");
			out.print(" <div class='user-box'> ");
			out.print(" <select name='status' id='status'>");
			out.print(" <option value='Pending'>Pending</option>");
			out.print(" <option value='Resolved'>Resolved</option>");
			out.print(" </select>");
			out.print(" <label class='selectLabel'>Status Details</label>");
			out.print(" </div> ");			
			out.print(" <div class='wrapper'>");
			out.print(" <input type='submit' value='EDIT STATUS' id='submit' />");
			out.print(" <span></span> <span></span> <span></span> <span></span> <span></span>");
			out.print(" </div> ");
			out.print(" </form>");
			out.print("</div>");
			out.print("</body>");
			out.print("</html>");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
