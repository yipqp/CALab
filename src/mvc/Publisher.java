package mvc;

import java.util.List;
import java.util.LinkedList;

public class Publisher {

    List<Subscriber> observers = new LinkedList<Subscriber>();

    public void subscribe(Subscriber s) { observers.add(s); }

    public void unsubscribe(Subscriber s) { observers.remove(s); }

    public void notifySubscribers() {
        for (Subscriber s: observers) {
            s.update();
        }
    }

}
