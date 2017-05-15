package structural.adapter;

/**
 * Created by Arsen on 2/8/2017
 */
public class ObjectAdapterApp {
    public static void main(String[] args) {
        new RectangleImpl().display(1,1,3,3);
    }
}


interface LegacyRectangle {
    void display(int x, int y, int w, int h);
}

class LegacyRectangleImpl implements LegacyRectangle {
    @Override
    public void display(int x, int y, int w, int h) {
        System.out.println("displaying rectangle with width " + w + " and height " + h);
    }
}

interface Rectangle {
    void display(int x1, int y1, int x2, int y2);
}

class RectangleImpl implements Rectangle {
    private final LegacyRectangle legacyRectangleAdaptee = new LegacyRectangleImpl();

    @Override
    public void display(int x1, int y1, int x2, int y2) {
        int w = x2 - x1;
        int h = y2 - y1;
        legacyRectangleAdaptee.display(x1, y1, w, h);
    }
}