package com.hirshi001.game.common.plugin.plugins.partygame.ui.screens;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader;
import com.badlogic.gdx.utils.ScreenUtils;
import com.hirshi001.game.common.plugin.plugins.partygame.PartyGamePlugin;

public class LoadingScreen extends GameScreen {

    AssetManager assetManager;

    public LoadingScreen(AssetManager assetManager, PartyGamePlugin game) {
        super(game);
        this.assetManager = assetManager;
        queueAssets();
    }

    private void queueAssets(){
        FileHandleResolver resolver = new InternalFileHandleResolver();
        assetManager.setLoader(FreeTypeFontGenerator.class, new FreeTypeFontGeneratorLoader(resolver));

        //.ttf
        assetManager.load("fonts/OpenSans-Regular.ttf", FreeTypeFontGenerator.class);

        //images
        assetManager.load("badlogic.jpg", Texture.class);
    }


    @Override
    public void render(float delta) {
        System.out.println("Loading assets: " + assetManager.getProgress()*100F + "%. " + assetManager.getQueuedAssets() + " assets remaining");

        if(assetManager.update()){
            System.out.println("Finished loading assets");
            game.setScreen(new HomeScreen(game));
            dispose();
        }

        ScreenUtils.clear(Color.BLACK);

    }

    @Override
    public void resize(int width, int height) { }

    @Override
    public void show() {    }

    @Override
    public void hide() {    }

    @Override
    public void pause() {   }

    @Override
    public void resume() {  }

    @Override
    public void dispose() { }
}
