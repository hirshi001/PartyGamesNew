package com.hirshi001.game.common.plugin.plugins;

import com.badlogic.gdx.ApplicationListener;

public interface GamePlugin extends ApplicationListener {

    public boolean isFinished();

    public void forceQuit();

}
