package de.ben_kostka.benchesster.payload;

import lombok.Data;
import de.ben_kostka.benchesster.model.User;
import java.util.Date;

@Data
public class GameDto {
    private int gameID;
    private Date date;
    private String round;
    private String event;
    private String site;
    private String moves;
    private String result;
    private User white;
    private User black;
    private String comment;
}
