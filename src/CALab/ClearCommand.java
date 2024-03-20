package CALab;

import mvc.*;

public class ClearCommand extends Command {

    public ClearCommand(Model model) {
        super(model);
    }

    @Override
    public void execute() {
        // Reset all cells to dead (status 0)
        ((Grid) model).repopulate(false);
        ((Grid) model).resetTime();
    }
}
