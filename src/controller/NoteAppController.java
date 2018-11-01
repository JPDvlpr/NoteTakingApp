package controller;

import model.DBNotes;
import model.INotesData;

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

    public boolean handleSelectNote(String type, String body, String other) {
        switch (type) {
            case "quote":
                if (isEmpty(body) || isEmpty(other)) {
                    return false;
                }
                model.viewNotes("quotes", body, other);
                return true;
        }
        return false;
    }

    private boolean isEmpty(String value) {
        return value == null || value.equals("");
    }
}
