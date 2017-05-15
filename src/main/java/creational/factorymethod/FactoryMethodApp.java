package creational.factorymethod;

/**
 * Created by Arsen on 2/6/2017
 */
public class FactoryMethodApp {
    public static void main(String[] args) {
        Monkey monkey = new Monkey();
        monkey.eat();
        System.out.println(monkey);
    }
}

interface Food {
    int calories();
}

class Apple implements Food {
    @Override
    public int calories() {
        return 100;
    }
}

class Monkey extends Eater {
    @Override
    Food getFood() {
        return new Apple();
    }
}

abstract class Eater {
    private int consumed = 0;

    void eat() {
        Food food = getFood();
        consumed += food.calories();
    }

    abstract Food getFood();

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "consumed=" + consumed +
                '}';
    }
}
