package view;

import controller.NoteAppController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.NotePair;

import java.util.List;

/**
 * To-do notes is one option of notes the user
 * can choose from to create
 */
public class ToDoNotes extends MenuUI {

    private Notes note = new Notes();
    private GridPane grid = new GridPane();
    private final int NUM_COLS = 4;
    private final int COL_WIDTH = 40;
    private final int ROWSPAN = 1;
    private final int WIN_WIDTH = COL_WIDTH * 12;
    private final int WIN_HEIGHT = COL_WIDTH * 12;
    private final double BUTTON_WIDTH = 40;
    private final int BUTTON_PADDING = 10;
    private NoteAppController controller = new NoteAppController();

    private void gridLayout() {
        note.gridLayout();
        getGrid().setAlignment(Pos.TOP_CENTER);
        getGrid().setId("grid");
        getGrid().setHgap(BUTTON_PADDING);
        getGrid().setVgap(BUTTON_PADDING);
        getGrid().setPadding(new Insets(BUTTON_PADDING));
    }

    Scene getScene(HBox defaultButtons) {

        VBox scene = new VBox();
        scene.getChildren().add(defaultButtons);

        gridLayout();

        TextField title = new TextField();
        title.setMaxHeight(BUTTON_WIDTH);
        title.setId("title");

        TextField todo = new TextField();
        todo.setMaxHeight(BUTTON_WIDTH);
        todo.setId("todo");

        Button post = new Button("Add");
        post.setMaxHeight(BUTTON_WIDTH);
        post.setId("post");

        final String[] names = new String[]{todo.getText()};
        final CheckBox[] cbs = new CheckBox[names.length];

        Button view = new Button("View Todos");
        post.setMaxHeight(BUTTON_WIDTH);
        post.setId("todo");

        VBox vbox = new VBox();

        ScrollPane scrollPane = new ScrollPane(vbox);
        scrollPane.setFitToWidth(true);

        view.setOnAction(event -> {
            List<NotePair> list;

            list = controller.handleSelectNote("todo");
            System.out.println(list);

            for (NotePair noteList : list) {

                VBox innerVBox = new VBox();

                TextField todoTitle = new TextField();
                todoTitle.setEditable(false);

                CheckBox todoItem = new CheckBox();

                HBox noteField = new HBox();

                todoTitle.setText(noteList.getBody());
                todoItem.setText(noteList.getOther());
                noteField.getChildren().addAll(todoTitle, todoItem);

                innerVBox.getChildren().addAll(noteField);
                vbox.getChildren().addAll(innerVBox);

                System.out.println("notelist: " + noteList);
            }
        });

        getGrid().add(title, 0, 0, NUM_COLS, ROWSPAN);

        getGrid().add(todo, 0, 1, NUM_COLS, ROWSPAN);

        getGrid().add(post, 0, 2, NUM_COLS, ROWSPAN);

        getGrid().add(view, 0, 3, NUM_COLS, ROWSPAN);

        getGrid().add(scrollPane, 0, 4, NUM_COLS, ROWSPAN);

        post.setOnAction(event -> controller.handleNewNote("todo", title.getText(), todo.getText()));

        scene.getChildren().add(getGrid());

        return new Scene(scene, WIN_WIDTH, WIN_HEIGHT);
    }

    @Override
    public String toString() {
        final int ROW_INDEX = 0;

        return "ToDoNotes{" +
                "note=" + note +
                ", grid=" + getGrid() +
                ", NUM_COLS=" + NUM_COLS +
                ", COL_WIDTH=" + COL_WIDTH +
                ", ROW_INDEX=" + ROW_INDEX +
                ", ROWSPAN=" + ROWSPAN +
                ", WIN_WIDTH=" + WIN_WIDTH +
                ", WIN_HEIGHT=" + WIN_HEIGHT +
                ", BUTTON_WIDTH=" + BUTTON_WIDTH +
                ", BUTTON_PADDING=" + BUTTON_PADDING +
                ", controller=" + controller +
                '}';
    }

    private GridPane getGrid() {

        return grid;
    }
}