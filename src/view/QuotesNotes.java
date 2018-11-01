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
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import model.DBNotes;

/**
 * quotes notes is one option of notes the user
 * can choose from to create
 */
public class QuotesNotes {

    private Notes note = new Notes();
    public GridPane grid = new GridPane();
    private final int NUM_COLS = 4;
    private final int COL_WIDTH = 40;
    private final int ROW_INDEX = 0;
    private final int ROWSPAN = 1;
    private final int WIN_WIDTH = COL_WIDTH * 12;
    private final int WIN_HEIGHT = COL_WIDTH * 12;
    private final double BUTTON_WIDTH = 40;
    private final int BUTTON_PADDING = 10;
    private NoteAppController controller = new NoteAppController();

    public void gridLayout() {
        note.gridLayout();
        grid.setAlignment(Pos.CENTER);
        //grid.setGridLinesVisible(true);
        grid.setId("grid");
        grid.setHgap(BUTTON_PADDING);
        grid.setVgap(BUTTON_PADDING);
        grid.setPadding(new Insets(BUTTON_PADDING));
    }

    /**
     * scene that has quote and author when user adds
     * the quote gets saved to db. The user has the
     * option to view all of their quotes
     * @param defaultButtons
     * @return
     */
    public Scene getScene(HBox defaultButtons) {
    
        VBox scene = new VBox(  );
        scene.getChildren().add( defaultButtons );
    
        gridLayout();

        TextField quote = new TextField();
        quote.setFont(Font.font("Helvetica", FontPosture.ITALIC, 14));
        quote.setMaxHeight(BUTTON_WIDTH);
        quote.setId("quote");

        TextField author = new TextField();
        author.setMaxHeight(BUTTON_WIDTH);
        author.setId("author");

        Button post = new Button("Post");
        post.setMaxHeight(BUTTON_WIDTH);
        post.setId("post");

        post.setOnAction(event -> {
            controller.handleNewNote("quote", quote.getText(), author.getText());
        });

        Button view = new Button("View Quotes");
        post.setMaxHeight(BUTTON_WIDTH);
        post.setId("view");

        DBNotes note = new DBNotes();

        VBox vbox = new VBox();

        view.setOnAction(event -> {
            //vbox.getChildren().add(note.viewNotes("quotes", ));
            //for (int i = 0; i < 20; i++) {
            //I want to show all database results from viewNotes function
            //grid.add(note.viewNotes("quotes","",""), 0, 5, NUM_COLS, ROWSPAN);
            //}
        });

        ScrollPane scrollPane = new ScrollPane(vbox);
        scrollPane.setFitToWidth(true);


        grid.add(quote, 0, ROW_INDEX, NUM_COLS, ROWSPAN);

        grid.add(author, 0, 1, NUM_COLS, ROWSPAN);

        grid.add(post, 0, 2, NUM_COLS, ROWSPAN);

        grid.add(scrollPane, 0, 4, NUM_COLS, ROWSPAN);

        scene.getChildren().add( grid );
        return new Scene(scene, WIN_WIDTH, WIN_HEIGHT);
    }
}