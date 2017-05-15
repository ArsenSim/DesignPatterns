package concurrent.activeobject;

import java.util.concurrent.ForkJoinPool;

/**
 * Created by Arsen on 5/15/2017
 */
public class ActiveObjectApp {
}

class ActiveObject {
    private double val;
    private final ForkJoinPool dispatchQueue = new ForkJoinPool(3,
            ForkJoinPool.defaultForkJoinWorkerThreadFactory,
            null,
            true);

    public void doSomething() {
        dispatchQueue.execute(() -> {val = 1D;});
    }

    public void doSomethingElse() {
        dispatchQueue.execute(() -> {val = 2D;});
    }

}
