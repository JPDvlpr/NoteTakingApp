package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;

/**
 * Notes is the layout of the other notes
 */
public class Notes
{
    private GridPane grid = new GridPane();
    private final int BUTTON_PADDING = 10;

    /**
     * gridlayout sets the alignment of the notes
     */
    public void gridLayout() {
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(BUTTON_PADDING);
        grid.setVgap(BUTTON_PADDING);
        grid.setPadding(new Insets(BUTTON_PADDING));
    }

    @Override
    public String toString()
    {
        final int COL_WIDTH = 40;
        final int NUM_COLS = 4;
        final int WIN_WIDTH = COL_WIDTH * 7;
        final int WIN_HEIGHT = COL_WIDTH * 7;

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