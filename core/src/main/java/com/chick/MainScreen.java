package com.chick;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class MainScreen implements Screen {
    private Stage stage;
    private chick game;
    private SpriteBatch batch;
    private Viewport viewport;

    // Animation-related fields
    private Animation<TextureRegion> backgroundAnimation;
    private float elapsedTime;

    // Textures for buttons and other assets
    Texture startTexture;
    Texture customTexture;
    Texture settingsTexture;
    Texture exitTexture;
    private Texture img;
    private Texture chick_1;
    private Texture Check_2;
    private Texture ver;
    private Texture bird;
    private Texture TM;
    private Texture pig;
    private Image pig_i, Img, Img_v, bird_i;

    ImageButton startButton;
    ImageButton customButton;
    ImageButton settingsButton;
    ImageButton exitButton;

    // Button sizes
    private final float BUTTON_WIDTH = 170, BUTTON_HEIGHT = 50;
    private final float CUSTOM_BUTTON_WIDTH = 140, CUSTOM_BUTTON_HEIGHT = 50;
    private final float SETTINGS_BUTTON_WIDTH = 150, SETTINGS_BUTTON_HEIGHT = 50;
    private final float EXIT_BUTTON_WIDTH = 70, EXIT_BUTTON_HEIGHT = 50;
    private final float CHICK_IMAGE_WIDTH = 180, CHICK_IMAGE_HEIGHT = 50;

    // Bird physics
    float birdY;
    float birdVelocity;
    private final float GRAVITY = -0.5f;
    private final float JUMP_VELOCITY = 10f;
    private final float GROUND_Y = 20;

    // Sounds



    public MainScreen(chick game) {
        this.game = game;
        viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        stage = new Stage(viewport);
        batch = new SpriteBatch();



        // Load textures for buttons and other assets
        startTexture = new Texture(Gdx.files.internal("Start Game.png"));
        customTexture = new Texture(Gdx.files.internal("Custom.png"));
        settingsTexture = new Texture(Gdx.files.internal("Settings.png"));
        exitTexture = new Texture(Gdx.files.internal("Exit.png"));
        chick_1 = new Texture(Gdx.files.internal("hollow.png"));
        ver = new Texture(Gdx.files.internal("version 69.13.png"));
        bird = new Texture(Gdx.files.internal("1.png"));
        pig = new Texture(Gdx.files.internal("pig.png"));

        // Initialize buttons and their positions
        startButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(startTexture)));
        customButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(customTexture)));
        settingsButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(settingsTexture)));
        exitButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(exitTexture)));

        startButton.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        customButton.setSize(CUSTOM_BUTTON_WIDTH, CUSTOM_BUTTON_HEIGHT);
        settingsButton.setSize(SETTINGS_BUTTON_WIDTH, SETTINGS_BUTTON_HEIGHT);
        exitButton.setSize(EXIT_BUTTON_WIDTH, EXIT_BUTTON_HEIGHT);

        startButton.setPosition(8, 255);
        customButton.setPosition(6, 210);
        settingsButton.setPosition(1, 165);
        exitButton.setPosition(9, 125);

        // Add images and set their positions and sizes
        pig_i = new Image(pig);
        pig_i.setPosition(476, 23);
        pig_i.setSize(120, 60);
        stage.addActor(pig_i);

        Img = new Image(chick_1);
        Img.setPosition(35, 306);
        Img.setSize(CHICK_IMAGE_WIDTH + 120, CHICK_IMAGE_HEIGHT + 75);
        stage.addActor(Img);

        Img_v = new Image(ver);
        Img_v.setPosition(-1, -10);
        Img_v.setSize(90, 30);
        stage.addActor(Img_v);

        bird_i = new Image(bird);
        bird_i.setPosition(156, 20);
        bird_i.setSize(160, 80);
        stage.addActor(bird_i);

        // Add button listeners
        startButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(check.getcheck()){
                    game.getclickSound().play();
                }
                game.setScreen(new Loading(game, new MapSelect(game)));  // Pass the next screen
            }
        });

        customButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(check.getcheck()){
                    game.getclickSound().play();
                }
                game.getBackgroundMusic().pause();
                game.setScreen(new Loading(game, new Custom(game)));  // Pass the next screen
                game.getLOU2().play();
            }
        });

        settingsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(check.getcheck()){
                    game.getclickSound().play();
                }
                game.getBackgroundMusic().pause();
                game.setScreen(new Settings(game));
                game.getLOU().play();
            }
        });

        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(check.getcheck()){
                    game.getclickSound().play();
                }
                Gdx.app.exit();
            }
        });

        stage.addActor(startButton);
        stage.addActor(customButton);
        stage.addActor(settingsButton);
        stage.addActor(exitButton);

        Gdx.input.setInputProcessor(stage);

        birdY = 20;
        birdVelocity = 0;

        // Add hover effects
        addHoverEffect1(startButton, BUTTON_WIDTH, BUTTON_HEIGHT);
        addHoverEffect1(customButton, CUSTOM_BUTTON_WIDTH, CUSTOM_BUTTON_HEIGHT);
        addHoverEffect1(settingsButton, SETTINGS_BUTTON_WIDTH, SETTINGS_BUTTON_HEIGHT);
        addHoverEffect1(exitButton, EXIT_BUTTON_WIDTH, EXIT_BUTTON_HEIGHT);

        // Load the animation frames for the background
        Array<TextureRegion> frames = new Array<>();
        for (int i = 1; i <= 16; i++) {
            Texture frameTexture = new Texture(Gdx.files.internal("1/" + i + ".png"));
            frames.add(new TextureRegion(frameTexture));
        }
        backgroundAnimation = new Animation<>(0.1f, frames, Animation.PlayMode.LOOP);
    }

    private void addHoverEffect1(final ImageButton button, final float originalWidth, final float originalHeight) {
        button.addListener(new InputListener() {
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                if(check.getcheck()) {
                    game.gethover().play();
                }
                button.setSize(originalWidth + 20, originalHeight + 20);
                button.setPosition(button.getX() - 10, button.getY() - 10);
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                button.setSize(originalWidth, originalHeight);
                button.setPosition(button.getX() + 10, button.getY() + 10);
            }
        });
    }

    @Override
    public void render(float delta) {
        elapsedTime += delta;

        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Get the current frame for the background animation
        TextureRegion currentFrame = backgroundAnimation.getKeyFrame(elapsedTime, true);

        // Draw the current frame as a full-screen background
        batch.begin();
        batch.draw(currentFrame, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();



        // Update bird physics
        birdVelocity += GRAVITY;
        birdY += birdVelocity;
        if (birdY <= GROUND_Y) {
            birdY = GROUND_Y;
            birdVelocity = JUMP_VELOCITY;
        }

        bird_i.setPosition(bird_i.getX(), birdY);

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void show() {
        game.backgroundMusic.setLooping(true);
        game.backgroundMusic.setVolume(0.8f);
        if(check2.getcheck()){
            game.backgroundMusic.play();
        }
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
        batch.dispose();
        startTexture.dispose();
        customTexture.dispose();
        settingsTexture.dispose();
        exitTexture.dispose();
        chick_1.dispose();
        ver.dispose();
        bird.dispose();
        pig.dispose();
        if (game.backgroundMusic != null) {
            game.backgroundMusic.dispose();
        }
    }
}
