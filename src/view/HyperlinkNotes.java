package view;

import controller.NoteAppController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import model.NotePair;
import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

/**
 * Hyperlink notes is one option of notes the user
 * can choose from to create
 */
class HyperlinkNotes
{
    private Notes note = new Notes();
    private GridPane grid = new GridPane();
    private NoteAppController controller = new NoteAppController();

    private void gridLayout(){
        note.gridLayout();
        grid.setAlignment(Pos.CENTER);
        //grid.setGridLinesVisible(true);
        grid.setId("hyperlink-grid");
        int BUTTON_PADDING = 10;
        grid.setHgap(BUTTON_PADDING);
        grid.setVgap(BUTTON_PADDING);
        grid.setPadding(new Insets(BUTTON_PADDING));
    }

    /**
     * the content of the hyperlink notes
     */
    Scene getScene(HBox buttonPanel) {
        final int COL_WIDTH = 40;
        final int WIN_WIDTH = COL_WIDTH * 12;
        final int WIN_HEIGHT = COL_WIDTH * 12;
        double BUTTON_WIDTH = 40;
        int NUM_COLS = 4;
        int ROW_INDEX = 0;
        int ROWSPAN = 1;
    
        VBox scene = new VBox(  );
        scene.getChildren().add( buttonPanel );
        gridLayout();
    
        Hyperlink myHyperlink = new Hyperlink();
        myHyperlink.setText("My Link Text");
        
        
        TextField hyperlinkName = new TextField();
        hyperlinkName.setId("textField");
        hyperlinkName.setPromptText("Hyperlink Title");
        
        hyperlinkName.setFont(Font.font("Helvetica", FontPosture.ITALIC, 14));
        hyperlinkName.setMaxHeight(BUTTON_WIDTH);
        hyperlinkName.setId("hyperlink-name");
    
        hyperlinkName.textProperty().addListener((observable, oldValue, newValue) ->
                System.out.println("Name: " + newValue) );

        TextField hyperlink = new TextField();
        hyperlink.setId("textField");
        hyperlink.setPromptText("Hyperlink");
        
        hyperlink.setFont(Font.font("Helvetica", FontPosture.ITALIC, 14));
        hyperlink.setMaxHeight(BUTTON_WIDTH);
        hyperlink.setId("hyperlink");

        hyperlink.textProperty().addListener((observable, oldValue, newValue) ->
                System.out.println("Hyperlink: " + newValue) );

        Button post = new Button("PostLink");
        post.setMaxHeight(BUTTON_WIDTH);
        post.setId("post");

        Button view = new Button("View Hyperlinks");
        post.setMaxHeight(BUTTON_WIDTH);
        post.setId("view");

        VBox vbox = new VBox();
        ScrollPane scrollPane = new ScrollPane(vbox);
        scrollPane.setFitToWidth(true);

        view.setOnAction(event -> {
            List<NotePair> list;

            list = controller.handleSelectNote("hyperlink");
            System.out.println(list);

            for (NotePair noteList : list) {

                TextField titleField = new TextField();

                Button hyperlinkField = new Button();
                hyperlinkField.setOnAction( event1 ->
                        openUrl( noteList.getOther()) );
                HBox noteField = new HBox();

                titleField.setText(noteList.getBody());
                
                hyperlinkField.setText(noteList.getOther());
                noteField.getChildren().addAll(titleField, hyperlinkField);
                vbox.getChildren().addAll(noteField);
            }

        });
        post.setOnAction(event -> controller.handleNewNote("hyperlink", hyperlinkName.getText(), hyperlink.getText()) );


        grid.add(hyperlinkName, 0, ROW_INDEX, NUM_COLS, ROWSPAN);

        grid.add(hyperlink, 0, 1, NUM_COLS, ROWSPAN);

        grid.add(post, 0, 2, NUM_COLS, ROWSPAN);

        grid.add(view, 0, 3, NUM_COLS, ROWSPAN);

        grid.add(scrollPane, 0, 4, NUM_COLS, ROWSPAN);

        scene.getChildren().add( grid );
    
        return new Scene(scene, WIN_WIDTH, WIN_HEIGHT);
    }
    
    private void openUrl(String url) {
        try {
            Desktop.getDesktop().browse(new URL("http://"+url).toURI());
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    
    }
}