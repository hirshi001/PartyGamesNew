package com.hirshi001.game.common.plugin.plugins.partygame.ui.screens;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import com.hirshi001.game.PartyGames;
import com.hirshi001.game.common.plugin.plugins.partygame.PartyGamePlugin;
import com.hirshi001.game.common.util.IpAddress;
import com.hirshi001.game.server.Server;

public class HostScreen extends GameScreen {

    Stage stage;
    Label ipLabel;

    boolean addressSet;

    public HostScreen(PartyGamePlugin game) {
        super(game);

        stage = new Stage();
        stage.getViewport().setWorldSize(100, 100);
        stage.setDebugAll(true);

        AssetManager assetManager = game.getAssetManager();
        FreeTypeFontGenerator generator = assetManager.get("fonts/OpenSans-Regular.ttf");
        FreeTypeFontParameter parameter = new FreeTypeFontParameter();
        parameter.size = 64;
        BitmapFont font = generator.generateFont(parameter);
        font.setUseIntegerPositions(false);


        ipLabel = new Label("Not Connected Yet", new Label.LabelStyle(font, Color.BLUE));

        resizeLabel();

        stage.addActor(ipLabel);
        String address = IpAddress.get();
        addressSet = false;
        if(address!=null){
            ipLabel.setText(address);
            addressSet = true;
        }
        startServer();
    }

    private void resizeLabel(){

        float labelWidth = 40;
        ipLabel.setOrigin(ipLabel.getWidth()/2F, ipLabel.getHeight()/2F);
        ipLabel.setFontScale(labelWidth/ipLabel.getWidth());
        ipLabel.setSize(labelWidth, ipLabel.getHeight()*labelWidth/ipLabel.getWidth());
        ipLabel.setPosition(50-ipLabel.getWidth()/2F, 50-ipLabel.getHeight()/2F);
        ipLabel.setAlignment(Align.center);
    }




    private void startServer(){

        Thread thread = new Thread(() -> {
            try {
                PartyGames.server = new Server(8080);
                Server server = PartyGames.server;
                server.startUp();
            }catch (Exception e){}
        });
        thread.start();
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        if(!addressSet){
            String address = IpAddress.get();
            if(address!=null){
                ipLabel.setText(address);
                addressSet = true;
            }
        }


        stage.act(delta);
        stage.getViewport().apply(true);

        ScreenUtils.clear(Color.RED);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        stage.getViewport().update(width, height);
    }

    @Override
    public void dispose() {
        super.dispose();
        stage.dispose();
    }
}
