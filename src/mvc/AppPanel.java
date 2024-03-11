package mvc;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class AppPanel extends JPanel implements Subscriber, ActionListener {

    private AppFactory factory;
    private Model model;
    private JFrame frame;
    protected ControlPanel controlPanel;
    public View view;

    public AppPanel(AppFactory factory) {
        this.factory = factory;
        model = factory.makeModel();
        view = factory.makeView(model);
        controlPanel = new ControlPanel();
        this.setLayout((new GridLayout(1, 2)));
        this.add(controlPanel);
        this.add(view);
        model.subscribe(this);

        frame = new SafeFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container cp = frame.getContentPane();
        cp.add(this);
        frame.setJMenuBar(this.createMenuBar());
        frame.setTitle(factory.getTitle());
        frame.setSize(500, 300);
    }

    protected JMenuBar createMenuBar() {
        JMenuBar result = new JMenuBar();
        JMenu fileMenu = Utilities.makeMenu("File", new String[]{"New", "Save", "Open", "Quit"}, this);
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

    public void actionPerformed(ActionEvent e) {
        String cmmd = e.getActionCommand();
        Object source = e.getSource();

        try {
            switch (cmmd) {
                case "Save": {
                     String fName = Utilities.getFileName((String) null, false);
                     ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(fName));
                     os.writeObject(this.model);
                     os.close();
                     break;
                }

                case "Open": {
                     if (Utilities.confirm("Are you sure? Unsaved changes will be lost!")) {
                         String fName = Utilities.getFileName((String) null, true);
                         ObjectInputStream is = new ObjectInputStream(new FileInputStream(fName));
                         model = (Model) is.readObject();
                         view.setModel(model);
                         is.close();
                     }

                     break;
                }

                case "New": {
                    model = factory.makeModel();
                    view.setModel(model);
                    break;
                }

                case "Quit": {
                     System.exit(0);
                     break;
                }

                case "About": {
                      Utilities.inform(factory.about());
                      break;
                }

                case "Help": {
                     String[] cmmds = factory.getHelp();
                     Utilities.inform(cmmds);
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

    public class ControlPanel extends JPanel {}
}
