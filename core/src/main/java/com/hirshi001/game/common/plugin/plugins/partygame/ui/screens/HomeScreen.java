package com.hirshi001.game.common.plugin.plugins.partygame.ui.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.hirshi001.game.common.plugin.plugins.partygame.PartyGamePlugin;

public class HomeScreen extends GameScreen {


    Stage stage;
    TextButton hostButton;
    TextButton joinButton;
    BitmapFont font;
    Skin skin;

    public HomeScreen(PartyGamePlugin game) {
        super(game);

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/OpenSans-Regular.ttf"));
        FreeTypeFontParameter parameter = new FreeTypeFontParameter();
        parameter.size = 32;
        parameter.shadowOffsetX = 2;
        parameter.shadowOffsetY = 2;
        parameter.shadowColor = Color.BLACK;
        font = generator.generateFont(parameter);
        generator.dispose();

        skin = new Skin(Gdx.files.internal("uiskin.json"));
        skin.add("default-font", font, BitmapFont.class);

        stage = new Stage(new FitViewport(100, 100));
        Gdx.input.setInputProcessor(stage);

        stage.setDebugAll(true);
        hostButton = new TextButton("Host a Game", new TextButton.TextButtonStyle(null, null, null, font));
        joinButton = new TextButton("Join a Room", new TextButton.TextButtonStyle(null, null, null, font));

        hostButton.addAction(animation());
        joinButton.addAction(animation());

        hostButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                game.setScreen(new HostScreen(game));
                dispose();
            }
        });

        joinButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                game.setScreen(new JoinScreen(game));
                dispose();
            }
        });


        stage.addActor(hostButton);
        stage.addActor(joinButton);
        stage.setDebugAll(true);


        float width = 40F;

        hostButton.setTransform(true);
        joinButton.setTransform(true);

        hostButton.setScale(width/hostButton.getWidth());
        joinButton.setScale(width/joinButton.getWidth());

        hostButton.setOrigin(hostButton.getWidth()/2F, hostButton.getHeight()/2F);
        joinButton.setOrigin(hostButton.getWidth()/2F, hostButton.getHeight()/2F);


        hostButton.setPosition(50F-hostButton.getWidth()/2F, 75-hostButton.getHeight()/2F);
        joinButton.setPosition(50F-joinButton.getWidth()/2F, 25-joinButton.getHeight()/2F);
    }



    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.RED);
        stage.act(delta);
        stage.getViewport().apply(true);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height);
    }

    @Override
    public void show() {    }

    @Override
    public void hide() {    }

    @Override
    public void pause() {   }

    @Override
    public void resume() {  }

    @Override
    public void dispose() {
        stage.dispose();
        font.dispose();
    }


    private static Action animation(){
        float halfDuration = 0.5F, halfAmount = 20F;
        float scaleAmount = 0.25F, scaleDuration = 1;

        Action action =
                Actions.sequence(
                        Actions.repeat(2,
                                Actions.sequence(
                                        Actions.repeat(3, spinAction(halfDuration, halfAmount)),
                                        Actions.parallel(spinAction(halfDuration, halfAmount), growShrinkAction(scaleAmount, scaleDuration))
                                )
                        ),
                        spinAction(halfDuration, halfAmount),
                        Actions.parallel(
                                spinAction(halfDuration, halfAmount),
                                Actions.fadeOut(halfDuration*2)
                        ),
                        Actions.parallel(
                                spinAction(halfDuration, halfAmount),
                                Actions.fadeIn(halfDuration*2)
                        )

                );

        return Actions.repeat(-1, action);
    }

    private static SequenceAction spinAction(float halfDuration, float halfAmount){
        return Actions.sequence(
                Actions.rotateBy(halfAmount, halfDuration),
                Actions.rotateBy(-2*halfAmount, 2*halfDuration),
                Actions.rotateBy(halfAmount, halfDuration),
                Actions.rotateTo(0)
        );
    }

    private static SequenceAction growShrinkAction(float scaleAmount, float scaleDuration){
        return Actions.sequence(Actions.scaleBy(scaleAmount, scaleAmount, scaleDuration),
                Actions.scaleBy(-scaleAmount, -scaleAmount, scaleDuration));
    }
}
