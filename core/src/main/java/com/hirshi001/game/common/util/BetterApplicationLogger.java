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

    public void log(String tag, String message, int depth){
        super.log(tagColor + "["+tag+"] " + ConsoleColors.RESET + message, depth+1);
    }

    @Override
    public void log(String tag, String message) {
        log(tag, message, 2);
    }

    public void log(String tag, String message, Throwable exception, int depth) {
        super.log(tagColor + "["+tag+"] " + ConsoleColors.RESET + message, depth+1);
        exception.printStackTrace(this);
    }

    @Override
    public void log(String tag, String message, Throwable exception) {
        log(tag, message, exception, 2);
    }

    @Override
    public void error(String tag, String message) {
        this.error(tag, message, 2);
    }

    public void error(String tag, String message, int depth) {
        super.log(warnTagColor + "["+tag+"] " + ConsoleColors.RESET + message, depth+1);
    }


    @Override
    public void error(String tag, String message, Throwable exception) {
        error(tag, message, exception, 2);
    }

    public void error(String tag, String message, Throwable exception, int depth) {
        super.log(warnTagColor + "["+tag+"] " + ConsoleColors.RESET + message, depth+1);
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
