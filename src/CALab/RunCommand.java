package CALab;

import mvc.*;

public class RunCommand extends Command {

    private int cycles;

    public RunCommand(Model model, int cycles) {
        super(model);
        this.cycles = cycles;
    }

    @Override
    public void execute() {
        ((Grid) model).updateLoop(cycles);
    }
}
