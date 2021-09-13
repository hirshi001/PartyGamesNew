package com.hirshi001.game.common.util;

import com.badlogic.gdx.ApplicationLogger;
import logger.ConsoleColors;
import logger.Logger;

import java.io.PrintStream;
import java.util.function.Supplier;

public class BetterApplicationLogger extends Logger implements ApplicationLogger {


    private final Object lock;
    private String tagColor, warnTagColor;

    @SafeVarargs
    public BetterApplicationLogger(String tagColor, String warnTagColor, PrintStream out, PrintStream err, Supplier<String>... stringSuppliers) {
        super(out, err, stringSuppliers);
        this.tagColor = tagColor;
        this.warnTagColor = warnTagColor;
        lock = new Object();
    }

    @Override
    public void log(String tag, String message) {
        super.log(tagColor + "["+tag+"] " + ConsoleColors.RESET + message, 2);
    }

    @Override
    public void log(String tag, String message, Throwable exception) {
        super.log(tagColor + "["+tag+"] " + ConsoleColors.RESET + message, 2);
        exception.printStackTrace(this);

    }
    @Override
    public void error(String tag, String message) {
        super.log(warnTagColor + "["+tag+"] " + ConsoleColors.RESET + message, 2);
    }

    @Override
    public void error(String tag, String message, Throwable exception) {
        super.log(warnTagColor + "["+tag+"] " + ConsoleColors.RESET + message, 2);
        exception.printStackTrace(this);
    }

    @Override
    public void debug(String tag, String message) {
        synchronized (lock){
            boolean debug = getDebug();
            debug();
            super.log(tagColor + "["+tag+"] " + ConsoleColors.RESET + message, 2);
            debug(debug);
        }
    }

    @Override
    public void debug(String tag, String message, Throwable exception) {
        synchronized (lock){
            boolean debug = getDebug();
            debug();
            super.log(tagColor + "["+tag+"] " + ConsoleColors.RESET + message, 2);
            debug(debug);
        }
    }
}
