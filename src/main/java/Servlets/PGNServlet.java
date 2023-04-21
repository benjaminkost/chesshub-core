package Servlets;

import BusinessObjects.Game;
import PGNReader.PGNReader;
import dao.GameDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.*;

public class PGNServlet extends HttpServlet{
    private static final long serialVersionUID = 1L;

    private GameDao gameDao;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public void init() {
        gameDao = GameDao.getInstance();
    }

    /*
        public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

            response.getWriter().append("Servedat: ").append(request.getContextPath());
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/PGNUpload.jsp") ;
            dispatcher.forward(request, response);
        }

     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //input mit request Objekt
        Part filePart = request.getPart("file");

        //safe input in game
        String fileName = filePart.getSubmittedFileName();

        System.out.println("Sind hier 1");

        // Erstelle eine temporäre Datei
        File tempFile = File.createTempFile(fileName.substring(0, fileName.indexOf(".")+1), "."+"pgn");

        try (InputStream in = filePart.getInputStream();
             OutputStream out = new FileOutputStream(tempFile)) {

            // Schreibe den Inhalt des Part-Objekts in die temporäre Datei
            byte[] buffer = new byte[1024];
            int length;
            while ((length = in.read(buffer)) > 0) {
                out.write(buffer, 0, length);
            }
        }

        //Game Objekt erstellen
        PGNReader pgnReader = new PGNReader();

        Game importedGame = pgnReader.parseFile(tempFile);

        //safe game with DAO in Database
        gameDao.insertGame(importedGame);

        //Nutzer Antwortwort geben mit response Objekt
        response.sendRedirect("PGNUploaded.html");
    }

        public void destroy() {
        }

}
