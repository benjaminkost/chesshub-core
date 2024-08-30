package de.ben_kostka.benchesster.payload;

import java.util.ArrayList;
import java.util.List;
import de.ben_kostka.benchesster.model.Team;
import lombok.Data;

@Data
public class UserDto {
    private int user_Id;
    private String lastname;
    private String firstname;
    private String email;
    private String password;
    private List<Team> teams = new ArrayList<>();
}
