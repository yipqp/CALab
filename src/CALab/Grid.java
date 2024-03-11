package CALab;

import java.awt.*;
import java.util.*;
import java.io.*;
import mvc.*;
public abstract class Grid extends Model {
    static private int time = 0;
    protected int dim = 20;
    protected Cell[][] cells;
    public int getTime() { return time; }
    public Cell getCell(int row, int col) { return cells[row][col]; }
    public abstract Cell makeCell();

    public Grid(int dim) {
        this.dim = dim;
        cells = new Cell[dim][dim];
        populate();
    }

    public Grid() { this(20); }

    protected void populate() {
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                cells[i][j] = makeCell();
            }
        }

        changed();

        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                cells[i][j].neighbors = getNeighbors(cells[i][j], 1);
            }
        }
    }

    // called when Populate button is clicked
    public void repopulate(boolean randomly) {
        if (randomly) {
            for (int i = 0; i < dim; i++) {
                for (int j = 0; j < dim; j++) {
                    cells[i][j].reset(randomly);
                }
            }
        } else {
            for (int i = 0; i < dim; i++) {
                for (int j = 0; j < dim; j++) {
                    cells[i][j].reset(false);
                }
            }
        }

        changed();
    }

    public Set<Cell> getNeighbors(Cell asker, int radius) {
        Set<Cell> reachableNeighbors = new HashSet<>();

        for (int row = asker.row - radius; row < asker.row + radius; row++) {
            for (int col = asker.col - radius; col < asker.col + radius; col++) {
                if ((row >= 0 && row < dim) && (col >= 0 && col < dim) && (row != asker.row && col != asker.col)) {
                   reachableNeighbors.add(cells[row][col]);
                }
            }
        }

        return reachableNeighbors;
    }

    // overide these
    public int getStatus() { return 0; }
    public Color getColor() { return Color.GREEN; }

    // cell phases:

    public void observe() {
         for (int i = 0; i < dim; i++) {
             for (int j = 0; j < dim; j++) {
                cells[i][j].observe();
             }
         }

         changed();
    }

    public void interact() {
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                cells[i][j].interact();
            }
        }

        changed();
    }

    public void update() {
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                cells[i][j].update();
            }
        }

        changed();
    }

    public void updateLoop(int cycles) {
        observe();
        for(int cycle = 0; cycle < cycles; cycle++) {
            interact();
            update();
            observe();
            time++;
            System.out.println("time = " + time);
        }
    }
}