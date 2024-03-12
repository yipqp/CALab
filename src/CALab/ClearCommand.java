package CALab;

import mvc.*;

public class ClearCommand extends Command {

    public ClearCommand(Model model) {
        super(model);
    }

    @Override
    public void execute() {
        ((Grid) model).repopulate(false); // Reset all cells to dead (status 0)
    }
}
