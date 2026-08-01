package com.chick;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import com.badlogic.gdx.utils.viewport.Viewport;

public class CustomTower implements Screen {
    private Stage stage;
    private chick game;
    private Viewport viewport;

    // Textures
    private Texture next, back, t1, t2, t3, t4, t5, preview, done, customTower, display,buck;

    // Images
    private Image customTower_i, t1_i, t2_i, t3_i, t4_i, t5_i, display_i;

    // Track current image
    private Image currentImage;

    public CustomTower(chick game) {
        this.game = game;
        viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        stage = new Stage(viewport);

        // Load textures
        customTower = new Texture(Gdx.files.internal("game_ass/main.png"));
        next = new Texture(Gdx.files.internal("game_ass/next.png"));
        back = new Texture(Gdx.files.internal("game_ass/back.png"));
        t1 = new Texture(Gdx.files.internal("game_ass/t1.png"));
        t2 = new Texture(Gdx.files.internal("game_ass/T2.png"));
        t3 = new Texture(Gdx.files.internal("game_ass/T3.png"));
        t4 = new Texture(Gdx.files.internal("game_ass/T4.png"));
        t5 = new Texture(Gdx.files.internal("game_ass/T5.png"));
        preview = new Texture(Gdx.files.internal("game_ass/preview.png"));
        done = new Texture(Gdx.files.internal("game_ass/done.png"));
        display = new Texture(Gdx.files.internal("game_ass/display.png"));
        buck = new Texture(Gdx.files.internal("game_ass/buck.png"));

        // Button creation
        ImageButton next_Button = new ImageButton(new TextureRegionDrawable(next));
        ImageButton buck_Button = new ImageButton(new TextureRegionDrawable(buck));
        ImageButton back_Button = new ImageButton(new TextureRegionDrawable(back));
        ImageButton preview_Button = new ImageButton(new TextureRegionDrawable(preview));
        ImageButton done_Button = new ImageButton(new TextureRegionDrawable(done));

        // Set positions and sizes of buttons
        next_Button.setPosition(490, 200);
        next_Button.setSize(70, 35);

        back_Button.setPosition(80, 200);
        back_Button.setSize(70, 35);

        buck_Button.setPosition(30, 330);
        buck_Button.setSize(100, 210);


        done_Button.setPosition(195, 20);
        done_Button.setSize(250, 150);



        // Background image
        customTower_i = new Image(customTower);
        customTower_i.setSize(stage.getWidth(), stage.getHeight());
        customTower_i.setPosition(0, 0);
        stage.addActor(customTower_i);

        // Add towers and display
        t1_i = new Image(t1);
        t1_i.setPosition(220, 160);
        t1_i.setSize(200, 150);


        t2_i = new Image(t2);
        t2_i.setPosition(265, 150);
        t2_i.setSize(110, 220);


        t3_i = new Image(t3);
        t3_i.setPosition(265, 150);
        t3_i.setSize(110, 220);


        t4_i = new Image(t4);
        t4_i.setPosition(265, 150);
        t4_i.setSize(110, 230);


        t5_i = new Image(t5);
        t5_i.setPosition(200, 130);
        t5_i.setSize(250, 250);


        display_i = new Image(display);
        display_i.setPosition(85, 45);
        display_i.setSize(470, 360);


        //working button
        buck_Button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(check.getcheck()){
                    game.getclickSound().play();
                }
                game.setScreen(new Custom(game));
            }
        });



        // Button click listeners to switch images
        next_Button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // Replace t1 with t2 when 'next' button is clicked
                if(check.getcheck()){
                    game.getclickSound().play();
                }

                if (currentImage == t1_i) {
                    currentImage.remove(); // Remove the current image
                    currentImage = t2_i; // Switch to t2
                    stage.addActor(currentImage); // Add t2 to stage
                }
                else if (currentImage == t2_i) {
                    currentImage.remove(); // Remove the current image
                    currentImage = t3_i;
                    stage.addActor(currentImage);
                }
                else if (currentImage == t3_i) {
                    currentImage.remove(); // Remove the current image
                    currentImage = t4_i;
                    stage.addActor(currentImage);
                }
                else if (currentImage == t4_i) {
                    currentImage.remove(); // Remove the current image
                    currentImage = t1_i;
                    stage.addActor(currentImage);
                }

            }
        });

        back_Button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(check.getcheck()){
                    game.getclickSound().play();
                }
                if (currentImage == t2_i) {
                    currentImage.remove();
                    currentImage = t1_i;
                    stage.addActor(currentImage);
                }

                else if (currentImage == t3_i) {
                    currentImage.remove();
                    currentImage = t2_i;
                    stage.addActor(currentImage);
                }

                else if (currentImage == t4_i) {
                    currentImage.remove();
                    currentImage = t3_i;
                    stage.addActor(currentImage);
                }
                else if (currentImage == t1_i) {
                    currentImage.remove();
                    currentImage = t4_i;
                    stage.addActor(currentImage);
                }
            }
        });

        done_Button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (check.getcheck()) {
                    game.getclickSound().play();
                }
                if (currentImage == t1_i) {
                    game.setSelectedTower("game_ass/t1.png");
                } else if (currentImage == t2_i) {
                    game.setSelectedTower("game_ass/T2.png");
                } else if (currentImage == t3_i) {
                    game.setSelectedTower("game_ass/T3.png");
                } else if (currentImage == t4_i) {
                    game.setSelectedTower("game_ass/T4.png");
                }
                game.setScreen(new GlobalGameplay(game));
            }
        });




        // Add buttons to the stage

        stage.addActor(customTower_i);
        stage.addActor(display_i);
        stage.addActor(buck_Button);

        stage.addActor(done_Button);
        stage.addActor(next_Button);
        stage.addActor(back_Button);
        // Add initial image to stage (start with t1)
        currentImage = t1_i;
        stage.addActor(currentImage);



    }







    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        stage.dispose();
        customTower.dispose();
        next.dispose();
        back.dispose();
        t1.dispose();
        t2.dispose();
        t3.dispose();
        t4.dispose();
        t5.dispose();
        preview.dispose();
        done.dispose();
        display.dispose();
    }
}
