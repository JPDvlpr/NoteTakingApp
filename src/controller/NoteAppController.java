package controller;

import model.DBNotes;
import model.INotesData;
import model.NotePair;

import java.util.Arrays;
import java.util.List;

public class NoteAppController {

    private INotesData model;
    private String[] noteData;

    public NoteAppController() {

        model = new DBNotes();
    }

    public boolean handleNewNote(String type, String body, String other) {
        switch (type) {
            case "quote":
                if (isEmpty(body) || isEmpty(other)) {
                    return false;
                }
                model.addNotes("quotes", body, other);
                return true;
        }
        switch (type) {
            case "hyperlink":
                if (isEmpty(body) || isEmpty(other)) {
                    return false;
                }
                model.addNotes("hyperlinks", body, other);
                return true;
        }
        switch (type) {
            case "codesnippet":
                if (isEmpty(body) || isEmpty(other)) {
                    return false;
                }
                model.addNotes("codesnippets", body, other);
                return true;
        }
        switch (type) {
            case "todo":
                if (isEmpty(body) || isEmpty(other)) {
                    return false;
                }
                model.addNotes("todos", body, other);
                return true;
        }
        return false;
    }

    public List<NotePair> handleSelectNote(String type) {
        switch (type) {
            case "quote":
                return model.viewNotes("quotes");
            case "hyperlink":
                return model.viewNotes("hyperlinks");
            case "codesnippet":
                return model.viewNotes("codesnippets");
            case "todo":
                return model.viewToDos();
         }
        return null;
    }

    private boolean isEmpty(String value) {
        return value == null || value.equals("");
    }

    @Override
    public String toString()
    {

        return "NoteAppController{" +
                "model=" + model +
                ", noteData=" + Arrays.toString( noteData ) +
                '}';
    }
}
