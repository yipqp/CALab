package CALab;

import mvc.AppFactory;
import mvc.AppPanel;

import javax.swing.*;
import java.awt.*;

public class GridPanel extends AppPanel {
    private JButton run1, run50, populate, clear;
    private JPanel r1, r50, p, c;
    public GridPanel(AppFactory factory) {
        super(factory);
        controlPanel.setLayout((new GridLayout(2,2)));
        r1 = new JPanel();
        r50 = new JPanel();
        p = new JPanel();
        c = new JPanel();

        run1 = new JButton("RUN1");
        run1.addActionListener(this);
        r1.add(run1);

        run50 = new JButton("RUN50");
        run50.addActionListener(this);
        r50.add(run50);

        populate = new JButton("populate");
        populate.addActionListener(this);
        p.add(populate);

        clear = new JButton("clear");
        clear.addActionListener(this);
        c.add(clear);

        controlPanel.add(r1);
        controlPanel.add(r50);
        controlPanel.add(p);
        controlPanel.add(c);
    }

}
