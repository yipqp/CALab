package CALab;

import javax.swing.*;

import mvc.*;
import java.awt.*;

public class GridView extends View {
    private CellView cellViews[][];
    public GridView(Model model) {
        super(model);
        Grid grid = (Grid) model;
        int size = grid.getDim();

        this.setLayout((new GridLayout(size, size)));
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                Cell cell = grid.getCell(i, j);
                cell.row = i;
                cell.col = j;
                cellViews[i][j] = new CellView(cell);
                this.add(cellViews[i][j]);
            }
        }
    }

    public void update(String msg, Object oldState, Object newState) {
        // call update method of each CellView
        for (CellView[] row : cellViews) {
            for(CellView cView : row) {
                cView.update(msg, oldState, newState);
            }
        }
    }

}
