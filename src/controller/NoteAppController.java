package controller;

import model.DBNotes;
import model.INotesData;
import model.NotePair;

import java.util.List;

/**
 * Note app controller controls the interactions
 * from the UI
 */
public class NoteAppController {

    private INotesData model;

    /**
     * constructor for the controller has the model
     * instance of INotesData
     */
    public NoteAppController() {

        model = new DBNotes();
    }

    /**
     * handle new note takes the case statement of
     * each table type and calls the add function
     * from model
     */
    public void handleNewNote(String type, String body, String other) {
        switch (type) {
            case "quote":
                if (isEmpty(body) || isEmpty(other)) {
                    return;
                }
                model.addNotes("quotes", body, other);

            case "hyperlink":
                if (isEmpty(body) || isEmpty(other)) {
                    return;
                }
                model.addNotes("hyperlinks", body, other);

            case "codesnippet":
                if (isEmpty(body) || isEmpty(other)) {
                    return;
                }
                model.addNotes("codesnippets", body, other);

            case "todo":
                if (isEmpty(body) || isEmpty(other)) {
                    return;
                }
                model.addNotes("todos", body, other);
        }
    }

    /**
     * handle select note takes the case statement of
     * each table type and calls the view function
     */
    public List<NotePair> handleSelectNote(String type) {
        switch (type) {
            case "quote":
                return model.viewNotes("quotes");
            case "hyperlink":
                return model.viewNotes("hyperlinks");
            case "codesnippet":
                System.out.println("controller: " + model.viewNotes("codesnippets"));
                return model.viewNotes("codesnippets");
            case "todo":
                return model.viewToDos();
        }
        return null;
    }

    private boolean isEmpty(String value) {
        return value == null || value.equals("");
    }


}
