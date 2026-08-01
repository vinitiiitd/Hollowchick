package com.chick;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.files.FileHandle;
import java.util.Random;


public class Loading implements Screen {
    //private int a = countFilesInFolder("loading_m");;
    private static final float LOADING_TIME = 5.8f; // Duration for loading screen
    private float elapsedTime = 0f;

    private SpriteBatch batch;
    private Animation<TextureRegion> loadingAnimation;
    private chick game;
    private Screen nextScreen; // The next screen to show
    private Array<Texture> loadedTextures; // Keep track of loaded textures for disposal
    private static final int VIDEO_COUNT = 5; // Total number of available videos

    public Loading(chick game, Screen nextScreen) {
        this.game = game;
        this.nextScreen = nextScreen; // Store the next screen
        batch = new SpriteBatch();
        loadedTextures = new Array<>(); // Array to keep track of loaded textures

        // Randomly choose one of the 5 videos
        int chosenVideo = getRandomIntInRange(1, VIDEO_COUNT);

        // Load the frames for the chosen video (assuming each video has 8 frames)
        Array<TextureRegion> frames = new Array<>();
        for (int i = 1; i <= 58; i++) { // Assuming 8 frames for each video
            String framePath = "loading_m/logo_" + chosenVideo + "/ " + i + ".png";
            Texture frameTexture = new Texture(Gdx.files.internal(framePath));
            frames.add(new TextureRegion(frameTexture));
            loadedTextures.add(frameTexture); // Track loaded textures for disposal
        }


        // Create an animation with the frames (0.1 second per frame, looping)
        loadingAnimation = new Animation<>(0.1f, frames, Animation.PlayMode.NORMAL);
    }


    // Utility function to generate a random integer in a given range
    private int getRandomIntInRange(int min, int max) {
        if (min > max) throw new IllegalArgumentException("min cannot be greater than max");
        return (int) (Math.random() * (max - min + 1)) + min;
    }

    @Override
    public void show() { }

    @Override
    public void render(float delta) {
        elapsedTime += delta;

        // Clear the screen
        Gdx.gl.glClearColor(1, 1, 1, 1); // White background
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Draw the loading animation
        batch.begin();
        TextureRegion currentFrame = loadingAnimation.getKeyFrame(elapsedTime, true);
        batch.draw(currentFrame, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()); // Full screen loading animation
        batch.end();

        // After the loading time, switch to the next screen
        if (elapsedTime >= LOADING_TIME) {
            game.setScreen(nextScreen);
        }
    }

    @Override
    public void resize(int width, int height) { }

    @Override
    public void pause() { }

    @Override
    public void resume() { }

    @Override
    public void hide() { }

    @Override
    public void dispose() {
        batch.dispose();
        // Dispose of all loaded textures
        for (Texture texture : loadedTextures) {
            texture.dispose();
        }
    }
}
