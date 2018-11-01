package model;

public interface INotesData {
    void addNotes(String tableName, String body, String other);

    String viewNotes(String tableName, String body, String other);
//    void removeColor(String name);
//    void update(String name, Color color);
//    public List<ColorPair> getColors();
}