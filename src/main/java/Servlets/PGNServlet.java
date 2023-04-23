package Servlets;

import BusinessObjects.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import static Servlets.LoginServlet.session;

import java.io.*;
import PGNReader.*;
import dao.*;

import java.util.*;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class PGNServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);

        try {
            // Parse the request
            List<FileItem> items = upload.parseRequest(request);

            // Process the uploaded items
            for (FileItem item : items) {
                // If the item is a file
                if (!item.isFormField()) {
                    // Get the file name
                    String fileName = item.getName();
                    request.setAttribute("fileName", fileName);

                    // Save the file
                    File uploadedFile = new File(fileName);
                    item.write(uploadedFile);

                    // Call the parseFile method of PGNReader class
                    PGNReader pgnReader = new PGNReader();

                    Game parsedFile = pgnReader.parseFile(uploadedFile);

                    // Set the parsed file as a request attribute
                    request.setAttribute("parsedFile", parsedFile);

                    //Color 
                    if(session.getAttribute("color").equals("White")) { 
                    	parsedFile.setWhite(UserDao.getInstance().getUserById((int)session.getAttribute("userId")));
                    	parsedFile.setBlack(UserDao.getInstance().getUserById(0));
                    	parsedFile.setComment(parsedFile.getCommentBlack());
                    }
                    else {
                    	parsedFile.setBlack(UserDao.getInstance().getUserById((int)session.getAttribute("userId")));
                    	parsedFile.setWhite(UserDao.getInstance().getUserById(0));
                    	parsedFile.setComment(parsedFile.getCommentWhite());
                    }

                    //safe game with DAO in Database
                    GameDao.getInstance().insertGame(parsedFile);

                    // Set the parsed file as a request attribute
                    request.setAttribute("parsedFile", parsedFile);


                    // Forward to the result page
                    request.getRequestDispatcher("PGNSuccessfulUpload.jsp").forward(request, response);

                    return;
                }
            }

            // Display an error message if no file was uploaded
            response.getWriter().println("No file uploaded.");
        } catch (Exception e) {
            // Handle errors
            response.getWriter().println("Error: " + e.getMessage());
        }

    }

}
