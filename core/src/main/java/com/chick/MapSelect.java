package com.chick;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class MapSelect implements Screen {
    private Stage stage;
    private chick game;
    private Viewport viewport;

    private ScrollPane scrollPane;
    private Table mapTable;

    private Texture d_map, f_map, s_map,f1_map,d1_map,s1_map, blur, Select_map, back;
    private Image Select_map_i,f1_map_i,d1_map_i,s1_map_i;
    private ImageButton f_Button,ice_Button,s_Button, back_button;
    private Image blur_i;

    // Original dimensions
    private final float fButtonOriginalWidth = 300, fButtonOriginalHeight = 480;
    private final float backButtonOriginalWidth = 100, backButtonOriginalHeight = 210;
    private final float sMapOriginalWidth = 255, sMapOriginalHeight = 335;
    private final float dMapOriginalWidth = 255, dMapOriginalHeight = 340;
    private final float selectMapOriginalWidth = 240, selectMapOriginalHeight = 70;

    public MapSelect(chick game) {
        this.game = game;
        viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        stage = new Stage(viewport);

        // Load textures
        d_map = new Texture(Gdx.files.internal("ice.png"));//
        f_map = new Texture(Gdx.files.internal("Component 1.png"));
        s_map = new Texture(Gdx.files.internal("scr.png"));
        f1_map = new Texture(Gdx.files.internal("map1.png"));
        d1_map = new Texture(Gdx.files.internal("map2.png"));
        s1_map = new Texture(Gdx.files.internal("map3.png"));
        blur = new Texture(Gdx.files.internal("blur.png"));
        Select_map = new Texture(Gdx.files.internal("sm.png"));
        back = new Texture(Gdx.files.internal("c6.png"));

        // Initialize buttons
        f_Button = new ImageButton(new TextureRegionDrawable(f_map));
        ice_Button = new ImageButton(new TextureRegionDrawable(d_map));
        s_Button = new ImageButton(new TextureRegionDrawable(s_map));
        back_button = new ImageButton(new TextureRegionDrawable(back));

        // Set positions and sizes for buttons
        f_Button.setPosition(23, 0);
        f_Button.setSize(fButtonOriginalWidth, fButtonOriginalHeight);
        ice_Button.setPosition(73, 0);
        ice_Button.setSize(fButtonOriginalWidth, fButtonOriginalHeight);
        s_Button.setPosition(143, 0);
        s_Button.setSize(fButtonOriginalWidth, fButtonOriginalHeight);
        back_button.setPosition(30, 330);
        back_button.setSize(backButtonOriginalWidth, backButtonOriginalHeight);

        // Initialize other images
//        d_map_i = new Image(d_map);
//        d_map_i.setPosition(199, 72);
//        d_map_i.setSize(dMapOriginalWidth, dMapOriginalHeight);
//
//        s_map_i = new Image(s_map);
//        s_map_i.setPosition(405, 78);
//        s_map_i.setSize(sMapOriginalWidth, sMapOriginalHeight);

        Select_map_i = new Image(Select_map);
        Select_map_i.setPosition(204, 390);
        Select_map_i.setSize(selectMapOriginalWidth, selectMapOriginalHeight);
        blur_i = new Image(blur);
        blur_i.setSize(stage.getWidth() + 200, stage.getHeight());
        blur_i.setPosition(0, 0);
        stage.addActor(blur_i);

        s1_map_i = new Image(s1_map);
        s1_map_i.setPosition(405, 78);
        s1_map_i.setSize(sMapOriginalWidth, sMapOriginalHeight);

        f1_map_i = new Image(f1_map);
        f1_map_i.setPosition(405, 78);
        f1_map_i.setSize(sMapOriginalWidth, sMapOriginalHeight);
        d1_map_i = new Image(d1_map);
        d1_map_i.setPosition(405, 78);
        d1_map_i.setSize(sMapOriginalWidth, sMapOriginalHeight);
        // Add hover effects
        addHoverEffect1(f_Button, fButtonOriginalWidth, fButtonOriginalHeight);
        addHoverEffect1(ice_Button,240, 440);
        addHoverEffect1(s_Button,fButtonOriginalWidth, fButtonOriginalHeight);
        addHoverEffect(s1_map_i, sMapOriginalWidth, sMapOriginalHeight);
        addHoverEffect(d1_map_i, sMapOriginalWidth, sMapOriginalHeight);
        addHoverEffect(f1_map_i, sMapOriginalWidth, sMapOriginalHeight);
        addHoverEffect1(back_button, backButtonOriginalWidth, backButtonOriginalHeight);

        // Add click listeners
        f_Button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (check.getcheck()) game.getclickSound().play();
                game.setScreen(new Loading(game, new LevelSelect(game)));
            }
        });
        ice_Button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (check.getcheck()) game.getclickSound().play();
                game.setScreen(new Loading(game, new LevelSelect2(game)));
            }
        });
        s_Button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (check.getcheck()) game.getclickSound().play();
                game.setScreen(new Loading(game, new LevelSelect3(game)));
            }
        });

        back_button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (check.getcheck()) game.getclickSound().play();
                game.setScreen(new Loading(game, new MainScreen(game)));
            }
        });

        // Set up the map table and scroll pane
        mapTable = new Table();
        mapTable.defaults().width(255).height(340);
        mapTable.add(f_Button).width(300).height(480).padLeft(20);
        mapTable.add(ice_Button).width(240).height(460).padLeft(-120).padTop(0);
        mapTable.add(s_Button).width(300).height(480).padLeft(-30);
        mapTable.add(s1_map_i).pad(-155);
        mapTable.add(f1_map_i).pad(70);
        mapTable.add(d1_map_i).pad(-115);

        scrollPane = new ScrollPane(mapTable);
        scrollPane.setScrollingDisabled(false, true);
        scrollPane.setFillParent(true);

        // Add actors to the stage
        stage.addActor(scrollPane);
        stage.addActor(back_button);
        stage.addActor(Select_map_i);

        Gdx.input.setInputProcessor(stage);
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

    private void addHoverEffect(final Image image, final float originalWidth, final float originalHeight) {
        image.addListener(new InputListener() {
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                if (check.getcheck()) game.gethover().play();
                image.setSize(originalWidth + 20, originalHeight + 20);
                image.setPosition(image.getX() - 10, image.getY() - 10);
            }

            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                image.setSize(originalWidth, originalHeight);
                image.setPosition(image.getX() + 10, image.getY() + 10);
            }
        });
    }

    @Override
    public void show() { }

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
    public void pause() { }

    @Override
    public void resume() { }

    @Override
    public void hide() { }

    @Override
    public void dispose() {
        stage.dispose();
        d_map.dispose();
        f_map.dispose();
        s_map.dispose();
        blur.dispose();
        Select_map.dispose();
        back.dispose();
        s1_map.dispose();
        d1_map.dispose();
        f1_map.dispose();
    }
}