package management;

import businessObjects.Game;
import java.io.*;
import java.text.*;
import java.util.Date;

/**
 * Enables parsing files in reduced PGN export format.
 *
 * @author Benjamin Kostka
 */
public class PGNReader {

    public Game importedGame = new Game();

    /**
     * Given pgn file this method returns list of games contained in the file.
     *
     * @param f the pgn file to be parsed.
     * @return Game that was put in.
     *
     * @author Benjamin Kostka
     */
    public Game parseFile(File f) {
        if (f == null) {
            throw new NullPointerException("File can't be null!");
        }

        if (!f.isFile()) {
            throw new IllegalArgumentException("f should not be a directory, but was: " + f);
        }

        if (!f.getName().toLowerCase().endsWith(".pgn")) {
            throw new IllegalArgumentException("File must end with \".pgn\"");
        }

        BufferedReader input = null;

        try {
            input = new BufferedReader(new FileReader(f));
            String line;
            //read the entire pgn file after the instructions
            while ((line = input.readLine()) != null) {
                if (line.length() > 0) {

                    if (line.startsWith("[")) {
                        String[] lineTag = line.split(" ",2);
                        setAttribute(lineTag[0].substring(1), //attribute name
                                lineTag[1].substring(1, lineTag[1].length() - 2));   //attribute value
                    } else {
                        importedGame.setMoves(importedGame.getMoves() + " "+ line);
                    }
                }
            }

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();

        } catch (ParseException | IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (input != null) {
                    input.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        importedGame.setMoves(importedGame.getMoves().substring(4));
        return importedGame;
    }

    /**
     * This method updates the attributes of a game, depending on the input values
     *
     * @param attrName - attribut, which should be set
     * @param attrValue - new value for attribut
     *
     * @throws ParseException if parsing Date fails
     *
     * @author Benjamin Kostka
     */

    private void setAttribute(String attrName,
                              String attrValue) throws ParseException {
        attrName = attrName.toLowerCase();

        switch (attrName) {
            case "event":
                importedGame.setEvent(attrValue);
                break;
            case "site":
                importedGame.setSite(attrValue);
                break;
            case "date":
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd");
                Date dateAttr = formatter.parse(attrValue);
                importedGame.setDate(dateAttr);
                break;
            case "round":
                importedGame.setRound(attrValue);
                break;
            case "white":
                importedGame.setCommentWhite(attrValue);
                break;
            case "black":
                importedGame.setCommentBlack(attrValue);
                break;
            case "result":
                importedGame.setResult(attrValue);
                break;
        }
    }
}