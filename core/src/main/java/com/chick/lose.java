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

public class lose implements Screen {
    private Stage stage;
    private chick game;
    private Viewport viewport;

    // Textures for buttons and images
    private Texture blur;
    private Texture h;
    private Texture r;
    private Texture Set;

    private Image blur_i;

    // Declare Music variable for new BGM

    public lose(chick game) {
        this.game = game;
        viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        stage = new Stage(viewport);

        // Load textures
        h = new Texture(Gdx.files.internal("dif/2.png"));
        r = new Texture(Gdx.files.internal("dif/3.png"));
        blur = new Texture(Gdx.files.internal("dif/1.png"));
        Set = new Texture(Gdx.files.internal("dif/4.png"));

        // Create buttons for l1, l2, and back
        ImageButton h_Button = new ImageButton(new TextureRegionDrawable(h));
        ImageButton s_button = new ImageButton(new TextureRegionDrawable(Set));
        ImageButton r_Button = new ImageButton(new TextureRegionDrawable(r));

        // Set positions and sizes for buttons
        h_Button.setPosition(168, 160);
        h_Button.setSize(80, 130);
        r_Button.setPosition(278, 130);
        r_Button.setSize(80, 130);
        s_button.setPosition(388, 160);
        s_button.setSize(80, 130);

        // Add blur background image
        blur_i = new Image(blur);
        blur_i.setSize(stage.getWidth(), stage.getHeight());
        blur_i.setPosition(0, 0);
        stage.addActor(blur_i);

        // Add ImageButtons
        stage.addActor(h_Button);
        stage.addActor(s_button);
        stage.addActor(r_Button);

        // Add listeners to buttons
        h_Button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(check.getcheck()){
                    game.getclickSound().play();
                }
                game.setScreen(new Loading(game, new MainScreen(game))); // Switch to Gameplay screen
            }
        });

        r_Button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(check.getcheck()){
                    game.getclickSound().play();
                }
                game.setScreen(new MapSelect(game));
            }
        });

        s_button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(check.getcheck()){
                    game.getclickSound().play();
                }
                game.setScreen(new Settings2(game) ); // Switch to MapSelect screen
            }
        });

        // Set the stage as the input processor
        Gdx.input.setInputProcessor(stage);
        addHoverEffect1(h_Button,80, 130);
        addHoverEffect1(r_Button,80, 130);
        addHoverEffect1(s_button,80, 130);
    }
    private void addHoverEffect1(final ImageButton button, final float originalWidth, final float originalHeight) {
        button.addListener(new InputListener() {

            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                game.gethover().play();
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
//        if (newBackgroundMusic != null) {
//            newBackgroundMusic.stop();
//            newBackgroundMusic.dispose();
//        }
    }

    @Override
    public void dispose() {
        stage.dispose();
        h.dispose();
        r.dispose();
        blur.dispose();
        Set.dispose();
    }
}
