package model;

/**
 *
 * Used as a type passed within the program
 */
public class NotePair {

    private String tableName;
    private String body;
    private String other;

    /**
     * constructor with 3 arguments
     */
    NotePair(String tableName, String body, String other) {
        this.tableName = tableName;
        this.body = body;
        this.other = other;
    }

    /**
     * this is the first param used
     */
    public String getBody() {
        return body;
    }

    /**
     * this is the optional second param used
     */
    public String getOther() {
        return other;
    }

    @Override
    public String toString() {
        return body + " - " + other;
    }
}