package de.ben_kostka.benchesster.payload;

import de.ben_kostka.benchesster.model.Game;
import de.ben_kostka.benchesster.model.User;
import lombok.Data;

@Data
public class RequestDto {
    private int request_ID;
    private User sender;
    private User recipient;
    private Game game;
    private String status;
}
