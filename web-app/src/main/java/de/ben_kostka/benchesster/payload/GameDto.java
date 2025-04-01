package de.ben_kostka.benchesster.payload;

import lombok.Data;
import de.ben_kostka.benchesster.model.User;
import java.util.Date;

@Data
public class GameDto {
    private Long id;
    private Date date;
    private String round;
    private String event;
    private String site;
    private String moves;
    private String result;
    private User white_user;
    private User black_user;
    private String white_player_name;
    private String black_player_name;
    private String comment;
}
