package LifeLab;
import CALab.*;
import mvc.*;

public class SocietyFactory extends GridFactory {
    @Override
    public Model makeModel() {
         return new Society();
    }

    @Override
    public View makeView(Model m) {
        return new GridView((Society) m);
    }

}
