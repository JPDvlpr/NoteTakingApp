package view;

import controller.NoteAppController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import model.DBNotes;
import model.NotePair;

import java.util.ArrayList;
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
    private final int ROW_INDEX = 0;
    private final int ROWSPAN = 1;
    private final int WIN_WIDTH = COL_WIDTH * 12;
    private final int WIN_HEIGHT = COL_WIDTH * 12;
    private final double BUTTON_WIDTH = 40;
    private final int BUTTON_PADDING = 10;
    private NoteAppController controller = new NoteAppController();

    /**
     * Sets up a grid with the spacing and alignment
     */
    public void gridLayout() {
        note.gridLayout();
        getGrid( ).setAlignment(Pos.TOP_CENTER);
        //grid.setGridLinesVisible(true);
        getGrid( ).setId("grid");
        getGrid( ).setHgap(BUTTON_PADDING);
        getGrid( ).setVgap(BUTTON_PADDING);
        getGrid( ).setPadding(new Insets(BUTTON_PADDING));
    }

    /**
     * scene that has title and to-do when user adds
     * textbox fields get added to do and todos get
     * appened to the grid. The user has the option
     * to view all of their to-dos
     * @param defaultButtons
     * @return
     */
    public Scene getScene(HBox defaultButtons) {

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
    
        DBNotes note = new DBNotes();
    
        VBox vbox = new VBox();
        ScrollPane scrollPane = new ScrollPane(vbox);
        scrollPane.setFitToWidth(true);
    
        view.setOnAction(event -> {
            List<NotePair> list = controller.handleSelectNote("todo");
    
            System.out.println(list);
        
            for (NotePair todoList : list) {
            
                TextField quoteField = new TextField();
                quoteField.getStyleClass().add("todo-field");
            
                TextField authorField = new TextField();
                authorField.getStyleClass().add("todo-field");
            
                HBox noteField = new HBox();
            
                //quoteField.setStyle("-fx-text-inner-font-style: italic;");
            
                quoteField.setText("'" + todoList.getBody() + "'");
                quoteField.setFont( Font.font("Verdana", FontPosture.ITALIC, 12));
            
                authorField.setText(todoList.getOther());
                noteField.getChildren().addAll(quoteField, authorField);
                vbox.getChildren().addAll(noteField);
            }
            //System.out.println(controller.handleSelectNote("quote"));
        
        });

        getGrid( ).add(title, 0, 0, NUM_COLS, ROWSPAN);

        getGrid( ).add(todo, 0, 1, NUM_COLS, ROWSPAN);

        getGrid( ).add(post, 0, 2, NUM_COLS, ROWSPAN);
    
        getGrid( ).add(view, 0, 3, NUM_COLS, ROWSPAN);
    
        getGrid( ).add(scrollPane, 0, 4, NUM_COLS, ROWSPAN);
    
    
        post.setOnAction(event -> {
            controller.handleNewNote("todo", title.getText(), todo.getText());
//
//            for (int i = 0; i < names.length; i++) {
//                int j = 3;
//                final CheckBox cb = cbs[i] = new CheckBox(todo.getText());
//
//                grid.add(cb, 0, j, NUM_COLS, ROWSPAN);
//                j++;
//            }
        });

        scene.getChildren().add( getGrid( ) );

        return new Scene(scene, WIN_WIDTH, WIN_HEIGHT);
    }
    
    @Override
    public String toString()
    {
        
        return "ToDoNotes{" +
                "note=" + note +
                ", grid=" + getGrid( ) +
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
    
    public GridPane getGrid()
    {
        
        return grid;
    }
    
}