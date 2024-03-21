package CALab;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import mvc.*;

public class CellView extends JButton implements ActionListener, Subscriber {
    private Cell myCell;

    public CellView(Cell c) {
        this.setOpaque(true);
        myCell = c;
        if (c != null) {
            c.subscribe(this);
        }
        this.addActionListener(this);
    }

    public CellView() {
        this(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        myCell.nextState();
        update();
    }
    // called by notifySubscribers and GridView.update

    public void setCell(Cell cell) {
        myCell.unsubscribe(this);
        myCell = cell;
        if (cell != null) { cell.subscribe(this); }
    }
    @Override
    public void update() {
        setBackground(myCell.getColor());
        setBorder(BorderFactory.createLineBorder(Color.black)); // optional
        setText("" + myCell.getAmbience());
    }
}
