package com.hirshi001.game.common.plugin.plugins.partygame.ui.screens;

import com.badlogic.gdx.ScreenAdapter;
import com.hirshi001.game.common.plugin.plugins.partygame.PartyGamePlugin;

public class GameScreen extends ScreenAdapter {

    PartyGamePlugin game;

    public GameScreen(PartyGamePlugin game) {
        super();
        this.game = game;
    }
}
