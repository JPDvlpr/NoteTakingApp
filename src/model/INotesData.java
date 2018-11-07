package model;

import java.util.List;

/**
 * the interface that holds the functions in the model
 */
public interface INotesData {
    void addNotes(String tableName, String body, String other);

    void addToDo(String title, String todo);

    List<NotePair> viewNotes(String type);

    List<NotePair> viewToDos();
}