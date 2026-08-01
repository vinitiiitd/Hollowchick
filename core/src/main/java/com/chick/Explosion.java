package com.chick;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class Explosion {
    private Animation<TextureRegion> explosionAnimation; // Animation for the explosion
    private float elapsedTime; // Timer to track animation progress
    private float x, y; // Position of the explosion
    private boolean isFinished; // Flag to check if the animation is complete
    private Array<Texture> loadedTextures; // Keep track of loaded textures for cleanup

    public Explosion(float x, float y) {
        this.x = x;
        this.y = y;
        this.elapsedTime = 0f;
        this.isFinished = false;
        this.loadedTextures = new Array<>();

        // Load frames for the explosion animation
        Array<TextureRegion> frames = new Array<>();
        for (int i = 1; i <= 58; i++) { // Assuming the explosion has 58 frames
            String framePath = "explosion/frame_" + i + ".png";
            Texture frameTexture = new Texture(Gdx.files.internal(framePath));
            frames.add(new TextureRegion(frameTexture));
            loadedTextures.add(frameTexture); // Track loaded textures for cleanup
        }

        // Create an animation (0.05 seconds per frame, play once)
        explosionAnimation = new Animation<>(0.05f, frames, Animation.PlayMode.NORMAL);
    }

    public void update(float delta) {
        // Update the elapsed time
        elapsedTime += delta;

        // Check if the animation has finished
        if (explosionAnimation.isAnimationFinished(elapsedTime)) {
            isFinished = true;
        }
    }

    public void render(SpriteBatch batch) {
        if (!isFinished) {
            // Get the current frame of the animation
            TextureRegion currentFrame = explosionAnimation.getKeyFrame(elapsedTime, false);

            // Draw the explosion at the specified position
            batch.draw(currentFrame, x, y);
        }
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void dispose() {
        // Dispose of all loaded textures
        for (Texture texture : loadedTextures) {
            texture.dispose();
        }
    }
}
