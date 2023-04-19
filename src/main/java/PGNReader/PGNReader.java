package PGNReader;

import BusinessObjects.Game;
import dao.ClubDao;
import dao.GameDao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Enables parsing files in reduced PGN export format.
 *
 * @author Benjamin Kostka
 */
public class PGNReader {

    /**
     * State information of the parser representing whether it currently parses
     * tag pair section of some game (true) or movetext section (false).
     */
    private static boolean parsingTagpairs;

    public Game importedGame = new Game();

    /**
     * Given pgn file this method returns list of games contained in the file.
     *
     * @param f the pgn file to be parsed.
     * @return The list of games stored in the file.
     */
    public void parseFile(File f) {
        if (f == null) {
            throw new NullPointerException("File can't be null!");
        }
        if (!f.isFile()) {
            throw new IllegalArgumentException("f should not be a directo"
                    + "ry, but was: " + f);
        }
        if (!f.getName().toLowerCase().endsWith(".pgn")) {
            throw new IllegalArgumentException("File must end with \".pgn\"");
        }

        BufferedReader input = null;

        try {
            input = new BufferedReader(new FileReader(f));
            String line = null;

            StringBuilder moveText = new StringBuilder();

            //po radcich precteme cely pgn file
            while ((line = input.readLine()) != null) {
                if (line.length() > 0) {

                    if (line.startsWith("[")) {
                        String lineTag[] = line.split(" ");
                        setAttribute(lineTag[0].substring(1, lineTag[0].length()), //attribute name
                                lineTag[1].substring(1, lineTag[1].length() - 2));   //attribute value
                    } else {
                        importedGame.setMoves(line);
                    }
                }
            }

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ParseException e) {
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

        System.out.println( importedGame.toString());
        GameDao.getInstance().insertGame(importedGame);
    }

    private void setAttribute(String attrName,
                                     String attrValue) throws ParseException {
        attrName = attrName.toLowerCase();

        //System.out.println("attrName: "+attrName+" attrValue: "+attrValue);

        switch (attrName) {
            case "event":
                importedGame.setEvent(attrValue);
                break;
            case "site":
                importedGame.setSite(attrValue);
                break;
            case "date":
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy.mm.dd");
                Date dateAttr = formatter.parse(attrValue);
                importedGame.setDate(dateAttr);
                break;
            case "round":
                importedGame.setRound(Integer.parseInt(attrValue));
                break;
            case "white":
                //TODO Mit String die Spieler einer Partie suchen
                //importedGame.setWhite(attrValue);
                break;
            case "black":
                //importedGame.setBlack(attrValue);
                break;
            case "result":
                importedGame.setResult(attrValue);
                break;
        }
    }
}