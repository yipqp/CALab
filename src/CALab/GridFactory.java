package CALab;

import mvc.*;
public class GridFactory implements AppFactory {
    @Override
    public Model makeModel() {
        return new Grid();
    }

    @Override
    public View makeView(Model m) {
        return new GridView(m);
    }

    @Override
    public String[] getEditCommands() {
        return new String[] {"RUN1", "RUN50", "POPULATE", "CLEAR"};
    }

    @Override
    public Command makeEditCommand(Model model, String type, Object source) {
        if(type.equals("RUN1"))
            return new RunCommand(1);
        if(type.equals("RUN50"))
            return new RunCommand(50);
        if(type.equals("POPULATE"))
            return new PopulateCommand();
        if(type.equals("CLEAR"))
            return new ClearCommand();

        return null;
    }

    @Override
    public String getTitle() {
        return "CALab";
    }

    @Override
    public String[] getHelp() {
        return new String[] {"Click RUN1 to run one iteration of the Cellular Automata",
                             "Click RUN50 to run 50 iterations of the Cellular Automata",
                             "Click POPULATE to set the state of each cell to a random value",
                             "Click CLEAR to set the state of all cells to the initial value"};
    }

    @Override
    public String about() {
        return "CALab Version 1.0. Copyright 2024 Richard Yip, Vats Panchal, Harjot Singh";
    }
}
