// MainScreen.java
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
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.utils.viewport.Viewport;

public class credits implements Screen {
    private Stage stage;
    private chick game;
    private Viewport viewport;

    private Texture blur;
    private Texture back;


    private Image blur_i;
    private Image back_i;

    private Sound clickSound;
    private Sound hoverSound;


    public credits(chick game) {
        this.game = game;
        viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        stage = new Stage(viewport);

        clickSound = Gdx.audio.newSound(Gdx.files.internal("click.mp3"));
        hoverSound = Gdx.audio.newSound(Gdx.files.internal("hover.mp3"));



        blur = new Texture(Gdx.files.internal("credits/2.png"));

        back = new Texture(Gdx.files.internal("credits/1.png"));

        ImageButton back_button = new ImageButton(new TextureRegionDrawable(back));


        back_button.setPosition(30, 330);
        back_button.setSize(100, 210);


        blur_i = new Image(blur);
        blur_i.setSize(stage.getWidth(), stage.getHeight());
        blur_i.setPosition(0,0);
        stage.addActor(blur_i);

        // Create non-clickable CHICK image

        back_button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                clickSound.play();
                game.setScreen(new Settings(game));  // Pass the next screen
            }
        });

//        customButton.addListener(new ClickListener() {
//            @Override
//            public void clicked(InputEvent event, float x, float y) {
//                // Handle Custom button click
//                // For example, navigate to CustomScreen
//                // game.setScreen(new CustomScreen(game));
//                dispose();
//            }
//        });
//
//        settingsButton.addListener(new ClickListener() {
//            @Override
//            public void clicked(InputEvent event, float x, float y) {
//                // Handle Settings button click
//                //game.setScreen(new SettingsScreen(game));
//                dispose();
//            }
//        });
//
//        exitButton.addListener(new ClickListener() {
//            @Override
//            public void clicked(InputEvent event, float x, float y) {
//                Gdx.app.exit(); // Exit the application
//            }
//        });
        stage.addActor(back_button);
        addHoverEffect1(back_button,100, 210);
        Gdx.input.setInputProcessor(stage);
    }
    // Method to add hover effect to ImageButton
    private void addHoverEffect1(final ImageButton button, final float originalWidth, final float originalHeight) {
        button.addListener(new InputListener() {

            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                hoverSound.play();
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

    // Method to add hover effect
    private void addHoverEffect(final Image image, final float originalWidth, final float originalHeight) {
        image.addListener(new InputListener() {

            public boolean mouseMoved(InputEvent event, float x, float y) {
                return super.mouseMoved(event, x, y);
            }


            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                hoverSound.play();
                // Enlarge the image on hover
                image.setSize(originalWidth + 20, originalHeight + 20);  // Increase size
                image.setPosition(image.getX() - 10, image.getY() - 10); // Adjust position to center
            }


            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                // Revert to original size
                image.setSize(originalWidth, originalHeight);  // Reset size
                image.setPosition(image.getX() + 10, image.getY() + 10); // Reset position
            }
        });
    }



    @Override
    public void show() { }
    @Override
    public void render(float delta) {
        // Clear the screen with a background color
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
        blur.dispose();
        back.dispose();
        clickSound.dispose();
        hoverSound.dispose();
    }
}
