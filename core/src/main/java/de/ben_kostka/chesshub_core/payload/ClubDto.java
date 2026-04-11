package de.ben_kostka.chesshub_core.payload;

import de.ben_kostka.chesshub_core.model.User;
import lombok.Data;

@Data
public class ClubDto {
    private Long id;
    private String name;
    private User president;
}
