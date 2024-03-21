package LifeLab;

import CALab.GridPanel;
import mvc.AppFactory;
import mvc.AppPanel;

public class LifePanel extends GridPanel {
    public LifePanel(AppFactory factory) {
        super(factory);
    }

    public static void main(String[] args) {
        AppFactory factory = new SocietyFactory();
        AppPanel panel = new GridPanel(factory);
        panel.display();
    }

}
