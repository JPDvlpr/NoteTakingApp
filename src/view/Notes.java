package view;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Notes
{
    public GridPane grid = new GridPane();
    private final int NUM_COLS = 4;
    private final int COL_WIDTH = 40;
    private final int BUTTON_PADDING = 10;
    private final int WIN_WIDTH = COL_WIDTH * 7;
    private final int WIN_HEIGHT = COL_WIDTH * 7;
    
    public void gridLayout() {
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(BUTTON_PADDING);
        grid.setVgap(BUTTON_PADDING);
        grid.setPadding(new Insets(BUTTON_PADDING));
        grid.setGridLinesVisible(true);
    }

    public Scene getScene(HBox defaultButtons)
    {
        VBox scene = new VBox(  );
        scene.getChildren().add( defaultButtons );
        gridLayout();
        
        ObservableList<ColumnConstraints> cols = grid.getColumnConstraints();
        for (int i = 1; i <= NUM_COLS; i++) {
            cols.add(new ColumnConstraints(COL_WIDTH));
        }
        scene.getChildren().add( grid );
        return new Scene(scene, WIN_WIDTH, WIN_HEIGHT);
    }
    
    @Override
    public String toString()
    {
        return "Notes{" +
                "grid=" + grid +
                ", NUM_COLS=" + NUM_COLS +
                ", COL_WIDTH=" + COL_WIDTH +
                ", BUTTON_PADDING=" + BUTTON_PADDING +
                ", WIN_WIDTH=" + WIN_WIDTH +
                ", WIN_HEIGHT=" + WIN_HEIGHT +
                '}';
    }
}