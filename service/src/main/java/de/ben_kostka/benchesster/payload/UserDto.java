package de.ben_kostka.benchesster.payload;

import de.ben_kostka.benchesster.model.Team;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserDto {
    private int user_ID;
    private String lastname;
    private String firstname;
    private String email;
    private String password;
    private List<Team> teams = new ArrayList<>();
}
