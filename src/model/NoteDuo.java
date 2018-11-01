package model;

public class NoteDuo {
    private String quote;
    private String author;

    public NoteDuo(String quote, String author) {
        this.quote = quote;
        this.author = author;
    }

    public String getQuote() {
        return quote;
    }

    public String getAuthor() {
        return author;
    }

    @Override
    public String toString() {
        return "'" + quote + "'" + " - " + author;
    }
}