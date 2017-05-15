package creational.builder;

/**
 * Created by Arsen on 2/6/2017
 */
public class BuilderApp {
    public static void main(String[] args) {
        Apple plutovka = AppleBuilder.init()
                .setAge(11)
                .setColor(Apple.Color.GREEN)
                .setName("Plutovka")
                .setSize(5)
                .setFlavour("Mmm...")
                .build();
        Apple zaraza = AppleBuilder.init()
                .setAge(3)
                .setColor(Apple.Color.RED)
                .setName("Zaraza")
                .setSize(3)
                .setFlavour("Yuk...")
                .build();
        System.out.println(plutovka);
    }
}

class Apple {
    private String name;
    private int size;
    private String flavour;
    private int age;
    private Color color;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getFlavour() {
        return flavour;
    }

    public void setFlavour(String flavour) {
        this.flavour = flavour;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public static enum Color {
        RED, YELLOW, GREEN
    }

    @Override
    public String toString() {
        return "Apple{" +
                "name='" + name + '\'' +
                ", size=" + size +
                ", flavour='" + flavour + '\'' +
                ", age=" + age +
                ", color=" + color +
                '}';
    }
}

class AppleBuilder {
    private final Apple apple;

    private AppleBuilder(Apple apple) {
        this.apple = apple;
    }

    static AppleBuilder init() {
        return new AppleBuilder(new Apple());
    }

    AppleBuilder setName(String name) {
        apple.setName(name);
        return this;
    }

    AppleBuilder setSize(int size) {
        apple.setSize(size);
        return this;
    }

    AppleBuilder setFlavour(String flavour) {
        apple.setFlavour(flavour);
        return this;
    }

    AppleBuilder setAge(int age) {
        apple.setAge(age);
        return this;
    }

    AppleBuilder setColor(Apple.Color color) {
        apple.setColor(color);
        return this;
    }

    Apple build() {
        return apple;
    }
}

