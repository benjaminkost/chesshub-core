package PGNReader;

import Database.DatabaseConnector;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PGNReaderTest {

    @Test
    public void testExecutePGNReader() {

        try {
            PGNReader test = new PGNReader();
            File pgnFile = new File("/Users/benkostka/Documents/Schach/Partien/Gewonnen/TestPGN.pgn");
            test.parseFile(pgnFile);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
