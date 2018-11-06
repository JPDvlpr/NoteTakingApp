package view;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;


public class MenuUI extends Application {

    public static final int WIN_SIZE = 300;
    private static final int NUM_COLS = 10;
    private static final double BUTTON_WIDTH = 20;
    private static final int COL_WIDTH = 45;
    private static final int TOP_RIGHT_BOTTOM_LEFT = 50;
    private static final int WIDTH = 500;
    private static final int HEIGHT = 500;
    private Label label;
    private HBox area;
    private Stage primaryStage;
    private String[] headers = {"Quotes", "Hyperlink", "todolist", "codesnippets"};
    private String[] bodies = {
            "quotes",
            "links",
            "todolist",
            "Codesnips",
    };

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
        System.out.println("intro scene");
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
        System.out.println("testing hyperlink");
        return newscene;
    }

    private Scene getToDoScene()
    {
        HBox buttonPanel = new HBox();
        buttonPanel.getChildren().addAll(getNotesButtons());
        ToDoNotes notes = new ToDoNotes();
        Scene newscene = (Scene) notes.getScene(buttonPanel);
        System.out.println("testing todo");
        return newscene;
    }

    private Scene getCodesnippetScene()
    {
        HBox buttonPanel = new HBox();
        buttonPanel.getChildren().addAll(getNotesButtons());
        CodeNotes notes = new CodeNotes();
        Scene newscene = (Scene) notes.getScene(buttonPanel);
        System.out.println("testing Codesnippet");
        return newscene;
    }


    public Button[] getNotesButtons()
    {
        char integer = 0;
        Button[] buttons = {new Button("Quote"), new Button("todo"), new Button("CodeSnippets"), new Button("Hyperlink")};
        Button[] notesButton = new Button[buttons.length];
        String[] buttonNames = {new String("quotenotes"), new String("todonotes"), new String("codesnippetnotes"), new String("hyperlinknotes")};

        for (int i = 0; i < buttons.length; i++) {
            String name = buttonNames[i];
            buttons[i].setOnAction(event ->
            {
                System.out.println("in event");
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

                    default:
                        break;
                }
            });
        }
        return buttons;
    }

    private GridPane buttons() {

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
        grid.add(area, 0, 5, NUM_COLS, 1);
        return grid;
    }
}