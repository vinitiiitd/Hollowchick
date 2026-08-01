package com.chick;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class catapult {
    private Texture texture;
    private Vector2 position;
    private float width, height;
    private Image blur_i;

    // For dragging and launching
    private Vector2 dragStart;
    private Vector2 dragEnd;
    private boolean isDragging;
    private Bird bird; // Projectile object

    public catapult(String imagePath, float x, float y, float width, float height, Bird bird) {
        this.texture = new Texture(Gdx.files.internal(imagePath));
        this.position = new Vector2(x, y);
        this.width = width;
        this.height = height;
        this.bird = bird; // Assign bird as the projectile
        this.dragStart = new Vector2();
        this.dragEnd = new Vector2();
        this.isDragging = false;
    }

    public void startDragging(float x, float y) {
        isDragging = true;
        dragStart.set(x, y);
        dragEnd.set(x, y);
    }

    public void updateDrag(float x, float y) {
        if (isDragging) {
            dragEnd.set(x, y); // Update drag endpoint as you move
        }
    }

    public void release() {
        if (isDragging) {
            isDragging = false;
            Vector2 launchVelocity = new Vector2(dragStart).sub(dragEnd).scl(1.5f); // Adjust multiplier as needed
            //bird.launch(launchVelocity.x, launchVelocity.y); // Launch the bird with calculated velocity
        }
    }

    public void render(SpriteBatch batch) {
        // Render catapult
        batch.draw(texture, position.x, position.y, width, height);

        // Render bird if it's currently being dragged or launched

        //bird.render(batch);

    }

    public void render_image(Stage stage) {
        blur_i = new Image(texture);
        blur_i.setSize(width, height);
        blur_i.setPosition(position.x, position.y);
        stage.addActor(blur_i);
    }

    public void dispose() {
        texture.dispose();
        //bird.dispose();
    }
}
