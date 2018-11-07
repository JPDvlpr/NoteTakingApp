package model;

import java.util.ArrayList;
import java.util.List;

public interface INotesData {
    void addNotes(String tableName, String body, String other);

    List<NotePair> viewNotes(String type);

    List<NotePair> viewToDos();

    void addToDo(String title, String todo);
}