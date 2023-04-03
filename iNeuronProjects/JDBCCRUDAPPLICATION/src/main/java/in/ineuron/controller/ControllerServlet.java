package in.ineuron.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.ineuron.dto.Student;
import in.ineuron.factory.StudentServiceFactory;
import in.ineuron.service.IStudentService;

@WebServlet("/controller/*")
public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	private void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String requestURI = request.getRequestURI();
		System.out.println(requestURI);
		RequestDispatcher requestDispatcher = null;
		Student student = new Student();
		String status=null;
		IStudentService stdSerImpl = StudentServiceFactory.getStudentConnection();
		if (requestURI.endsWith("layout")) {
			requestDispatcher = request.getRequestDispatcher("../Layout.html");
			requestDispatcher.forward(request, response);
		}
		if (requestURI.endsWith("AddForm")) {
			String sname = request.getParameter("sname");
			String sage = request.getParameter("sage");
			String sadd = request.getParameter("sadd");
			student.setSname(sname);
			student.setSage(sage);
			student.setSadd(sadd);
			if ( student != null/*student.getSid() != null || student.getSname() 
					!= null || student.getSage() != null || student.getSadd() != null*/) {
				status = stdSerImpl.save(student);

				if (status.equalsIgnoreCase("success")) {
					requestDispatcher = request.getRequestDispatcher("../success.html");
					requestDispatcher.forward(request, response);
				}
				if (status.equalsIgnoreCase("failure")) {
					request.getRequestDispatcher("../failure.html");
				}
			}
			else {
				requestDispatcher=request.getRequestDispatcher("../failure.html");
				requestDispatcher.forward(request, response);
			}
		}
		if (requestURI.endsWith("SelectForm")) {
			String sid = request.getParameter("sid");
			student = stdSerImpl.findById(sid);
			if (student.getSid() != null) {
				PrintWriter out = response.getWriter();
				out.println("<html><head></head>");
				out.println("<body>");
				out.println("<br/><br/><br/><br/>");
				out.println("<h2 align='center'>STUDENT DETAILS:</h2>");
				out.println("<table align='center' border='1px'>");
				out.println("<tr>");
				out.println("<th>Student ID : </th><td>" + student.getSid() + "</td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<th>Student Name : </th><td>" + student.getSname() + "</td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<th>Student Age : </th><td>" + student.getSage() + "</td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<th>Student Address : </th><td>" + student.getSadd() + "</td>");
				out.println("</tr>");
				out.println("<table>");
				out.println("</body>");
				out.println("</html>");
			} else {
				requestDispatcher = request.getRequestDispatcher("../Nodata.html");
				requestDispatcher.forward(request, response);
			}
		}
		if (requestURI.endsWith("EditForm")) {
			String sid = request.getParameter("sid");
			student = stdSerImpl.findById(sid);
			if (student.getSid() != null) {
				PrintWriter out = response.getWriter();
				out.println("<html><head></head>");
				out.println("<body>");
				out.println("<form method='post' action='./UpdateForm'>");
				out.println("<center>");
				out.println("<br/><br/><br/>");
				out.println("<h4>EDIT FORM</h4>");
				out.println("<table>");
				out.println("<tr><th>SID</th><td>" + student.getSid() + "</td></tr>");
				out.println("<input type='hidden' name='sid' value='"+student.getSid()+"'/>");
				out.println("<tr><th>SNAME</th><td><input type='text' name='sname' value='" + student.getSname()
						+ "'/></td></tr>");
				out.println("<tr><th>SAGE</th><td><input type='text' name='sage' value='" + student.getSage()
						+ "'/></td></tr>");
				out.println("<tr><th>SADD</th><td><input type='text' name='sadd' value='" + student.getSadd()
						+ "'/></td></tr>");
				out.println("<tr><th></th><td><input type='submit' value='submit'></td></tr>");
				out.println("</table>");
				out.println("<center>");
				out.println("</html>");
				out.close();
			} else {
				requestDispatcher = request.getRequestDispatcher("../DeleteFormFailed.html");
				requestDispatcher.forward(request, response);
			}

		}
		if (requestURI.endsWith("UpdateForm")) {
			System.out.println("inside update form");
			String sid = request.getParameter("sid");
			String sname = request.getParameter("sname");
			String sage = request.getParameter("sage");
			String sadd = request.getParameter("sadd");
			student.setSid(sid);
			student.setSname(sname);
			student.setSage(sage);
			student.setSadd(sadd);
			status = stdSerImpl.updateById(student);
			if (status.equalsIgnoreCase("success")) {
				requestDispatcher = request.getRequestDispatcher("../successUpdate.html");
				requestDispatcher.forward(request, response);
			} else if (status.equalsIgnoreCase("failed")) {
				requestDispatcher = request.getRequestDispatcher("../failedUpdate.html");
				requestDispatcher.forward(request, response);
			}
		}
		if (requestURI.endsWith("DeleteForm")) {
			String sid = request.getParameter("sid");
			status = stdSerImpl.deleteById(sid);
			if (status.equalsIgnoreCase("success")) {
				requestDispatcher = request.getRequestDispatcher("../DeleteFormSuccess.html");
				requestDispatcher.forward(request, response);
			} else if (status.equalsIgnoreCase("recordNotFound")) {
				requestDispatcher = request.getRequestDispatcher("../DeleteFormFailed.html");
				requestDispatcher.forward(request, response);
			} else if (status.equalsIgnoreCase("failed")) {
				requestDispatcher = request.getRequestDispatcher("../DeleteFormIllegal.html");
				requestDispatcher.forward(request, response);
			}
		}

	}

}
