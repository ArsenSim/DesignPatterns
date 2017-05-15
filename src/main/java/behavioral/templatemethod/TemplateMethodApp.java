package behavioral.templatemethod;

/**
 * Created by Arsen on 5/15/2017
 */
public class TemplateMethodApp {
}

abstract class SomeClass {
    void procede() {
        doThis();
        doThat();
        doSomethingElse();
    }
    abstract void doThis();
    abstract void doThat();
    void doSomethingElse() {
    }
}

