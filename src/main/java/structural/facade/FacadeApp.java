package structural.facade;

/**
 * Created by Arsen on 5/8/2017
 */
public class FacadeApp {
    public static void main(String[] args) {
        new LowLevelFacade(new LowLevel()).doStuff(); // easier than doing all this stuff
    }
}

class LowLevel {
    void xxx() {}
    void xxy() {}
    void xxz() {}
    void xyx() {}
}

class LowLevelFacade {
    private final LowLevel low;

    LowLevelFacade(LowLevel low) {
        this.low = low;
    }

    void doStuff() {
        low.xxx();
        low.xxy();
        low.xyx();
        low.xxz();
    }
}