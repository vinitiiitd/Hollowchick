package com.chick;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class VideoScreen implements Screen {
    private chick game;
    SpriteBatch batch;
    Animation<TextureRegion> videoAnimation;
    private float elapsedTime = 0f;
    float frameDuration = 0.1f;

    // Duration per frame of the video
    private final float FRAME_DURATION = 0.05f;

    public VideoScreen(chick game) {
        this.game = game;
        batch = new SpriteBatch();

        // Load video frames
        Array<TextureRegion> frames = new Array<>();
        for (int i = 1; i <= 61; i++) {
            Texture frame = new Texture(Gdx.files.internal("logo/" + i + ".jpg"));
            frames.add(new TextureRegion(frame));
        }

        // Create the animation
        videoAnimation = new Animation<>(FRAME_DURATION, frames, Animation.PlayMode.NORMAL);


    }

    @Override
    public void show() {
        // Called when this screen becomes active
    }
    public void setVideoSpeed(float speedMultiplier) {
        frameDuration = 0.1f / speedMultiplier;  // Adjusting the speed
        videoAnimation.setFrameDuration(frameDuration);  // Apply the new frame duration
    }

    @Override
    public void render(float delta) {
        // Update the elapsed time
        elapsedTime += delta;

        // Clear the screen
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Get the current frame of the video animation
        TextureRegion currentFrame = videoAnimation.getKeyFrame(elapsedTime, false);

        // Draw the current frame
        batch.begin();
        batch.draw(currentFrame, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();

        // If the video is finished, switch to MainScreen
        if (videoAnimation.isAnimationFinished(elapsedTime)) {
            game.setScreen(new MainScreen(game));
        }
    }

    @Override
    public void resize(int width, int height) {
        // Handle screen resize if needed
    }

    @Override
    public void pause() { }

    @Override
    public void resume() { }

    @Override
    public void hide() {
        // Dispose the screen when it's hidden
    }
    @Override
    public void dispose() {
        batch.dispose();
    }
}
