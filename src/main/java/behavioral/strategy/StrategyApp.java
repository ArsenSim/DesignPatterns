package behavioral.strategy;

import org.jetbrains.annotations.NotNull;

import java.util.LinkedList;

import static java.util.Collections.sort;

/**
 * Created by Arsen on 5/15/2017
 */
public class StrategyApp {
}

interface CostCalculator {
    double calculate(Item item);
}

class BasicCostCalculator implements CostCalculator{
    public double calculate(Item item) {
        return item.cost();
    }
}

class SpecialOfferCostCalculator implements CostCalculator {
    public double calculate(Item item) {
        return item.cost() * 0.9;
    }
}

interface Item extends Comparable<Item> {
    double cost();
    double importance();
}

abstract class AbstractItem implements Item {
    public int compareTo(@NotNull Item o) {
        if (o.importance() > importance()) {
            return -1;
        }
        if (o.importance() < importance()) {
            return 1;
        }
        return 0;
    }
}

class Apple extends AbstractItem{
    public double cost() {
        return 10D;
    }
    public double importance() {
        return 3D;
    }
}

class Laptop extends AbstractItem {
    public double cost() {
        return 10000D;
    }
    public double importance() {
        return 1D;
    }
}

class Customer {
    private double money;
    private CostCalculator costCalculator;
    private LinkedList<Item> cart;

    public Customer(double money, CostCalculator costCalculator) {
        this.money = money;
        this.costCalculator = costCalculator;
        emptyCart();
    }

    public void addToCart(Item item) {
        cart.add(item);
    }

    private void removeItemsUntilNotBroke() {
        sort(cart);
        while (money < howMuchToPay()) {
            cart.removeFirst();
            removeItemsUntilNotBroke();
        }
    }

    double pay() {
        double toPay = howMuchToPay();
        if (money < toPay) {
            removeItemsUntilNotBroke();
        }
        money -= toPay;
        emptyCart();
        return money;
    }

    private double howMuchToPay() {
        return cart.stream().map(costCalculator::calculate).mapToDouble(v -> v).sum();
    }

    private void emptyCart() {
        cart = new LinkedList<>();
    }
}
