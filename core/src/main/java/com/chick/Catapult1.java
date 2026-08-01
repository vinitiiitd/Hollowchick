// Catapult.java
package com.chick;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Catapult1 {
    private Texture baseTexture;
    private Texture armTexture;
    private Texture projectileTexture;
    private Vector2 position;
    private Vector2 projectilePosition;
    private boolean isDragging;

    public Catapult1(String basePath, String armPath, String projectilePath, Vector2 position) {
        this.baseTexture = new Texture(basePath);
        this.armTexture = new Texture(armPath);
        this.projectileTexture = new Texture(projectilePath);
        this.position = position;
        this.projectilePosition = new Vector2(position.x + 20, position.y + 50);  // Adjust as needed
        this.isDragging = false;
    }

    public void render(SpriteBatch batch) {
        // Render the catapult base
        batch.draw(baseTexture, position.x, position.y);

        // Render the arm
        batch.draw(armTexture, position.x + 15, position.y + 10);

        // Render the projectile
        batch.draw(projectileTexture, projectilePosition.x, projectilePosition.y);

        // Render the rubber band (for visual pull-back effect)
        if (isDragging) {
            // You could draw a line or texture to simulate a rubber band here
            // Use LibGDX's ShapeRenderer if you want a line effect
        }
    }

    public void update(Vector2 touchPosition) {
        if (isDragging) {
            projectilePosition.set(touchPosition);
        }
    }

    public void release() {
        isDragging = false;
    }

    public void dispose() {
        baseTexture.dispose();
        armTexture.dispose();
        projectileTexture.dispose();
    }

    public void startDragging() {
        isDragging = true;
    }
}
