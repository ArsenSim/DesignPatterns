package behavioral.command;

/**
 * Created by Arsen on 5/10/2017
 */
public class CommandApp {
    public static void main(String[] args) {
        new Executor(new OneCommand()).doStuff();
        new Executor(new OtherCommand()).doStuff();
    }
}

interface Command {
    void execute();
}

class OneCommand implements Command {
    public void execute() {
    }
}

class OtherCommand implements Command {
    public void execute() {
    }
}

class Executor {
    private final Command command;
    Executor(Command command) {
        this.command = command;
    }

    void doStuff() {
        //...
        command.execute();
        //...
    }
}