package behavioral.chainofresponsibility;

import javax.swing.text.html.Option;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.Optional;

/**
 * Created by Arsen on 5/10/2017
 */
public class ChainOfResponsibilityApp {
    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger();
        logger.log("ALL is GOOD", LogLevel.INFO);
        logger.log("Not sure if ALL is GOOD", LogLevel.WARNING);
        logger.log("ALL is BAD!!!", LogLevel.ERROR);
    }
}

enum LogLevel {INFO, WARNING, ERROR}

class LoggerFactory {
    static Logger getLogger() {
        ConsoleLogger consoleLogger = new ConsoleLogger(LogLevel.INFO, LogLevel.WARNING, LogLevel.ERROR);
        DataBaseErrorLogger dbLogger = new DataBaseErrorLogger("postgres");
        consoleLogger.setNext(dbLogger);
        return consoleLogger;
    }
}

abstract class Logger {
    private final EnumSet<LogLevel> lvls;
    private Logger next;

    Logger(LogLevel... lvls) {
        this.lvls = EnumSet.copyOf(Arrays.asList(lvls));
    }

    void log(String msg, LogLevel lvl) {
        if (lvls.contains(lvl)) {
            write(msg);
        }
        getNext().ifPresent(l -> l.log(msg, lvl));
    }

    protected abstract void write(String msg);

    void setNext(Logger next) {
        this.next = next;
    }

    private Optional<Logger> getNext() {
        return Optional.ofNullable(next);
    }
}


class ConsoleLogger extends Logger {
    ConsoleLogger(LogLevel... lvls) {
        super(lvls);
    }

    protected void write(String msg) {
        System.out.println(String.format("Writing to console: %s", msg));
    }
}

class DataBaseErrorLogger extends Logger {
    private final String dbName;

    DataBaseErrorLogger(String dbName) {
        super(LogLevel.ERROR);
        this.dbName = dbName;
    }

    protected void write(String msg) {
        System.out.println(String.format("Writing to database[%s]: %s", dbName, msg));
    }
}

