package com.chick;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Custom implements Screen {
    private Stage stage;
    private chick game;
    private Viewport viewport;

    // Textures for buttons
    private Texture d_map;
    private Texture f_map;
    private Texture s_map;
    private Texture blur;
    private Texture back;

    // Images for display
    private Image d_map_i;
    private Image s_map_i;
    private Image blur_i;



    public Custom(chick game) {
        this.game = game;
        viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        stage = new Stage(viewport);

        // Load textures
        d_map = new Texture(Gdx.files.internal("custom/3.png"));
        f_map = new Texture(Gdx.files.internal("custom/2.png"));
        s_map = new Texture(Gdx.files.internal("custom/4.png"));
        blur = new Texture(Gdx.files.internal("custom/1.png"));
        back = new Texture(Gdx.files.internal("c6.png")); // Fix for back button texture

        // Create buttons
        ImageButton f_Button = new ImageButton(new TextureRegionDrawable(f_map));
        ImageButton d_Button = new ImageButton(new TextureRegionDrawable(d_map));
        ImageButton s_Button = new ImageButton(new TextureRegionDrawable(s_map));
        ImageButton back_button = new ImageButton(new TextureRegionDrawable(back));

        // Set positions and sizes
        f_Button.setPosition(23, -80);
        f_Button.setSize(305, 610);

        d_Button.setPosition(213, -145);
        d_Button.setSize(205, 740);

        s_Button.setPosition(405, -75);
        s_Button.setSize(310, 610);

        back_button.setPosition(30, 330);
        back_button.setSize(100, 210);

        // Background image
        blur_i = new Image(blur);
        blur_i.setSize(stage.getWidth(), stage.getHeight());
        blur_i.setPosition(0, 0);
        stage.addActor(blur_i);

        // Non-clickable images
//        s_map_i = new Image(s_map);
//        s_map_i.setPosition(405, 54);
//        s_map_i.setSize(320, 350);
//        stage.addActor(s_map_i);

//        d_map_i = new Image(d_map);
//        d_map_i.setPosition(212, 54);
//        d_map_i.setSize(320, 350);
//        stage.addActor(d_map_i);

        // Add listeners to buttons
        f_Button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(check.getcheck()){
                    game.getclickSound().play();
                }
                game.setSelectedMap("12.png");
                game.setScreen(new Loading(game, new CustomTower(game)));  // Pass the next screen
            }
        });

        d_Button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(check.getcheck()){
                    game.getclickSound().play();
                }
                game.setSelectedMap("level3/map.png");
                game.setScreen(new Loading(game, new CustomTower(game)));  // Pass the next screen
            }
        });

        s_Button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(check.getcheck()){
                    game.getclickSound().play();
                }
                game.setSelectedMap("level4/map4.png");
                game.setScreen(new Loading(game, new CustomTower(game)));  // Pass the next screen
            }
        });

        back_button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(check.getcheck()){
                    game.getclickSound().play();
                }
                game.getLOU2().pause();
                game.setScreen(new Loading(game,new MainScreen(game)));
                game.getBackgroundMusic().play();
            }
        });

        // Add buttons to the stage
        stage.addActor(f_Button);
        stage.addActor(d_Button);
        stage.addActor(s_Button);
        stage.addActor(back_button);

        addHoverEffect1(f_Button, 305, 610);
        addHoverEffect1(d_Button, 205, 740);
        addHoverEffect1(s_Button, 310, 610);
//        addHoverEffect(d_map_i, 320, 350);

        // Set the stage as the input processor
        Gdx.input.setInputProcessor(stage);
    }

    // Method to add hover effect to ImageButton
    private void addHoverEffect1(final ImageButton button, final float originalWidth, final float originalHeight) {
        button.addListener(new InputListener() {
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                if(check.getcheck()){
                    game.gethover().play();
                }

                button.setSize(originalWidth + 20, originalHeight + 20);  // Increase size
                button.setPosition(button.getX() - 10, button.getY() - 10); // Adjust position to center
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                button.setSize(originalWidth, originalHeight);  // Reset size
                button.setPosition(button.getX() + 10, button.getY() + 10); // Reset position
            }
        });
    }

    // Method to add hover effect to images
    private void addHoverEffect(final Image image, final float originalWidth, final float originalHeight) {
        image.addListener(new InputListener() {
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                if(check.getcheck()){
                    game.gethover().play();
                }
                image.setSize(originalWidth + 20, originalHeight + 20);  // Increase size
                image.setPosition(image.getX() - 10, image.getY() - 10); // Adjust position to center
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                image.setSize(originalWidth, originalHeight);  // Reset size
                image.setPosition(image.getX() + 10, image.getY() + 10); // Reset position
            }
        });
    }

    @Override
    public void show() { }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 0); // White background
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Update and draw the stage
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
        if (d_map != null) d_map.dispose();
        if (f_map != null) f_map.dispose();
        if (s_map != null) s_map.dispose();
        if (blur != null) blur.dispose();
        if (back != null) back.dispose();

    }
}
