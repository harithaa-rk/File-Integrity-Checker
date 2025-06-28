package model;

public class FileHash {
    private String fileName;
    private String hash;

    public FileHash(String fileName, String hash) {
        this.fileName = fileName;
        this.hash = hash;
    }

    public String getFileName() {
        return fileName;
    }

    public String getHash() {
        return hash;
    }
}
