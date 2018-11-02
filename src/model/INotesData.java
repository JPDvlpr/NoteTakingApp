package model;

import java.util.List;

public interface INotesData {
    void addNotes(String tableName, String body, String other);

    public List<NotePair> viewNotes();
}