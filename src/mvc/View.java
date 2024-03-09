package mvc;

import javax.swing.*;
import java.awt.*;

public class View extends JPanel implements Subscriber {
    protected Model model;

    public View(Model model) {
        this.model = model;
        model.subscribe(this);
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public void update() {
        repaint();
    }

}
