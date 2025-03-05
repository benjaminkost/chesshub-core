package de.ben_kostka.benchesster.payload;

import de.ben_kostka.benchesster.model.User;
import lombok.Data;

@Data
public class ClubDto {
    private Long id;
    private String name;
    private User president;
}
