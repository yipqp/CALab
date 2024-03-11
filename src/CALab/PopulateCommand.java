package CALab;

import mvc.*;

public class PopulateCommand extends Command {

    private boolean random;

    public PopulateCommand(Model model, boolean random) {
        super(model);
        this.random = random;
    }

    @Override
    public void execute() {
        ((Grid) model).repopulate(random);
    }
}
