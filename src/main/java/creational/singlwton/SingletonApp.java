package creational.singlwton;

import java.util.Random;

/**
 * Created by Arsen on 2/8/2017
 */
public class SingletonApp{
    public static void main(String[] args) {
        System.out.println(Singleton.instance());
        System.out.println(Singleton.instance());
        System.out.println(Singleton.instance());
        System.out.println(Singleton.instance());
    }
}

class Singleton {
    private static Singleton instance;
    private int id = new Random().nextInt(100);

    private Singleton() {
    }

    public static Singleton instance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }

    @Override
    public String toString() {
        return "Singleton{" +
                "id=" + id +
                '}';
    }
}