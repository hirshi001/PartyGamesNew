package com.hirshi001.game.common.plugin.pluginsecurity;

import com.badlogic.gdx.*;
import com.badlogic.gdx.utils.Clipboard;
import com.hirshi001.game.common.util.BetterApplicationLogger;
import logger.ConsoleColors;
import logger.DateStringFunction;

public class PluginApplicationWrapper implements Application{

    private Application application;
    private BetterApplicationLogger logger;

    public PluginApplicationWrapper(Application application){
        this.application = application;
        setApplicationLogger();
    }

    private void setApplicationLogger(){
        logger = new BetterApplicationLogger(ConsoleColors.GREEN, ConsoleColors.RED, System.out, System.err,
                new DateStringFunction(ConsoleColors.CYAN, "[","]"+ConsoleColors.RESET));

        logger.debugShort(true);
        logger.debug(ConsoleColors.BLUE, "[", "]" + ConsoleColors.RESET);

        //setApplicationLogger(logger); don't set
        System.setOut(logger);
    }


    @Override
    public ApplicationListener getApplicationListener() {
        return application.getApplicationListener();
    }

    @Override
    public Graphics getGraphics() {
        return application.getGraphics();
    }

    @Override
    public Audio getAudio() {
        return application.getAudio();
    }

    @Override
    public Input getInput() {
        return application.getInput();
    }

    @Override
    public Files getFiles() {
        return application.getFiles();
    }

    @Override
    public Net getNet() {
        return application.getNet();
    }

    @Override
    public void log(String tag, String message) {
        if (application.getLogLevel() >= LOG_INFO) {
            application.log(tag, message);
            logger.log(tag, message, 1);
        }
    }

    @Override
    public void log(String tag, String message, Throwable exception) {
        if (application.getLogLevel() >= LOG_INFO) {
            logger.log(tag, message, exception, 1);
        }
    }

    @Override
    public void error(String tag, String message) {
        if (application.getLogLevel() >= LOG_ERROR) {
            logger.error(tag, message, 1);
        }
    }

    @Override
    public void error(String tag, String message, Throwable exception) {
        if (application.getLogLevel() >= LOG_ERROR) {
            logger.error(tag, message, exception, 1);
        }
    }

    @Override
    public void debug(String tag, String message) {
        if (application.getLogLevel() >= LOG_DEBUG) {
            if (logger.getDebug()) {
                logger.debug(false);
                logger.log(tag, message, 1);
                logger.debug(true);
            } else {
                logger.log(tag, message, 1);
            }
        }
    }

    @Override
    public void debug(String tag, String message, Throwable exception) {
        if (application.getLogLevel() >= LOG_DEBUG) {
            if (logger.getDebug()) {
                logger.debug(false);
                logger.log(tag, message, exception, 1);
                logger.debug(true);
            } else {
                logger.log(tag, message, exception, 1);
            }
        }
    }

    @Override
    public void setLogLevel(int logLevel) {
        application.setLogLevel(logLevel);
    }

    @Override
    public int getLogLevel() {
        return application.getLogLevel();
    }

    @Override
    public void setApplicationLogger(ApplicationLogger applicationLogger) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ApplicationLogger getApplicationLogger() {
        return logger;
    }

    @Override
    public ApplicationType getType() {
        return application.getType();
    }

    @Override
    public int getVersion() {
        return application.getVersion();
    }

    @Override
    public long getJavaHeap() {
        return application.getJavaHeap();
    }

    @Override
    public long getNativeHeap() {
        return application.getNativeHeap();
    }

    @Override
    public Preferences getPreferences(String name) {
        return application.getPreferences(name);
    }

    @Override
    public Clipboard getClipboard() {
        return application.getClipboard();
    }

    @Override
    public void postRunnable(Runnable runnable) {
        application.postRunnable(runnable);
    }

    @Override
    public void exit() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void addLifecycleListener(LifecycleListener listener) {
        application.addLifecycleListener(listener);
    }

    @Override
    public void removeLifecycleListener(LifecycleListener listener) {
        application.addLifecycleListener(listener);
    }
}
