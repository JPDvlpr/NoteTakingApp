package model;

import java.util.List;

public interface INotesData {
    void addNotes(String tableName, String body, String other);

    List<NotePair> viewNotes(String type);

    List<NotePair> viewToDos();
}