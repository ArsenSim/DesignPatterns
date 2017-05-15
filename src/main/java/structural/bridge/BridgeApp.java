package structural.bridge;

import java.util.function.Function;

/**
 * Created by Arsen on 2/8/2017
 */
public class BridgeApp {
}

interface Action {
    void start();
}

class GoodAction implements Action {
    public void start() {
    }
}

class badAction implements Action {
    public void start() {
    }
}

abstract class Abstraction {
    private final Action action;
    protected final Function stopFunction;

    Abstraction(Action action, Function stopFunction) {
        this.action = action;
        this.stopFunction = stopFunction;
    }

    void start() {
        action.start();
    }

    abstract void stop();
}

class RegularObject extends Abstraction {

    protected RegularObject(Action action, Function stopFunction) {
        super(action, stopFunction);
    }

    void stop() {
        stopFunction.apply(this); //nvm
    }
}
