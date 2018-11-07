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

/**
 * Code notes is one option of notes the user
 * can choose from to create
 */
public class CodeNotes {
    private Notes note = new Notes();
    public GridPane grid = new GridPane();
    private final int COL_WIDTH = 40;
    private final int WIN_HEIGHT = COL_WIDTH * 12;

    private NoteAppController controller = new NoteAppController();

    private void gridLayout() {
        note.gridLayout();
        grid.setAlignment(Pos.CENTER);
        //grid.setGridLinesVisible(true);
        grid.setId("codenotes-grid");
        int BUTTON_PADDING = 10;
        grid.setHgap(BUTTON_PADDING);
        grid.setVgap(BUTTON_PADDING);
        grid.setPadding(new Insets(BUTTON_PADDING));
        grid.setGridLinesVisible(true);
    }

    /**
     * scene that has code snippet and when user adds
     * the snippet gets added to db. The user has the option
     * to view all of their to-dos
     *
     */
    public Scene getScene(HBox buttonPanel) {
        VBox scene = new VBox();
        scene.getChildren().add(buttonPanel);
        gridLayout();

        TextField codeSnippet = new TextField();
        codeSnippet.setId("textField");
        codeSnippet.setPromptText("Type your code");

        double BUTTON_WIDTH = 40;
        codeSnippet.setMaxHeight(BUTTON_WIDTH);
        codeSnippet.setId("codenote");
        codeSnippet.setStyle("-fx-font-family: 'monospaced';");
//        codeSnippet.prefColumnCountProperty().bind(codeSnippet.textProperty().length());

        codeSnippet.textProperty().addListener((observable, oldValue, newValue) -> System.out.println("CodeSnippet: " + newValue));

        Button post = new Button("PostCode");
        post.setMaxHeight(BUTTON_WIDTH);
        post.setId("post");
    
        Button view = new Button("View CodeSnippet");
        post.setMaxHeight(BUTTON_WIDTH);
        post.setId("view");
    
        VBox vbox = new VBox();
        ScrollPane scrollPane = new ScrollPane(vbox);
        scrollPane.setFitToWidth(true);
    
        view.setOnAction(event -> {
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

        post.setOnAction(event -> controller.handleNewNote("codesnippet", codeSnippet.getText(), codeSnippet.getText()));

        int NUM_COLS = 4;
        int ROWSPAN = 1;
        grid.add(codeSnippet, 0, 0, NUM_COLS, ROWSPAN);

        grid.add(post, 0, 1, NUM_COLS, ROWSPAN);
    
        grid.add(view, 0, 2, NUM_COLS, ROWSPAN);
    
        grid.add(scrollPane, 0, 3, NUM_COLS, ROWSPAN);

        scene.getChildren().add(grid);

        int WIN_WIDTH = COL_WIDTH * 12;
        return new Scene(scene, WIN_WIDTH, WIN_HEIGHT);
    }
}