package de.ben_kostka.benchesster.service.impl;

import de.ben_kostka.benchesster.exception.FileEmptyException;
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
    public GameDto uploadFromFile(MultipartFile multipartFile) throws Exception {
        GameDto gameDto = new GameDto();

        if(multipartFile.isEmpty()){
            throw new FileEmptyException(multipartFile.getOriginalFilename());
        }

        // File convert to GameDto object
        try {
            File uploadedFile = convertMultipartfileToFile(multipartFile);

            // Parse File and make
            gameDto = parseFile(uploadedFile);

            // safe game with DAO in Database
            gameRepository.save(mapToEntity(gameDto));

        } catch (Exception e) {
            if (e.getMessage().isEmpty()) {
                throw new Exception("No file uploaded.");
            }else {
                throw new Exception(e.getMessage());
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
        gameDto.setGameID(game.getGame_ID());
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
        game.setGame_ID(gameDto.getGameID());
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
        Boolean isFirstTime = true;

        try {
            input = new BufferedReader(new FileReader(f));
            String line;
            //read the entire pgn file after the instructions
            while ((line = input.readLine()) != null) {
                if (!line.isEmpty()) {

                    if (line.startsWith("[")) {
                        String MetaDataTag = line.substring(line.indexOf("[")+1, line.indexOf("\"")-1);
                        String ContentOfTag = line.substring(line.indexOf("\"")+1, line.lastIndexOf("\""));
                        setAttribute(gameDto, MetaDataTag, ContentOfTag);
                    } else if(isFirstTime){
                        gameDto.setMoves(line);
                        isFirstTime = false;
                    } else{
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
        Path path = Paths.get(uploadPath, multipartFile.getOriginalFilename());
        File file = new File(path.toString());

        // Write content into the file
        try {
            multipartFile.transferTo(file);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }

        return file;
    }

    private void uploadFile(MultipartFile multipartFile) throws IOException {
        Path path = Paths.get(uploadPath, multipartFile.getOriginalFilename());
        Files.copy(multipartFile.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
    }
}
