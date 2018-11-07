package view;

import controller.NoteAppController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.NotePair;

import java.util.List;

public class AllNotes {

    private Notes note = new Notes();
    public GridPane grid = new GridPane();
    private final int COL_WIDTH = 40;
    private final int WIN_HEIGHT = COL_WIDTH * 12;

    private NoteAppController controller = new NoteAppController();

    private void gridLayout() {
        note.gridLayout();
        grid.setAlignment(Pos.CENTER);
        grid.setId("codenotes-grid");
        int BUTTON_PADDING = 10;
        grid.setHgap(BUTTON_PADDING);
        grid.setVgap(BUTTON_PADDING);
        grid.setPadding(new Insets(BUTTON_PADDING));
    }

    /**
     * scene that has code snippet and when user adds
     * the snippet gets added to db. The user has the option
     * to view all of their to-dos
     */
    public Scene getScene(HBox buttonPanel) {
        VBox scene = new VBox();
        scene.getChildren().add(buttonPanel);
        gridLayout();

        Button filterNotes = new Button("Filter Notes");


        double BUTTON_WIDTH = 40;

        filterNotes.textProperty().addListener((observable, oldValue, newValue) -> System.out.println("CodeSnippet: " + newValue));

        VBox vbox = new VBox();
        ScrollPane scrollPane = new ScrollPane(vbox);
        scrollPane.setFitToWidth(true);

        filterNotes.setOnAction(event -> {
            List<NotePair> list;

            list = controller.handleSelectNote("codesnippet");
            System.out.println(list);

            for (NotePair noteList : list) {

                TextField snippetField = new TextField();

                HBox noteField = new HBox();

                snippetField.setText(noteList.getBody());

                noteField.getChildren().addAll(snippetField);
                vbox.getChildren().addAll(noteField);
            }

        });

        int NUM_COLS = 4;
        int ROWSPAN = 1;

        grid.add(filterNotes, 0, 0, NUM_COLS, ROWSPAN);
        grid.add(scrollPane, 0, 1, NUM_COLS, ROWSPAN);

        scene.getChildren().add(grid);

        int WIN_WIDTH = COL_WIDTH * 12;
        return new Scene(scene, WIN_WIDTH, WIN_HEIGHT);

    }
}