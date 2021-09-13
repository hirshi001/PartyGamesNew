package com.hirshi001.game.lwjgl3;

import com.badlogic.gdx.*;
import com.badlogic.gdx.backends.lwjgl3.*;
import com.badlogic.gdx.backends.lwjgl3.audio.Lwjgl3Audio;
import com.badlogic.gdx.utils.Clipboard;
import com.hirshi001.game.PartyGames;
import com.hirshi001.game.common.plugin.pluginsecurity.PluginManager;
import com.hirshi001.game.common.util.BetterApplicationLogger;
import logger.ConsoleColors;
import logger.DateStringFunction;

public class Lwjgl3PluginApplication extends Lwjgl3Application {

    private PluginManager manager;

    public Lwjgl3PluginApplication(PartyGames listener, PluginManager manager, Lwjgl3ApplicationConfiguration config) {
        super(listener, config);
        setApplicationLogger(createApplicationLogger());
        this.manager = manager;
    }

    protected ApplicationLogger createApplicationLogger(){
        BetterApplicationLogger logger = new BetterApplicationLogger(ConsoleColors.GREEN, ConsoleColors.RED, System.out, System.err,
                new DateStringFunction(ConsoleColors.CYAN, "[","]"+ConsoleColors.RESET));

        logger.debugShort(true);
        logger.debug(ConsoleColors.BLUE, "[", "]" + ConsoleColors.RESET);
        setApplicationLogger(logger);
        setApplicationLogger(logger);
        return logger;
    }

    public Lwjgl3PluginApplication(ApplicationListener listener, Lwjgl3ApplicationConfiguration config) {
        super(listener, config);
    }

    @Override
    protected void loop() {
        super.loop();
    }

    @Override
    protected void cleanupWindows() {
        super.cleanupWindows();
    }

    @Override
    protected void cleanup() {
        super.cleanup();
    }

    @Override
    public ApplicationListener getApplicationListener() {
        return super.getApplicationListener();
    }

    @Override
    public Graphics getGraphics() {
        return super.getGraphics();
    }

    @Override
    public Audio getAudio() {
        return super.getAudio();
    }

    @Override
    public Input getInput() {
        return super.getInput();
    }

    @Override
    public Files getFiles() {
        return super.getFiles();
    }

    @Override
    public Net getNet() {
        return super.getNet();
    }

    @Override
    public void debug(String tag, String message) {
        super.debug(tag, message);
    }

    @Override
    public void debug(String tag, String message, Throwable exception) {
        super.debug(tag, message, exception);
    }

    @Override
    public void log(String tag, String message) {
        super.log(tag, message);
    }

    @Override
    public void log(String tag, String message, Throwable exception) {
        super.log(tag, message, exception);
    }

    @Override
    public void error(String tag, String message) {
        super.error(tag, message);
    }

    @Override
    public void error(String tag, String message, Throwable exception) {
        super.error(tag, message, exception);
    }

    @Override
    public void setLogLevel(int logLevel) {
        super.setLogLevel(logLevel);
    }

    @Override
    public int getLogLevel() {
        return super.getLogLevel();
    }

    @Override
    public void setApplicationLogger(ApplicationLogger applicationLogger) {
        super.setApplicationLogger(applicationLogger);
    }

    @Override
    public ApplicationLogger getApplicationLogger() {
        return super.getApplicationLogger();
    }

    @Override
    public ApplicationType getType() {
        return super.getType();
    }

    @Override
    public int getVersion() {
        return super.getVersion();
    }

    @Override
    public long getJavaHeap() {
        return super.getJavaHeap();
    }

    @Override
    public long getNativeHeap() {
        return super.getNativeHeap();
    }

    @Override
    public Preferences getPreferences(String name) {
        return super.getPreferences(name);
    }

    @Override
    public Clipboard getClipboard() {
        return super.getClipboard();
    }

    @Override
    public void postRunnable(Runnable runnable) {
        super.postRunnable(runnable);
    }

    @Override
    public void exit() {
        super.exit();
    }

    @Override
    public void addLifecycleListener(LifecycleListener listener) {
        super.addLifecycleListener(listener);
    }

    @Override
    public void removeLifecycleListener(LifecycleListener listener) {
        super.removeLifecycleListener(listener);
    }

    @Override
    public Lwjgl3Audio createAudio(Lwjgl3ApplicationConfiguration config) {
        return super.createAudio(config);
    }

    @Override
    public Lwjgl3Input createInput(Lwjgl3Window window) {
        return super.createInput(window);
    }

    @Override
    protected Files createFiles() {
        return super.createFiles();
    }

    @Override
    public Lwjgl3Window newWindow(ApplicationListener listener, Lwjgl3WindowConfiguration config) {
        return super.newWindow(listener, config);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }
}
