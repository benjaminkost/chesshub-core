package PGNReaderTest;

import businessObjects.Game;
import management.PGNReader;
import org.junit.Test;

import java.io.File;

public class PGNReaderTest {

    @Test
    public void testExecutePGNReader() {

        try {
            PGNReader test = new PGNReader();
            File pgnFile = new File("src/test/resources/PGNTestFile.pgn");
            Game importedGame = test.parseFile(pgnFile);
            System.out.println(importedGame.toString());
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}