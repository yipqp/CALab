package CALab;

import mvc.Publisher;

import java.awt.*;
import java.util.*;
import java.io.*;

abstract class Cell extends Publisher implements Serializable {

    protected int row = 0, col = 0;
    protected Set<Cell> neighbors = new HashSet<Cell>();
    protected Grid myGrid = null;
    protected Cell partner = null;

    public void choosePartner() {
        if (partner == null && neighbors != null) {
            Cell[] neighborsArr = new Cell[8];
            int i = 0;
            for (Cell neighbor : neighbors) {
                neighborsArr[i] = neighbor;
                i++;
            }

            int randomIn = (int) (Math.random() * 8);
            for (int j = 0; j < 8; j++) {
                Cell neighbor = neighborsArr[randomIn % 8];
                if (neighbor != null && neighbor.partner == null) {
                    neighbor.partner = this;
                    partner = neighbor;
                    break;
                }
                randomIn++;
            }
        }
    }

    public void unpartner() {
        if (partner != null) {
            if (partner.partner != null) {
                partner.partner = null;
            }
            partner = null;
        }
    }

    // observer neighbors' states
    public abstract void observe();
    // interact with a random neighbor
    public abstract void interact();
    // update my state
    public abstract void update();
    // set status to status + 1 mod whatever
    public abstract void nextState();
    // set status to a random or initial value
    public abstract void reset(boolean randomly);
    public abstract int getStatus();
    public abstract Color getColor();

}