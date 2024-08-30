package de.ben_kostka.benchesster.service.impl;

import de.ben_kostka.benchesster.exception.ResourceNotFoundException;
import de.ben_kostka.benchesster.model.Game;
import de.ben_kostka.benchesster.payload.GameDto;
import de.ben_kostka.benchesster.repository.GameRepository;
import de.ben_kostka.benchesster.service.UploadGameService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UploadGameServiceImpl implements UploadGameService {
    @Value("${file.upload-dir}")
    private String uploadPath;
    private GameRepository gameRepository;

    public UploadGameServiceImpl(GameRepository gameRepository){
        this.gameRepository = gameRepository;
    }
    @Override
    public List<String> getAvailableUploadMethods() {
        List<String> uploadMethods = new ArrayList<>(List.of("Board", "Upload PGN", "Take Photo"));
        return uploadMethods;
    }

    @Override
    public GameDto uploadOnBoard(GameDto gameDto) {
        return null;
    }

    /**
     * Given pgn file this method returns list of games contained in the file.
     *
     * @param multipartFile the pgn file to be parsed.
     * @return Game that was put in.
     *
     * @author Benjamin Kostka
     */
    @Override
    public GameDto uploadFromFile(MultipartFile multipartFile) {
        GameDto gameDto = new GameDto();

        System.out.println("Is the File empty: "+multipartFile.isEmpty());

        // File convert to GameDto object
        try {
            System.out.println("In try block");
            // Get the file name
            System.out.println("In try block part 1.2");
            // Save the file
            File uploadedFile = convertMultipartfileToFile(multipartFile);
            System.out.println("In try block part 1.3");

            // Parse File and make
            gameDto = parseFile(uploadedFile);

            System.out.println("In try block part 2");
            // safe game with DAO in Database
            gameRepository.save(mapToEntity(gameDto));
            System.out.println("In try block part 3");

        } catch (Exception e) {
            if (e.getMessage().isEmpty()) {
                new Exception("No file uploaded.");
            } else {
                new Exception("File must end with .pgn");
            }
        }
        return gameDto;
    }

    @Override
    public GameDto uploadFromImage(MultipartFile multipartFile) {
        return null;
    }

    // converted entity to DTO
    private GameDto mapToDTO(Game game){
        GameDto gameDto = new GameDto();
        gameDto.setGame_ID(game.getGame_ID());
        gameDto.setDate(game.getDate());
        gameDto.setEvent(game.getEvent());
        gameDto.setComment(game.getComment());
        gameDto.setBlack(game.getBlack());
        gameDto.setResult(game.getResult());
        gameDto.setRound(game.getRound());
        gameDto.setSite(game.getSite());
        gameDto.setWhite(game.getWhite());
        gameDto.setMoves(game.getMoves());
        return gameDto;
    }

    private Game mapToEntity(GameDto gameDto){
        Game game = new Game();
        game.setGame_ID(gameDto.getGame_ID());
        game.setDate(gameDto.getDate());
        game.setEvent(gameDto.getEvent());
        game.setComment(gameDto.getComment());
        game.setBlack(gameDto.getBlack());
        game.setResult(gameDto.getResult());
        game.setRound(gameDto.getRound());
        game.setSite(gameDto.getSite());
        game.setWhite(gameDto.getWhite());
        game.setMoves(gameDto.getMoves());
        return game;
    }

    /**
     * Given pgn file this method returns list of games contained in the file.
     *
     * @param f the pgn file to be parsed.
     * @return Game that was put in.
     *
     * @author Benjamin Kostka
     */
    private GameDto parseFile(File f) {
        GameDto gameDto = new GameDto();

        if (f == null) {
            throw new NullPointerException("File can't be null!");
        }

        if (!f.isFile()) {
            throw new IllegalArgumentException("f should not be a directory, but was: " + f);
        }

        if (!f.getName().toLowerCase().endsWith(".pgn")) {
            throw new IllegalArgumentException("File must end with \".pgn\"");
        }

        BufferedReader input = null;

        try {
            input = new BufferedReader(new FileReader(f));
            String line;
            //read the entire pgn file after the instructions
            while ((line = input.readLine()) != null) {
                if (line.length() > 0) {

                    if (line.startsWith("[")) {
                        String[] lineTag = line.split(" ",2);
                        setAttribute(gameDto, lineTag[0].substring(1), //attribute name
                                lineTag[1].substring(1, lineTag[1].length() - 2));   //attribute value
                    } else {
                        gameDto.setMoves(gameDto.getMoves() + " "+ line);
                    }
                }
            }

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();

        } catch (ParseException | IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (input != null) {
                    input.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        gameDto.setMoves(gameDto.getMoves().substring(4));
        return gameDto;
    }
    private void setAttribute(GameDto gameDto, String attrName, String attrValue) throws ParseException {
        attrName = attrName.toLowerCase();

        switch (attrName) {
            case "event":
                gameDto.setEvent(attrValue);
                break;
            case "site":
                gameDto.setSite(attrValue);
                break;
            case "date":
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd");
                Date dateAttr = formatter.parse(attrValue);
                gameDto.setDate(dateAttr);
                break;
            case "round":
                gameDto.setRound(attrValue);
                break;
            case "result":
                gameDto.setResult(attrValue);
                break;
        }
    }

    private File convertMultipartfileToFile(MultipartFile multipartFile) {
        // Create new File in directory
        System.out.println("In methode part 1");
        Path path = Paths.get(uploadPath, "temp_file.txt");
        System.out.println("In methode part 2: "+multipartFile.getOriginalFilename());
        File file = new File(path.toString());
        System.out.println("In methode part 3: "+path.toString()+" "+file.getAbsolutePath());


        // Write content into the file
        try {
            multipartFile.transferTo(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("In methode part 4");

        return file;
    }

    private void uploadFile(MultipartFile multipartFile) throws IOException {
        Path path = Paths.get(uploadPath, multipartFile.getOriginalFilename());
        Files.copy(multipartFile.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
    }
}
