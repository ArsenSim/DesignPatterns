package behavioral.iterator;

import java.util.Arrays;
import java.util.Iterator;
import java.util.stream.Stream;

/**
 * Created by Arsen on 5/10/2017
 */
public class IteratorApp {
    public static void main(String[] args) {
        SomeAggregate myObjs = new SomeAggregate(new MyObj(), new MyObj(), new MyObj());
        for (MyObj obj : myObjs) {
            System.out.println(obj);
        }
    }
}

class MyObj {
}

class SomeAggregate implements Iterable<MyObj> {
    private final MyObj[] objs;
    SomeAggregate(MyObj... objs) {
        this.objs = objs;
    }
    public Iterator<MyObj> iterator() {
        return Arrays.stream(objs).iterator();
    }
    public Stream<MyObj> stream() {
        return Stream.of(objs);
    }
}
