package creational.lazyinitialization;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Arsen on 2/7/2017
 */
public class LazyInitializationApp {
    public static void main(String[] args) {
        List<Something> things = Arrays.stream(Color.values())
                .map(Something.LazyMultitonFactory::instance)
                .collect(Collectors.toList());
        things.add(Something.LazyMultitonFactory.instance(Color.BLACK));
        things.add(Something.LazyMultitonFactory.instance(Color.BLUE));
        things.forEach(System.out::println);
    }
}

enum Color {
    RED, BLUE, BLACK
}

class Something {
    private int id;
    private Color color;

    private Something(Color color) {
        this.color = color;
        id = new Random().nextInt(10);
    }

    static class LazyMultitonFactory {
        private static Map<Color, Something> stuff = new HashMap<>();

        static Something instance(Color color) {
            if (!stuff.containsKey(color)) {
                Something something = new Something(color);
                stuff.put(color, something);
            }
            return stuff.get(color);
        }
    }

    @Override
    public String toString() {
        return "Something{" +
                "id=" + id +
                ", color=" + color +
                '}';
    }
}
