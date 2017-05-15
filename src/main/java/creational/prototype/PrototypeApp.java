package creational.prototype;

import java.util.Random;

/**
 * Created by Arsen on 2/7/2017
 */
public class PrototypeApp {
    public static void main(String[] args) {
        CellOrganismPrototype fred = new SingleCellOrganism("fred");
        for (int i = 0; i < 10; i ++) {
            System.out.println(fred.clone());
        }
    }
}

interface CellOrganismPrototype {
    CellOrganismPrototype clone();
}

class SingleCellOrganism implements CellOrganismPrototype {
    private String name;
    private int id = new Random().nextInt(100);

    SingleCellOrganism(String name) {
        this.name = name;
    }

    @Override
    public CellOrganismPrototype clone() {
        return new SingleCellOrganism(name);
    }

    @Override
    public String toString() {
        return "SingleCellOrganism{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}