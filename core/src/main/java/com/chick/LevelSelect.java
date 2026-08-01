// LevelSelect.java
package com.chick;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.audio.Music; // Import Music for background music
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

public class LevelSelect implements Screen {
    private Stage stage;
    private chick game;
    private Viewport viewport;

    // Textures for buttons and images
    private Texture blur;
    private Texture l1;
    private Texture l2;
    private Texture back;

    private Image blur_i;


    // Declare Music variable for new BGM

    public LevelSelect(chick game) {
        this.game = game;
        viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        stage = new Stage(viewport);



        // Load textures
        l1 = new Texture(Gdx.files.internal("c7.png"));
        l2 = new Texture(Gdx.files.internal("c8.png"));
        blur = new Texture(Gdx.files.internal("c5.png"));
        back = new Texture(Gdx.files.internal("c6.png"));

        // Create buttons for l1, l2, and back
        ImageButton l1_Button = new ImageButton(new TextureRegionDrawable(l1));
        ImageButton back_button = new ImageButton(new TextureRegionDrawable(back));
        ImageButton l2_Button = new ImageButton(new TextureRegionDrawable(l2));

        // Set positions and sizes for buttons
        l1_Button.setPosition(38, 35);
        l1_Button.setSize(180, 380);
        l2_Button.setPosition(188, -90);
        //l2_Button.setSize(290, 630);
        l2_Button.setSize(290, 630);
        back_button.setPosition(30, 370);
        back_button.setSize(100, 150);

        l1_Button.getImage().setFillParent(false);
        l2_Button.getImage().setFillParent(false);
        back_button.getImage().setFillParent(false);

        // Add blur background image
        blur_i = new Image(blur);
        blur_i.setSize(stage.getWidth(), stage.getHeight());
        blur_i.setPosition(0, 0);
        stage.addActor(blur_i);

        // Add ImageButtons
        stage.addActor(l1_Button);
        stage.addActor(back_button);
        stage.addActor(l2_Button);

        // Add listeners to buttons
        l1_Button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(check.getcheck()){
                    game.getclickSound().play();
                }
                game.getBackgroundMusic().pause();
                game.getHK1().play();
                game.setScreen(new Loading(game, new Tutorial(game)));
            }
        });


        l2_Button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(check.getcheck()){
                    game.getclickSound().play();
                }
                game.getBackgroundMusic().pause();
                game.getHK2().play();
                game.setScreen(new Loading(game, new Gameplay2(game)));
            }
        });

        back_button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(check.getcheck()){
                    game.getclickSound().play();
                }
                game.setScreen(new Loading(game, new MapSelect(game) )); // Switch to MapSelect screen
            }
        });

        // Set the stage as the input processor
        Gdx.input.setInputProcessor(stage);
        addHoverEffect1(l1_Button,180, 380);
        addHoverEffect1(l2_Button,290, 630);
        addHoverEffect1(back_button,100, 150);
    }
    private void addHoverEffect1(final ImageButton button, final float originalWidth, final float originalHeight) {
        button.addListener(new InputListener() {

            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                if(check.getcheck()){
                    game.gethover().play();
                }
                // Enlarge the button on hover
                button.setSize(originalWidth + 20, originalHeight + 20);  // Increase size
                button.setPosition(button.getX() - 10, button.getY() - 10); // Adjust position to center
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                // Revert to original size
                button.setSize(originalWidth, originalHeight);  // Reset size
                button.setPosition(button.getX() + 10, button.getY() + 10); // Reset position
            }
        });
    }


    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        // Clear the screen with a white background
        Gdx.gl.glClearColor(1, 1, 1, 1);
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
    public void hide() {
        // Dispose of the new background music when hiding
//        if (newBackgroundMusic != null) {
//            newBackgroundMusic.stop();
//            newBackgroundMusic.dispose();
//        }
    }

    @Override
    public void dispose() {
        stage.dispose();
        l1.dispose();
        l2.dispose();
        blur.dispose();
        back.dispose();
        // Don't dispose of newBackgroundMusic here; do it in hide()
    }
}
