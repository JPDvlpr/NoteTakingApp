package view;

import controller.NoteAppController;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.stage.Stage;
import model.DBNotes;
import model.NotePair;

import java.util.List;


public class MenuUI extends Application {

    public static final int WIN_SIZE = 300;
    private static final int NUM_COLS = 10;
    private static final double BUTTON_WIDTH = 20;
    private static final int COL_WIDTH = 45;
    private static final int TOP_RIGHT_BOTTOM_LEFT = 50;
    private static final int WIDTH = 500;
    private static final int HEIGHT = 500;
    private Label label;
    private Stage primaryStage;
    private HBox area;

    String[] buttonNames = {new String("quotenotes"), new String("todonotes"), new String("codesnippetnotes"), new String("hyperlinknotes"), new String("filter")};
    Button[] buttons = {new Button("Quote"), new Button("todo"), new Button("CodeSnippets"), new Button("Hyperlink"), new Button("filter")};
    NoteAppController controller = new NoteAppController();

    private DBNotes notes = new DBNotes();

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        primaryStage.setTitle("NotesApp");
        primaryStage.setScene(getIntroScene());
        primaryStage.show();
        this.primaryStage = primaryStage;
        
    }

    private Scene getStoryScreen(int i)
    {
        VBox panel = new VBox();
        Node elements = null;
        panel.getChildren().addAll(elements);
        return new Scene(panel, WIN_SIZE, WIN_SIZE);
    }

    private Scene getIntroScene()
    {
        HBox panel = new HBox();
        panel.getChildren().addAll(getNotesButtons());
        Scene scene = new Scene(panel, WIDTH, HEIGHT);
        return scene;
    }
    
    private Scene getNoteScene() {

        HBox buttonPanel = new HBox();
        buttonPanel.getChildren().addAll(getNotesButtons());
        QuotesNotes notes = new QuotesNotes();
        Scene scene = notes.getScene(buttonPanel);
        scene.getStylesheets().add("styles/styles.css");
        System.out.println("testing note scene");
        return scene;
    }
    
    private Scene getNewNoteScene()
    {
        HBox buttonPanel = new HBox();
        buttonPanel.getChildren().addAll(getNotesButtons());
        HyperlinkNotes notes = new HyperlinkNotes();
        Scene newscene = notes.getScene(buttonPanel);
        return newscene;
    }

    private Scene getToDoScene()
    {
        HBox buttonPanel = new HBox();
        buttonPanel.getChildren().addAll(getNotesButtons());
        ToDoNotes notes = new ToDoNotes();
        Scene newscene = (Scene) notes.getScene(buttonPanel);
        return newscene;
    }

    private Scene getCodesnippetScene()
    {
        HBox buttonPanel = new HBox();
        buttonPanel.getChildren().addAll(getNotesButtons());
        CodeNotes notes = new CodeNotes();
        Scene newscene = (Scene) notes.getScene(buttonPanel);
        return newscene;
    }

    private Scene getFilterScene() {

        HBox buttonPanel = new HBox();
        buttonPanel.getChildren().addAll(getNotesButtons());
        AllNotes notes = new AllNotes();
        Scene filterScene = (Scene) notes.getScene(buttonPanel);
        filterScene.getStylesheets().add("styles/styles.css");
        return filterScene;
    }

    public Button[] getNotesButtons()
    {
        char integer = 0;
        Button[] notesButton = new Button[buttons.length];

        for (int i = 0; i < buttons.length; i++) {
            String name = buttonNames[i];
            buttons[i].setOnAction(event ->
            {
                switch (name) {
                    case "quotenotes":
                        primaryStage.setScene(getNoteScene());
                        break;
                    case "hyperlinknotes":
                        primaryStage.setScene(getNewNoteScene());
                        break;
                    case "todonotes":
                        primaryStage.setScene(getToDoScene());
                        break;
                    case "codesnippetnotes":
                        primaryStage.setScene(getCodesnippetScene());
                        break;
                    case "filter":
                        primaryStage.setScene(getFilterScene());
                        break;
                    default:
                        break;
                }
            });
        }

        return buttons;
    }


    private GridPane buttons() {

        VBox vbox = new VBox();
        ScrollPane scrollPane = new ScrollPane(vbox);
        scrollPane.setFitToWidth(true);
        GridPane grid = new GridPane();

        grid.setId("feature");
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(TOP_RIGHT_BOTTOM_LEFT));

        ObservableList<ColumnConstraints> cols = grid.getColumnConstraints();
        for (int i = 0; i <= NUM_COLS; i++) {
            cols.add(new ColumnConstraints(COL_WIDTH));
        }
        label = new Label("0");
        area.setId("area");
        area.getChildren().add(label);
        area.setPrefHeight(BUTTON_WIDTH);
        grid.add(area, 0, 0, NUM_COLS, 1);
        grid.add(scrollPane, 0, 6, NUM_COLS, 1);

        return grid;
    }
}