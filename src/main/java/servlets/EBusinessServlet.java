package servlets;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.net.URI;
import java.net.http.*;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class EBusinessServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        String form = req.getParameter("form");

        switch (form){
            case "nextmove": {
                String fen_input = req.getParameter("fen");

                String api_key = "7471c6c4fbmsh3cf424368bd59e2p1fe9ffjsn938cc5464d15";

                // HTTP-Verbindung aufbauen und Position analysieren
                try {
                    HttpRequest request = HttpRequest.newBuilder()
                            .uri(URI.create("https://chess-stockfish-16-api.p.rapidapi.com/chess/api"))
                            .header("content-type", "application/x-www-form-urlencoded")
                            .header("X-RapidAPI-Key", api_key)
                            .header("X-RapidAPI-Host", "chess-stockfish-16-api.p.rapidapi.com")
                            .method("POST", HttpRequest.BodyPublishers.ofString("fen="+fen_input))
                            .build();
                    HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

                    String bestMove = "";

                    try{
                        //Antwort im JSON-Format, die bestMove enthält
                        JSONObject json_Response = new JSONObject(response.body());
                        //String bestMove auslesen
                        bestMove = json_Response.getString("bestmove");
                    }
                    catch (JSONException j){
                        req.setAttribute("message", "Please insert the current position as FEN");
                        req.getRequestDispatcher("Message.jsp").forward(req, res);
                    }

                    // Besten Move als Attribut speichern
                    req.setAttribute("best_move", bestMove);

                    // JSP wieder aufrufen
                    req.getRequestDispatcher("EBusiness.jsp").forward(req, res);

                    //wenn Probleme, wird Custom Error Seite mit Error angezeigt
                } catch (Exception e) {
                    req.setAttribute("message", e.toString());
                    req.getRequestDispatcher("Message.jsp").forward(req, res);
                }
                break;
            }

            case "evaluation": {
                int depth_input = -1;
                try {
                    depth_input = Integer.parseInt(req.getParameter("depth"));
                }
                catch (NumberFormatException e){
                    req.setAttribute("message", "Please insert depth as a number <14");
                    req.getRequestDispatcher("Message.jsp").forward(req, res);
                }

                if(depth_input>13){
                    req.setAttribute("message", "Depth to high, please use depth <14");
                    req.getRequestDispatcher("Message.jsp").forward(req, res);
                }
                String fen_input = req.getParameter("fen");

                String mode = "eval";

                String url_params = "https://stockfish.online/api/stockfish.php?fen="+fen_input+"&depth="+depth_input+"&mode="+mode;

                try {
                    // Eine Verbindung zur URL herstellen und GET einstellen
                    HttpURLConnection connection = (HttpURLConnection) new URL(url_params).openConnection();
                    connection.setRequestMethod("GET");

                    // Lesen der Antwort
                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    reader.close();

                    //Antwort ist im JSON-Format, darin muss die Evaluation mit dem Key "data" ausgelesen werden.
                    String evaluation = "";
                    try{
                        JSONObject json_Response = new JSONObject(response.toString());
                        evaluation = json_Response.getString("data");
                    }
                    catch (JSONException j){
                        req.setAttribute("message", "Please insert the current position as FEN");
                        req.getRequestDispatcher("Message.jsp").forward(req, res);
                    }

                    // Verbindung schließen
                    connection.disconnect();

                    // Evaluation als Attribut speichern und JSP wieder aufrufen
                    req.setAttribute("evaluation", evaluation);
                    req.getRequestDispatcher("EBusiness.jsp").forward(req, res);

                } catch (IOException e) {
                    req.setAttribute("message", e.toString());
                    req.getRequestDispatcher("Message.jsp").forward(req, res);
                }
                break;
            }

            default: {
                req.setAttribute("message", "Error: Unknown issue");
                req.getRequestDispatcher("Message.jsp").forward(req, res);
            }
        }
    }
}