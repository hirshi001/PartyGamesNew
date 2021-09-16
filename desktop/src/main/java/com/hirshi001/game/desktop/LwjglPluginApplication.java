package com.hirshi001.game.desktop;

import com.badlogic.gdx.*;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.backends.lwjgl.LwjglGraphics;
import com.badlogic.gdx.backends.lwjgl.LwjglInput;
import com.badlogic.gdx.backends.lwjgl.audio.LwjglAudio;
import com.badlogic.gdx.utils.Clipboard;
import com.hirshi001.game.PartyGames;
import com.hirshi001.game.common.plugin.pluginsecurity.PluginManager;
import com.hirshi001.game.common.util.BetterApplicationLogger;
import logger.ConsoleColors;
import logger.DateStringFunction;

import java.io.PrintStream;

public class LwjglPluginApplication extends LwjglApplication {

    private PluginManager manager;

    public LwjglPluginApplication(PartyGames listener, PluginManager manager, LwjglApplicationConfiguration config) {
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


    @Override
    protected void mainLoop() {
        super.mainLoop();
    }

    @Override
    public boolean executeRunnables() {
        manager.enablePluginMode();
        boolean result = super.executeRunnables();
        manager.disablePluginMode();
        return result;
    }

    @Override
    public ApplicationListener getApplicationListener() {
        return super.getApplicationListener();
    }

    @Override
    protected Files createFiles() {
        return super.createFiles();
    }

    @Override
    public LwjglAudio createAudio(LwjglApplicationConfiguration config) {
        return super.createAudio(config);
    }

    @Override
    public LwjglInput createInput(LwjglApplicationConfiguration config) {
        return super.createInput(config);
    }

    @Override
    public Audio getAudio() {
        return super.getAudio();
    }

    @Override
    public Files getFiles() {
        return super.getFiles();
    }

    @Override
    public LwjglGraphics getGraphics() {
        return super.getGraphics();
    }

    @Override
    public Input getInput() {
        return super.getInput();
    }

    @Override
    public Net getNet() {
        return super.getNet();
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
    public void stop() {
        super.stop();
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
    public void setLogLevel(int logLevel) {
        super.setLogLevel(logLevel);
    }

    @Override
    public int getLogLevel() {
        return super.getLogLevel();
    }

    @Override
    public void setApplicationLogger(ApplicationLogger applicationLogger) {
        if(applicationLogger instanceof PrintStream){
            System.setOut((PrintStream) applicationLogger);
        }
        super.setApplicationLogger(applicationLogger);
    }

    @Override
    public ApplicationLogger getApplicationLogger() {
        return super.getApplicationLogger();
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
}
