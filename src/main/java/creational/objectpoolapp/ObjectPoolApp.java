package creational.objectpoolapp;

import java.util.Arrays;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Created by Arsen on 2/7/2017
 */
public class ObjectPoolApp {
    public static void main(String[] args) {
        Thing.Pool pool = new Thing.Pool(5);
        for (int i = 0; i < 10; i++) {
            try {
                System.out.println(Thing.Pool.get());
            } catch (Thing.Pool.NoFreeThingsException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}

class Thing {
    private int id;

    Thing() {
        id = new Random().nextInt(10);
    }

    static class Pool {
        private static Map<Thing, Thing.Pool.ThingStatus> things;

        Pool(int size) {
            things = Arrays.stream(new Integer[size])
                    .map(i -> new Thing())
                    .collect(Collectors.toMap(t -> t, t -> Thing.Pool.ThingStatus.FREE));
        }

        public enum ThingStatus {
            FREE, IN_USE
        }

        static Thing get() throws NoFreeThingsException {
            for (Map.Entry<Thing, Thing.Pool.ThingStatus> e : things.entrySet()) {
                if (e.getValue() == Thing.Pool.ThingStatus.FREE) {
                    e.setValue(Thing.Pool.ThingStatus.IN_USE);
                    return e.getKey();
                }
            }
            throw new NoFreeThingsException("No free thing to give");
        }

        static class NoFreeThingsException extends Exception {
            NoFreeThingsException(String message) {
                super(message);
            }
        }
    }

    @Override
    public String toString() {
        return "Thing{" +
                "id=" + id +
                '}';
    }
}
