package behavioral.observer;

import com.sun.org.apache.xpath.internal.SourceTree;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Created by Arsen on 5/14/2017
 */
public class ObserverApp {
    public static void main(String[] args) {
        Subject entitySavedQueue = new Subject();
        new EmailSender(entitySavedQueue);
        new Logger(entitySavedQueue);

        String event = "Entity got saved to the queue";
        System.out.println("Publishing event");
        entitySavedQueue.publish(event);
        System.out.println("Done publishing event");
    }
}

abstract class Observer {
    Observer(Subject s) {
        s.subscribe(this);
    }
    abstract void onEvent(Object event);
}

class EmailSender extends Observer {
    EmailSender(Subject s) {
        super(s);
    }
    void onEvent(Object event) {
        System.out.println(
                String.format("Thread [%s]: Sending emails that [%s]",
                        Thread.currentThread().getName(),
                        event.toString())
        );
    }
}

class Logger extends Observer {
    Logger(Subject s) {
        super(s);
    }
    void onEvent(Object event) {
        System.out.println(
                String.format("Thread [%s]: Logging to the data base that [%s]",
                        Thread.currentThread().getName(),
                        event.toString())
        );
    }
}

class Subject {
    private Set<Observer> observers = new HashSet<>();
    void subscribe(Observer o) {
        observers.add(o);
    }
    void publish(Object event) {
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            observers.parallelStream().forEach(o -> o.onEvent(event));
        }).start();
    }
}
