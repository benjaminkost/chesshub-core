package de.ben_kostka.benchesster.payload;

import java.util.Date;
import de.ben_kostka.benchesster.model.User;
import lombok.Data;

@Data
public class GameDto {
    private int game_ID;
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
