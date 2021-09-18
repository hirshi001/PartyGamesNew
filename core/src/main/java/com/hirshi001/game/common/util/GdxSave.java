package com.hirshi001.game.common.util;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.GL30;

public class GdxSave {


    public Application app;
    public Graphics graphics;
    public Audio audio;
    public Input input;
    public Files files;
    public Net net;

    public GL20 gl;
    public GL20 gl20;
    public GL30 gl30;

    public void save(){
        app = Gdx.app;
        graphics = Gdx.graphics;
        audio = Gdx.audio;
        input = Gdx.input;
        files = Gdx.files;
        net = Gdx.net;
        gl = Gdx.gl;
        gl20 = Gdx.gl20;
        gl30 = Gdx.gl30;
    }

    public void set(){
        Gdx.app = app;
        Gdx.graphics = graphics;
        Gdx.audio = audio;
        Gdx.input = input;
        Gdx.files = files;
        Gdx.net = net;
        Gdx.gl = gl;
        Gdx.gl20 = gl20;
        Gdx.gl30 = gl30;
    }
}
