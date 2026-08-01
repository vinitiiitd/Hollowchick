package com.chick;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
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
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.utils.viewport.Viewport;


public class Settings2 implements Screen {
    private Stage stage;
    private chick game;
    private Viewport viewport;

    // Textures for buttons and images
    private Texture blur;
    private Texture scr;
    private Texture cr; //
    private Texture crd;
    private Texture on; //
    private Texture on1; //
    private Texture off;
    private Texture off1;

    private Image blur_i;
    private Image scr_i;
    private Image crd_i;

    private ImageButton on_Button;
    private ImageButton off_Button;
    private ImageButton on1_Button;
    private ImageButton off1_Button;


    public Settings2(chick game) {
        this.game = game;
        viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        stage = new Stage(viewport);

        game.getBackgroundMusic().setVolume(0.4f);


        // Load textures
        scr = new Texture(Gdx.files.internal("Settings/1.png"));
        cr = new Texture(Gdx.files.internal("Settings/2.png"));
        blur = new Texture(Gdx.files.internal("game/1.png"));
        crd = new Texture(Gdx.files.internal("Settings/3.png"));
        on = new Texture(Gdx.files.internal("Settings/4.png"));
        on1 = new Texture(Gdx.files.internal("Settings/4.png"));
        off = new Texture(Gdx.files.internal("Settings/6.png"));
        off1 = new Texture(Gdx.files.internal("Settings/6.png"));

        // Create buttons for on and off states
        on_Button = new ImageButton(new TextureRegionDrawable(on));
        on1_Button = new ImageButton(new TextureRegionDrawable(on1));
        off_Button = new ImageButton(new TextureRegionDrawable(off));
        off1_Button = new ImageButton(new TextureRegionDrawable(off1));
        ImageButton back_button = new ImageButton(new TextureRegionDrawable(cr));

        back_button.setPosition(510, 275);
        back_button.setSize(40, 120);

        // Set positions and sizes for buttons
        on_Button.setPosition(333, 50);
        on_Button.setSize(200, 350);
        on1_Button.setPosition(108, 50);
        on1_Button.setSize(200, 350);

        off_Button.setPosition(333, 50);
        off_Button.setSize(200, 350);
        //off_Button.setVisible(false);

        off1_Button.setPosition(108, 50);
        off1_Button.setSize(200, 350);
        //off1_Button.setVisible(false);// Hide off button initially

        if(check.getcheck()){
            off_Button.setVisible(false);

        }
        else{
            on_Button.setVisible(false);

        }
        if(check2.getcheck()){
            off1_Button.setVisible(false);
        }
        else{
            on1_Button.setVisible(false);
        }

        // Add blur background image
        blur_i = new Image(blur);
        blur_i.setSize(stage.getWidth(), stage.getHeight());
        blur_i.setPosition(0, 0);
        stage.addActor(blur_i);



        scr_i = new Image(scr);
        scr_i.setSize(520, 260);
        scr_i.setPosition(65, 120);
        stage.addActor(scr_i);


        crd_i = new Image(crd);
        crd_i.setSize(200, 50);
        crd_i.setPosition(225, 150);
       // stage.addActor(crd_i);


        // Add ImageButtons
        stage.addActor(off_Button); // Add the off button
        stage.addActor(on_Button);
        stage.addActor(on1_Button);
        stage.addActor(off1_Button);// Add the on button

        // Add listeners to buttons
        on_Button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
//                if (game.isClickEnabled()) {
//                    game.getclickSound().play();
//                }
                if(check.getcheck()){
                    game.getclickSound().play();
                }
                check.setcheck(false);
                on_Button.setVisible(false);
                off_Button.setVisible(true);
                game.setback(false); // Disable click sound
            }
        });

        off_Button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(check.getcheck()){
                    game.getclickSound().play();
                }
                check.setcheck(true);
                off_Button.setVisible(false);
                on_Button.setVisible(true);
                game.setback(true);// Enable click sound
            }
        });

        // Hover sound on/off buttons
        on1_Button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(check.getcheck()){
                    game.getclickSound().play();
                }
                //check.setcheck(false);

                game.getBackgroundMusic().stop();
                check2.setcheck(false);
                on1_Button.setVisible(false);
                off1_Button.setVisible(true);
                game.setClickEnabled(true);
                game.setHoverEnabled(true);
                // Disable hover sound
            }
        });

        off1_Button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(check.getcheck()){
                    game.getclickSound().play();
                }
                //check.setcheck(true);
                game.getBackgroundMusic().play();
                check2.setcheck(true);
                on1_Button.setVisible(true);
                off1_Button.setVisible(false);
                game.setClickEnabled(false);
                game.setHoverEnabled(false);// Enable hover sound
            }
        });
        back_button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

                if(check.getcheck()){
                    game.getclickSound().play();
                }

                game.setScreen( new MainScreen(game));
                game.getBackgroundMusic().setVolume(1f);// Pass the next screen
            }
        });
        back_button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(check.getcheck()){
                    game.getclickSound().play();
                }
                game.setScreen( new Gameplay(game));
                game.getBackgroundMusic().setVolume(1f);// Pass the next screen
            }
        });

        stage.addActor(back_button);

        // Set the stage as the input processor
        Gdx.input.setInputProcessor(stage);
        addHoverEffect1(off_Button, 200, 350);
        addHoverEffect1(off1_Button, 200, 350);
        addHoverEffect1(on_Button, 200, 350);
        addHoverEffect1(on1_Button, 200, 350);
        addHoverEffect1(back_button, 40, 120);

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
    public void show() { }

    @Override
    public void render(float delta) {
        // Clear the screen with a white background
        Gdx.gl.glClearColor(0, 0, 0, 0);
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
        scr.dispose();
        cr.dispose();
        //crd.dispose();
        on.dispose();
        on1.dispose();
        off.dispose();
        off1.dispose();
        game.getBackgroundMusic().setVolume(0.5f);
    }
}
