package jsl28.exam.project.model;

public class SitterMessage {
    private Sitter sitter;
    private String message;

    public SitterMessage(Sitter sitter) {
        this.sitter = sitter;
    }

    public SitterMessage() {
    }

    public Sitter getSitter() {
        return sitter;
    }

    public void setSitter(Sitter sitter) {
        this.sitter = sitter;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
