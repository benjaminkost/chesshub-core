package Servlets;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import static Servlets.LoginServlet.session;
import static gameManagement.GameManagement.PGN;

import java.io.*;

import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class PGNServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		try {
			request.setAttribute("message", PGN(new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request),
					(String) session.getAttribute("color"), (int) session.getAttribute("userId")));
			request.getRequestDispatcher("FileUploadResult.jsp").forward(request, response);
		} catch (FileUploadException e) {
		}

	}

}
