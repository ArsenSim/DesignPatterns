package behavioral.visitor;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Arsen on 5/15/2017
 */
public class VisitorPattern {
    public static void main(String[] args) {
        Car car = new Car(new CarFixerImpl());
        car.fixMe();
    }
}

interface CarFixer {
    void fix(Wheel wheel);
    void fix(Engine engine);
    void fix(Car car);
    void fix(CarPart carPart);
}

class CarFixerImpl implements CarFixer {
    public void fix(Wheel wheel) {
        System.out.println("Fixing Wheel");
    }
    public void fix(Engine engine) {
        System.out.println("Fixing Engine");
    }
    public void fix(Car car) {
        System.out.println("Fixing Car");
    }
    public void fix(CarPart carPart) {
        throw new UnsupportedOperationException("No implementation for " + carPart.getClass());
    }
}

abstract class CarPart {
    final CarFixer carFixer;
    CarPart(CarFixer carFixer) {
        this.carFixer = carFixer;
    }
    abstract void fixMe();
}

class Wheel extends CarPart {
    Wheel(CarFixer carFixer) {
        super(carFixer);
    }
    void fixMe() {
        carFixer.fix(this);
    }
}

class Engine extends CarPart {
    Engine(CarFixer carFixer) {
        super(carFixer);
    }
    void fixMe() {
        carFixer.fix(this);
    }
}

class Car extends CarPart {
    private final List<CarPart> carParts;
    Car(CarFixer carFixer) {
        super(carFixer);
        this.carParts = Arrays.asList(new Wheel(carFixer), new Engine(carFixer));
    }
    public void fixMe() {
        carParts.forEach(CarPart::fixMe);
        carFixer.fix(this);
    }

}
