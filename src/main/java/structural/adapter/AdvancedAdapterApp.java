package structural.adapter;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Arsen on 2/8/2017
 */
public class AdvancedAdapterApp {
    public static void main(String[] args) {
        registerAdapters();
        FormatterAdapter trimmer = AdapterFactory.instance().getAdapter(SimpleStringProvider.class, SimpleStringTrimmer.class);
        Formatter formatter = trimmer.adapt(new SimpleStringProvider());
        SimpleStringConsumer consumer = new SimpleStringConsumer();
        consumer.setStringData(formatter.getStringData());
        System.out.println(consumer);

        FormatterAdapter decorator = AdapterFactory.instance().getAdapter(SimpleStringProvider.class, SimpleStringDecorator.class);
        formatter = decorator.adapt(new SimpleStringProvider());
        consumer.setStringData(formatter.getStringData());
        System.out.println(consumer);
    }

    private static void registerAdapters() {
        AdapterFactory.instance().registerAdapter(SimpleStringProvider.class, SimpleStringTrimmer.class, new SimpleStringTrimmerAdapter());
        AdapterFactory.instance().registerAdapter(SimpleStringProvider.class, SimpleStringDecorator.class, new SimpleStringDecoratorAdapter());
    }
}

class SimpleStringConsumer {
    private String data;

    void setStringData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "SimpleStringConsumer{" +
                "data='" + data + '\'' +
                '}';
    }
}

interface Formatter {
    String getStringData();
}

class SimpleStringProvider implements Formatter {
    @Override
    public String getStringData() {
        return this.getClass().getSimpleName() + " ";
    }
}

class SimpleStringTrimmer implements Formatter {
    private SimpleStringProvider classA;

    SimpleStringTrimmer(SimpleStringProvider classA) {
        this.classA = classA;
    }

    @Override
    public String getStringData() {
        return format(classA.getStringData());
    }

    private String format(String target) {
        return target.trim();
    }
}

class SimpleStringDecorator implements Formatter {
    private SimpleStringProvider classA;

    SimpleStringDecorator(SimpleStringProvider classA) {
        this.classA = classA;
    }

    @Override
    public String getStringData() {
        return format(classA.getStringData());
    }

    private String format(String target) {
        return "..." + target.trim() + "...";
    }
}



interface FormatterAdapter {
    Formatter adapt(Formatter adaptee);
}

class SimpleStringTrimmerAdapter implements FormatterAdapter {
    public Formatter adapt(Formatter adaptee) {
        return new SimpleStringTrimmer((SimpleStringProvider) adaptee);
    }
}

class SimpleStringDecoratorAdapter implements FormatterAdapter {
    public Formatter adapt(Formatter adaptee) {
        return new SimpleStringDecorator((SimpleStringProvider) adaptee);
    }
}

class AdapterFactory {
    private static AdapterFactory instance;

    static AdapterFactory instance() {
        if (instance == null) {
            instance = new AdapterFactory();
        }
        return instance;
    }

    private Map<Class<? extends Formatter>, Map<Class<? extends Formatter>, FormatterAdapter>> adapters = new HashMap<>();

    void registerAdapter(Class<? extends Formatter> from, Class<? extends Formatter> to, FormatterAdapter adapter) {
        Map<Class<? extends Formatter>, FormatterAdapter> toAdapter = adapters.get(from);
        if (toAdapter == null) {
            toAdapter = new HashMap<>();
        }
        toAdapter.put(to, adapter);
        adapters.put(from, toAdapter);
    }

    FormatterAdapter getAdapter(Class<? extends Formatter> from, Class<? extends Formatter> to) {
        return adapters.get(from).get(to);
    }

}