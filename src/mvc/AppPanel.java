package mvc;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class AppPanel extends JPanel implements Subscriber, ActionListener {

    protected AppFactory factory;
    protected Model model;
    protected View view;
    protected JPanel controlPanel;
    private JFrame frame;
    public static int FRAME_WIDTH = 500;
    public static int FRAME_HEIGHT = 300;

    public AppPanel(AppFactory factory) {
        this.factory = factory;

        model = factory.makeModel();
        model.subscribe(this);

        this.setLayout((new GridLayout(1, 2)));

        controlPanel = new JPanel();
        this.add(controlPanel);

        view = factory.makeView(model);
        this.add(view);

        frame = new SafeFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container cp = frame.getContentPane();
        cp.add(this);
        frame.setJMenuBar(this.createMenuBar());
        frame.setTitle(factory.getTitle());
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
    }

    protected JMenuBar createMenuBar() {
        JMenuBar result = new JMenuBar();
        JMenu fileMenu = Utilities.makeMenu("File", new String[]{"New", "Save", "SaveAs", "Open", "Quit"}, this);
        result.add(fileMenu);
        JMenu editMenu = Utilities.makeMenu("Edit", factory.getEditCommands(), this);
        result.add(editMenu);
        JMenu helpMenu = Utilities.makeMenu("Help", new String[]{"About", "Help"}, this);
        result.add(helpMenu);
        return result;
    }

    public void display() {
        frame.setVisible(true);
    }

    public void update() { }

    public void setModel(Model newModel) {
        this.model.unsubscribe(this);
        this.model = newModel;
        this.model.subscribe(this);
        view.setModel(this.model);
        model.changed();
    }

    public void actionPerformed(ActionEvent e) {
        String cmmd = e.getActionCommand();
        Object source = e.getSource();

        try {
            switch (cmmd) {
                case "Save": {
                    Utilities.save(model, false);
                    break;
                }

                case "SaveAs": {
                    Utilities.save(model, true);
                }

                case "Open": {
                    Model newModel = Utilities.open(model);
                    if (newModel != null) setModel(newModel);
                    break;
                }

                case "New": {
                    Utilities.saveChanges(model);
                    setModel(factory.makeModel());
                    model.setUnsavedChanges(false);
                    break;
                }

                case "Quit": {
                    Utilities.saveChanges(model);
                    System.exit(0);
                    break;
                }

                case "About": {
                      Utilities.inform(factory.about());
                      break;
                }

                case "Help": {
                     Utilities.inform(factory.getHelp());
                     break;
                }

                default: {
                     Command command = factory.makeEditCommand(model, cmmd, source);
                     if(command != null) {
                         command.execute();
                     } else {
                         throw new Exception("Unrecognized command: " + cmmd);
                     }
                }
            }

        } catch (Exception ex) {
            Utilities.error(ex);
        }
    }
}
