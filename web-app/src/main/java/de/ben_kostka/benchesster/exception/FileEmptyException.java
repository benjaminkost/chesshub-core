package de.ben_kostka.benchesster.exception;

public class FileEmptyException extends RuntimeException {
    private String fileName;


    public FileEmptyException(String fileName) {

        super(String.format("%s file is empty", fileName));
        this.fileName = fileName;
    }

}
