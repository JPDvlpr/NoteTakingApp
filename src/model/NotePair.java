package model;

public class NotePair {

    private String body;
    private String other;

    public NotePair(String body, String other) {
        this.body = body;
        this.other = other;
    }

    public String getBody() {
        return body;
    }

    public String getOther() {
        return other;
    }

    @Override
    public String toString() {
        return body + " - " + other;
    }
}