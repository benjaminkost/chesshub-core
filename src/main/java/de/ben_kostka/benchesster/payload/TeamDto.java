package de.ben_kostka.benchesster.payload;

import de.ben_kostka.benchesster.model.Club;
import de.ben_kostka.benchesster.model.User;
import lombok.Data;

@Data
public class TeamDto {
    private int team_ID;
    private String name;
    private Club club;
    private User leader;
}
