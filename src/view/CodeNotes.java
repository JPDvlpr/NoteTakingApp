package view;

import controller.NoteAppController;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;

/**
 * Code notes is one option of notes the user
 * can choose from to create
 */
public class CodeNotes
{
    private Notes note = new Notes();
    public GridPane grid = new GridPane();
    private final int NUM_COLS = 4;
    private final int COL_WIDTH = 40;
    private final int BUTTON_PADDING = 10;
    private final int WIN_WIDTH = COL_WIDTH * 7;
    private final int WIN_HEIGHT = COL_WIDTH * 7;
    private final double BUTTON_WIDTH = 40;
    private NoteAppController controller = new NoteAppController();

    public void gridLayout()
    {
        note.gridLayout();
        grid.setAlignment(Pos.CENTER);
        //grid.setGridLinesVisible(true);
        grid.setId("codenotes-grid");
        grid.setHgap(BUTTON_PADDING);
        grid.setVgap(BUTTON_PADDING);
        grid.setPadding(new Insets(BUTTON_PADDING));
    }

    /**
     * scene that has code snippet and when user adds
     * the snippet gets added to db. The user has the option
     * to view all of their to-dos
     * @param buttonPanel
     * @return
     */
    public Scene getScene(HBox buttonPanel)
    {
        VBox scene = new VBox(  );
        scene.getChildren().add( buttonPanel );
        gridLayout();
    
        TextField codeSnippet = new TextField();
        
        codeSnippet.setStyle("-fx-font-family: 'monospaced';");
        codeSnippet.prefColumnCountProperty().bind(codeSnippet.textProperty().length());
    
        codeSnippet.textProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println("CodeSnippet: " + newValue);
        });
    
        TextField codeNote = new TextField();
        codeNote.setFont( Font.font("Helvetica", FontPosture.ITALIC, 14));
        codeNote.setMaxHeight(BUTTON_WIDTH);
        codeNote.setId("codenote");
    
        codeNote.textProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println("Code: " + newValue);
        });
    
        Button post = new Button("Post");
        post.setMaxHeight(BUTTON_WIDTH);
        post.setId("post");
    
        post.setOnAction(event -> {
            controller.handleNewNote("codeSnippet", codeNote.getText(), codeNote.getText());
        });
    
        grid.add(codeSnippet, 0,0, NUM_COLS,1);
    
        grid.add(codeNote, 0, 1, NUM_COLS, 1);
    
        grid.add(post, 0, 2, NUM_COLS, 1);
    
        scene.getChildren().add( grid );
    
        return new Scene(scene, WIN_WIDTH, WIN_HEIGHT);
    }
}