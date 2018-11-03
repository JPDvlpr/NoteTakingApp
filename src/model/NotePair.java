package model;

public class NotePair {

    private String tableName;
    private String body;
    private String other;

    public NotePair(String tableName, String body, String other) {
        this.tableName = tableName;
        this.body = body;
        this.other = other;
    }

    public String getTableName() {
        return tableName;
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