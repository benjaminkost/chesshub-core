package de.ben_kostka.chesshub_core.exception;

public class FileEmptyException extends RuntimeException {
    private String fileName;


    public FileEmptyException(String fileName) {

        super(String.format("%s file is empty", fileName));
        this.fileName = fileName;
    }

}
