// Pause.java
package com.chick;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Pause implements Screen {
    private Stage stage;
    private chick game;
    private Viewport viewport;
    private Screen previousScreen;  // Variable to store the previous screen

    // Textures for buttons
    private Texture blur;
    private Texture c;
    private Texture s;
    private Texture mm;
    private Texture r;

    private Image blur_i;

    ImageButton cB;
    ImageButton sB;
    ImageButton mmB;
    ImageButton rB;

    public Pause(chick game, Screen previousScreen) {
        this.game = game;
        this.previousScreen = previousScreen;  // Store the previous screen
        viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        stage = new Stage(viewport);

        // Load textures for buttons
        c = new Texture(Gdx.files.internal("game/C.png"));
        s = new Texture(Gdx.files.internal("Settings.png"));
        mm = new Texture(Gdx.files.internal("game/MM.png"));
        blur = new Texture(Gdx.files.internal("game/1.png"));
        r = new Texture(Gdx.files.internal("game/R.png"));

        // Initialize buttons and their positions
        cB = new ImageButton(new TextureRegionDrawable(c));
        sB = new ImageButton(new TextureRegionDrawable(s));
        mmB = new ImageButton(new TextureRegionDrawable(mm));
        rB = new ImageButton(new TextureRegionDrawable(r));

        cB.setPosition(218, 305);
        cB.setSize(200, 70);

        rB.setPosition(237, 240);
        rB.setSize(160, 70);

        sB.setPosition(230, 175);
        sB.setSize(180, 70);

        mmB.setPosition(200, 115);
        mmB.setSize(240, 70);

        // Add blur background image
        blur_i = new Image(blur);
        blur_i.setSize(stage.getWidth() + 200, stage.getHeight());
        blur_i.setPosition(0, 0);
        stage.addActor(blur_i);

        // Add button listeners
        cB.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(check.getcheck()){
                    game.getclickSound().play();
                }
                game.setScreen(new MapSelect(game));  // Switch back to the previous screen
            }
        });

        sB.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(check.getcheck()){
                    game.getclickSound().play();
                }
                game.setScreen(new Settings2(game));  // Go to settings screen
            }
        });

        rB.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(check.getcheck()){
                    game.getclickSound().play();
                }
                game.setScreen(new MapSelect(game));  // Restart the gameplay
            }
        });

        mmB.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(check.getcheck()){
                    game.getclickSound().play();
                }
                game.setScreen(new Loading(game, new MainScreen(game)));  // Go to the Main Menu
            }
        });

        stage.addActor(cB);
        stage.addActor(rB);
        stage.addActor(mmB);
        stage.addActor(sB);

        Gdx.input.setInputProcessor(stage);
        addHoverEffect1(cB, 180, 70);
        addHoverEffect1(sB, 180, 70);
        addHoverEffect1(rB, 160, 70);
        addHoverEffect1(mmB, 240, 70);
    }

    private void addHoverEffect1(final ImageButton button, final float originalWidth, final float originalHeight) {
        button.addListener(new InputListener() {

            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                if(check.getcheck()){
                    game.gethover().play();
                }
                button.setSize(originalWidth + 20, originalHeight + 20);
                button.setPosition(button.getX() - 10, button.getY() - 10);
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                button.setSize(originalWidth, originalHeight);
                button.setPosition(button.getX() + 10, button.getY() + 10);
            }
        });
    }

    @Override
    public void show() {
        // No need to pause music here; it's handled in the button listener
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void pause() { }

    @Override
    public void resume() { }

    @Override
    public void hide() { }

    @Override
    public void dispose() {
        stage.dispose();
        c.dispose();
        s.dispose();
        mm.dispose();
        r.dispose();
        blur.dispose();
    }
}
