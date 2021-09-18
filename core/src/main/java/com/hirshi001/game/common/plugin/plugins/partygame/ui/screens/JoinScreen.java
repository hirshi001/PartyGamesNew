package com.hirshi001.game.common.plugin.plugins.partygame.ui.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.ScreenUtils;
import com.hirshi001.game.common.plugin.plugins.partygame.PartyGamePlugin;

public class JoinScreen extends GameScreen {

    Stage stage;
    TextField ipAddressTextField;
    Label ipAddressLabel;
    BitmapFont font;


    public JoinScreen(PartyGamePlugin game) {
        super(game);

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/OpenSans-Regular.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 64;
        parameter.shadowOffsetX = 2;
        parameter.shadowOffsetY = 2;
        parameter.shadowColor = Color.BLACK;
        font = generator.generateFont(parameter);
        generator.dispose();

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        stage.getCamera().position.setZero();
        stage.setDebugAll(true);
        stage.getViewport().setWorldSize(100, 100);
        ipAddressLabel = new Label("IP Address ", new Label.LabelStyle(font, Color.WHITE));
        ipAddressTextField = new TextField("", new TextField.TextFieldStyle(font, Color.WHITE, null, null, null));

        stage.addActor(ipAddressLabel);
        stage.addActor(ipAddressTextField);




        float width = 40F;


        ipAddressLabel.setScale(width/ipAddressLabel.getWidth());
        ipAddressLabel.setFontScale(width/ipAddressLabel.getWidth());
        ipAddressTextField.setScale(width/ipAddressTextField.getWidth());

        ipAddressLabel.setOrigin(ipAddressLabel.getWidth()/2F, ipAddressLabel.getHeight()/2F);
        ipAddressTextField.setOrigin(ipAddressTextField.getWidth()/2F, ipAddressTextField.getHeight()/2F);


        ipAddressLabel.setPosition(0, 0);
        ipAddressTextField.setPosition(50F-ipAddressTextField.getWidth()/2F, 25-ipAddressTextField.getHeight()/2F);


    }



    @Override
    public void render(float delta) {
        super.render(delta);
        ScreenUtils.clear(Color.RED);
        stage.getViewport().apply(false);
        stage.act(delta);
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
        font.dispose();
    }
}
