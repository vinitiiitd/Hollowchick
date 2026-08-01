package com.chick;

import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;



public class tick {

    private Texture tick;
    private float x, y, width, height;
    private Image tick_i;

    public tick(String imagePath, float x, float y, float width, float height) {
        this.tick = new Texture(Gdx.files.internal(imagePath));
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        // Load textures for buttons
        tick = new Texture(Gdx.files.internal("tick.png"));

    }

    public void render(SpriteBatch batch) {
        batch.draw(tick, x, y, width, height);
    }

    public void renderImage(Stage stage, chick game) {
        tick_i = new Image(tick);
        tick_i.setSize(width, height);
        tick_i.setPosition(x, y);
        stage.addActor(tick_i);
    }
    public void hide(){
        tick_i.setVisible(false);
    }
    public void see(){
        tick_i.setVisible(true);
    }

    public void dispose() {
        tick.dispose();

    }
}
