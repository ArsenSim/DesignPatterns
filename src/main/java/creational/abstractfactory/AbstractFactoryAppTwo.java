package creational.abstractfactory;

/**
 * Created by Arsen on 2/7/2017
 */
public class AbstractFactoryAppTwo {
    public static void main(String[] args) {
        AbstractGUIFactory gui;
        switch (getOS()) {
            case LINUX:
                gui = new LinGUIFactory();
                break;
            case WINDOWS:
                gui = new WinGUIFactory();
                break;
            default:
                throw new RuntimeException();
        }
        System.out.println(gui.button().getClass().getSimpleName());
        System.out.println(gui.textArea().getClass().getSimpleName());
    }

    private static AbstractGUIFactory.OS getOS() {
        return AbstractGUIFactory.OS.WINDOWS;
    }
}


interface Button {
}

interface TextArea {
}

class WinButton implements Button {
}

class WinTextArea implements TextArea {
}

class LinButton implements Button {
}

class LinTextArea implements TextArea {
}

abstract class AbstractGUIFactory {
    public abstract Button button();
    public abstract TextArea textArea();

    public enum OS {
        WINDOWS, LINUX, OSX
    }
}

class WinGUIFactory extends AbstractGUIFactory {
    @Override
    public Button button() {
        return new WinButton();
    }

    @Override
    public TextArea textArea() {
        return new WinTextArea();
    }
}

class LinGUIFactory extends AbstractGUIFactory {
    @Override
    public Button button() {
        return new LinButton();
    }

    @Override
    public TextArea textArea() {
        return new LinTextArea();
    }
}
