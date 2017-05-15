package creational.abstractfactory;

import java.util.AbstractMap.SimpleEntry;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Arsen on 2/6/2017
 */
public class AbstractFactoryApp {
    public static void main(String[] args) {
        System.out.println(AbstractEntityFactory.getFactory(Apple.class).build().id());
        System.out.println(AbstractEntityFactory.getFactory(Peach.class).build().id());
    }
}

interface Entity {
    public int id();
}

class Apple implements Entity {
    @Override
    public int id() {
        return 0;
    }
}

class Peach implements Entity {
    @Override
    public int id() {
        return 1;
    }
}

interface EntityFactory<T extends Entity> {
    T build();
}

class AppleFactory implements EntityFactory<Apple> {
    @Override
    public Apple build() {
        return new Apple();
    }
}

class PeachFactory implements EntityFactory<Peach> {
    @Override
    public Peach build() {
        return new Peach();
    }
}

class AbstractEntityFactory {
    private static final Map<Class<? extends Entity>, EntityFactory<? extends Entity>> FACTORIES = Stream.of(
                new SimpleEntry<>(Apple.class, new AppleFactory()),
                new SimpleEntry<>(Peach.class, new PeachFactory())
        ).collect(Collectors.toMap(SimpleEntry::getKey, SimpleEntry::getValue));

    static EntityFactory<? extends Entity> getFactory(Class<? extends Entity> entityType) {
        return FACTORIES.get(entityType);
    };
}