package com.chick;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import java.util.ArrayList;
import java.util.List;

public class GlobalGameplay implements Screen {
    private SpriteBatch batch;
    private Bird bird;
    private Bird b2;
    private Bird b3;
    //private World world;
    private World world;
    private List<Block> blocks;
    private Texture pause;
    private ShapeRenderer shapeRenderer;
    private OrthographicCamera camera;
    private Box2DDebugRenderer debugRenderer;
    private Stage stage;
    public static final float PPM = 100; // Pixels Per Meter
    private Body groundBody;
    private Texture c;

    private Texture background; // Holds the texture for the selected map
    private Texture tower;
    private Block block1,block2,block3,block4,block5,block6,block7,block8,block9,block10,block11,block12,block13,block14,block15;

    private Vector2 launchImpulse;
    private boolean isLaunched;
    private ImageButton f_Button;
    private chick game;
    private int a=3;// Reference to your main game class\

    private Bird sb;

    private pig pig1;
    private List<pig> pigs = new ArrayList<>();
    private List<pig> pigsToDestroy = new ArrayList<>();
    private List<Explosion> explosions = new ArrayList<>();
    private String[] explosionFrames = {"ex/1.png", "ex/2.png", "ex/3.png","ex/4.png","ex/5.png"}; // Explosion frames


    private int currentBirdIndex = 0;
    private List<Bird> birds;

    private int l = 4;
    private boolean p1=true;

    public GlobalGameplay(chick game) {
        this.game = game; // Initialize with the game instance
    }

    @Override
    public void show() {
        world = new World(new Vector2(0, -2.5f), true);
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        isLaunched = false;

        // Initialize Box2D world
//        world = new World(new Vector2(0, -9.8f), true);
        c = new Texture(Gdx.files.internal("c.png"));
        pause = new Texture(Gdx.files.internal("pause2.png"));

        f_Button = new ImageButton(new TextureRegionDrawable(pause));
        f_Button.setPosition(5, 435);
        f_Button.setSize(60, 40);

        // Camera setup
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth() / PPM, Gdx.graphics.getHeight() / PPM);

        // Debug renderer
        debugRenderer = new Box2DDebugRenderer();

        // Initialize stage for non-physics elements
        stage = new Stage(new StretchViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()), batch);
        Gdx.input.setInputProcessor(stage);

        // Load the selected map and tower
        background = game.getSelectedMap();
        tower = game.getSelectedTower();

        // Add background and ground to stage
        Image backgroundImage = new Image(background);
        backgroundImage.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        stage.addActor(backgroundImage);

        Image catapult = new Image(new Texture("level3/gulel.png"));
        //map1_tower1
        if("12.png".equals(game.getSelectedMapPath()) && "game_ass/t1.png".equals(game.getSelectedTowerPath())) {
            catapult.setSize(180, 140);
            catapult.setPosition(0, 100);
            stage.addActor(catapult);
        }

        //map1_tower2
        else if("12.png".equals(game.getSelectedMapPath()) && "game_ass/T2.png".equals(game.getSelectedTowerPath())){
            catapult.setSize(180, 140);
            catapult.setPosition(0, 100);
            stage.addActor(catapult);
        }

        //map1_tower3
        else if("12.png".equals(game.getSelectedMapPath()) && "game_ass/T3.png".equals(game.getSelectedTowerPath())){
            catapult.setSize(180, 140);
            catapult.setPosition(0, 100);
            stage.addActor(catapult);
        }

        //map1_tower4
        else if("12.png".equals(game.getSelectedMapPath()) && "game_ass/T4.png".equals(game.getSelectedTowerPath())){
            catapult.setSize(180, 140);
            catapult.setPosition(0, 100);
            stage.addActor(catapult);
        }

        //map2_tower1_tower2_Tower3_tower4
        else if("level3/map.png".equals(game.getSelectedMapPath()) && ("game_ass/t1.png".equals(game.getSelectedTowerPath())||"game_ass/T2.png".equals(game.getSelectedTowerPath())||"game_ass/T3.png".equals(game.getSelectedTowerPath())||"game_ass/T4.png".equals(game.getSelectedTowerPath()))){
            catapult.setSize(180, 140);
            catapult.setPosition(56, 158);
            stage.addActor(catapult);
        }

        //map3_tower1
        else if("level4/map4.png".equals(game.getSelectedMapPath())){
            catapult.setSize(180, 140);
            catapult.setPosition(56, 100);
            stage.addActor(catapult);
        }

        // Create bird
        // map1_towe1
        if("12.png".equals(game.getSelectedMapPath()) && "game_ass/t1.png".equals(game.getSelectedTowerPath())) {
            if(p1){pig1 = new pig(world, "level2/p1.png", 5.02f, 3.2f);}
            pig1.getBody().setUserData("pig1");
            //pig2 = new pig(world, "game2/pig1.png", 4.99f, 2.5f);
        }

        //map1_tower2
        else if("12.png".equals(game.getSelectedMapPath()) && "game_ass/T2.png".equals(game.getSelectedTowerPath())){
            if(p1){pig1 = new pig(world, "level2/p1.png", 5.02f, 3.2f);}
            pig1.getBody().setUserData("pig1");
        }

        //map1_tower3
        else if("12.png".equals(game.getSelectedMapPath()) && "game_ass/T3.png".equals(game.getSelectedTowerPath())){
            if(p1){pig1 = new pig(world, "level2/p1.png", 5.62f, 3.8f);}
            pig1.getBody().setUserData("pig1");
        }

        //map1_tower4
        else if("12.png".equals(game.getSelectedMapPath()) && "game_ass/T4.png".equals(game.getSelectedTowerPath())){
            if(p1){pig1 = new pig(world, "level2/p1.png", 5.02f, 3.2f);}
            pig1.getBody().setUserData("pig1");
            //pig2 = new pig(world, "level4/p2.png", 5.12f, 2.1f+1.5f);
            //pig3 = new pig(world, "level4/p3.png", 5.12f, 3.4f+1.5f);
        }

        //map2_tower1
        else if("level3/map.png".equals(game.getSelectedMapPath()) && "game_ass/t1.png".equals(game.getSelectedTowerPath())){
            if(p1){pig1 = new pig(world, "level2/p1.png", 5.02f, 3.2f);}
            pig1.getBody().setUserData("pig1");
            //pig2 = new pig(world, "game2/pig1.png", 4.99f, 2.5f-0.5f);
        }

        //map2_Tower2
        else if("level3/map.png".equals(game.getSelectedMapPath()) && "game_ass/T2.png".equals(game.getSelectedTowerPath())){
            if(p1){pig1 = new pig(world, "level2/p1.png", 5.02f, 3.2f);}
            pig1.getBody().setUserData("pig1");
        }

        //map2_tower3
        else if("level3/map.png".equals(game.getSelectedMapPath()) && "game_ass/T3.png".equals(game.getSelectedTowerPath())){
            if(p1){pig1 = new pig(world, "level2/p1.png", 5.02f, 3.2f);}
            pig1.getBody().setUserData("pig1");
        }

        //map2_tower4
        else if("level3/map.png".equals(game.getSelectedMapPath()) && "game_ass/T4.png".equals(game.getSelectedTowerPath())){
            if(p1){pig1 = new pig(world, "level2/p1.png", 5.02f, 3.2f);}
            pig1.getBody().setUserData("pig1");
        }

        //map3_tower1
        else if("level4/map4.png".equals(game.getSelectedMapPath()) && "game_ass/t1.png".equals(game.getSelectedTowerPath())) {
            if(p1){pig1 = new pig(world, "level2/p1.png", 5.02f, 3.2f);}
            pig1.getBody().setUserData("pig1");
            //pig2 = new pig(world, "game2/pig1.png", 4.99f, 2.5f-1.5f);
        }

        //map3_tower2
        else if("level4/map4.png".equals(game.getSelectedMapPath()) && "game_ass/T2.png".equals(game.getSelectedTowerPath())){
            if(p1){pig1 = new pig(world, "level2/p1.png", 5.02f, 3.2f);}
            pig1.getBody().setUserData("pig1");
        }

        //map3_tower3
        else if("level4/map4.png".equals(game.getSelectedMapPath()) && "game_ass/T3.png".equals(game.getSelectedTowerPath())){
            if(p1){pig1 = new pig(world, "level2/p1.png", 5.02f, 3.2f);}
            pig1.getBody().setUserData("pig1");
        }

        //map3_tower3
        else if("level4/map4.png".equals(game.getSelectedMapPath()) && "game_ass/T4.png".equals(game.getSelectedTowerPath())){
            if(p1){pig1 = new pig(world, "level2/p1.png", 5.02f, 3.2f);}
            pig1.getBody().setUserData("pig1");
//            pig2 = new pig(world, "level4/p2.png", 5.12f, 2.1f);
//            pig3 = new pig(world, "level4/p3.png", 5.12f, 3.4f);
        }


        bird = new Bird(world, "b/bird.png", 1, 2.0f);
        b2 = new Bird(world, "b/bb.png", 0.5f, 2.2f);
        b3 = new Bird(world, "b/ch.png", 0.1f, 2.5f);

        // Create blocks

        //Map1_Tower1
        if("12.png".equals(game.getSelectedMapPath()) && "game_ass/t1.png".equals(game.getSelectedTowerPath())) {
            blocks = new ArrayList<>();
            block1 = new Block(world, "level1/t1.png", 5.2f, 2.45f, 1.2f, 0.1f);
            block2 = new Block(world, "level1/t3.png", 5.55f, 2.78f, 0.1f, 0.5f);
            block3 = new Block(world, "level1/t3.png", 4.71f, 2.78f, 0.1f, 0.5f);
            block4 = new Block(world, "level1/t1.png", 5.15f, 3.10f, 1.2f, 0.1f);
            block5 = new Block(world, "level1/t1.png", 4.7f, 3.20f, 0.4f, 0.1f);
            block6 = new Block(world, "level1/t1.png", 5.55f, 3.20f, 0.4f, 0.1f);
            block7 = new Block(world, "level1/t1.png", 4.8f, 3.30f, 0.2f, 0.1f);
            block8 = new Block(world, "level1/t1.png", 5.45f, 3.30f, 0.2f, 0.1f);
            block9 = new Block(world, "level1/t4.png", 5.13f, 3.40f, 0.6f, 0.2f);
//        block1.setRotation(0);
            block2.setRotation(0);
//        block3.setRotation(90);
        }

        //map1_tower2
        else if("12.png".equals(game.getSelectedMapPath()) && "game_ass/T2.png".equals(game.getSelectedTowerPath())){
            blocks = new ArrayList<>();
            block1 = new Block(world, "level2/t1.png", 4.8f, 2.45f, 0.15f, 0.15f);
            block2 = new Block(world, "level2/t1.png", 5.0f, 2.45f, 0.15f, 0.15f);
            block3 = new Block(world, "level2/t1.png", 5.2f, 2.45f, 0.15f, 0.15f);
            block4 = new Block(world, "level2/t2.png", 5.0f, 2.60f, 0.6f, 0.07f);
            block5 = new Block(world, "level2/t3.png", 4.8f, 2.8f, 0.12f, 0.3f);
            block6 = new Block(world, "level2/t3.png", 5.0f, 2.8f, 0.12f, 0.3f);
            block7 = new Block(world, "level2/t3.png", 5.2f, 2.8f, 0.12f, 0.3f);
            block8 = new Block(world, "level2/t2.png", 5.0f, 3.01f, 0.8f, 0.07f);
            block9 = new Block(world, "level2/t3.png", 4.7f, 3.27f, 0.12f, 0.4f);
            block10 = new Block(world, "level2/t3.png", 5.3f, 3.27f, 0.12f, 0.4f);
            block11 = new Block(world, "level2/t2.png", 5.0f, 3.55f, 0.8f, 0.07f);
            block12 = new Block(world, "level2/t4.png", 4.7f, 3.84f, 0.07f, 0.4f);
            block13 = new Block(world, "level2/t4.png", 5.3f, 3.84f, 0.07f, 0.4f);
            //pig2 = new pig(world, "level2/t5.png", 5.02f, 3.72f);
            block14 = new Block(world, "level2/t2.png", 5.0f, 4.01f, 0.8f, 0.07f);
        }

        //map1_tower3
        else if("12.png".equals(game.getSelectedMapPath()) && "game_ass/T3.png".equals(game.getSelectedTowerPath())){
            // Create blocks
            blocks = new ArrayList<>();
            block1 = new Block(world, "level3/b1.png", 5.05f, 3.18f-0.5f, 0.08f, 0.8f);
            block2 = new Block(world, "level3/b1.png", 5.5f, 3.18f-0.5f, 0.08f, 0.8f);
            block3 = new Block(world, "level3/b1.png", 4.95f, 3.18f-0.5f, 0.08f, 0.8f);
            block4 = new Block(world, "level3/b1.png", 5.6f, 3.18f-0.5f, 0.08f, 0.8f);
            block5 = new Block(world, "level3/b1.png", 5.3f, 3.6f-0.5f, 0.08f, 0.8f);
            block6 = new Block(world, "level3/b1.png", 5.3f, 3.7f-0.5f, 0.08f, 0.8f);
            // block7 = new Block(world, "level3/b2.png", 5.0f, 2.8f, 0.22f, 0.22f);
            block8 = new Block(world, "level3/b2.png", 5.18f, 3.8f-0.5f, 0.22f, 0.22f);
            block9 = new Block(world, "level3/b2.png", 5.28f, 3.8f-0.5f, 0.22f, 0.22f);
            block10 = new Block(world, "level3/b2.png", 5.38f, 3.8f-0.5f, 0.22f, 0.22f);
            block11 = new Block(world, "level3/b2.png", 5.36f, 3.9f-0.5f, 0.22f, 0.22f);
            block12 = new Block(world, "level3/b2.png", 5.25f, 3.9f-0.5f, 0.22f, 0.22f);
            block13 = new Block(world, "level3/b2.png", 5.28f, 4.0f-0.5f, 0.22f, 0.22f);


//        block1.setRotation(0);
            block6.setRotation(90);
            block5.setRotation(90);
        }

        //map1_tower4
        else if("12.png".equals(game.getSelectedMapPath()) && "game_ass/T4.png".equals(game.getSelectedTowerPath())){
            blocks = new ArrayList<>();
            block1 = new Block(world, "level4/t1.png", 5.05f, 0.89f+1.50f, 1.0f, 0.08f);
            block2 = new Block(world, "level4/t2.png", 5.40f, 1.46f+1.50f, 0.098f, 1.08f);
            block3 = new Block(world, "level4/t2.png", 4.70f, 1.46f+1.50f, 0.098f, 1.08f);
            block4 = new Block(world, "level4/t1.png", 5.05f, 2.0f+1.50f, 1.0f, 0.08f);
            block5 = new Block(world, "level4/t2.png", 5.40f, 2.70f+1.50f, 0.098f, 1.08f);
            block6 = new Block(world, "level4/t2.png", 4.70f, 2.70f+1.50f, 0.098f, 1.08f);
            block7 = new Block(world, "level4/t1.png", 5.05f, 3.19f+1.50f, 1.0f, 0.08f);
        }

        //map2_tower1
        else if("level3/map.png".equals(game.getSelectedMapPath()) && "game_ass/t1.png".equals(game.getSelectedTowerPath())){
            blocks = new ArrayList<>();
            block1 = new Block(world, "level1/t1.png", 5.2f, 2.45f-0.5f, 1.2f, 0.1f);
            block2 = new Block(world, "level1/t3.png", 5.55f, 2.78f-0.5f, 0.1f, 0.5f);
            block3 = new Block(world, "level1/t3.png", 4.71f, 2.78f-0.5f, 0.1f, 0.5f);
            block4 = new Block(world, "level1/t1.png", 5.15f, 3.10f-0.5f, 1.2f, 0.1f);
            block5 = new Block(world, "level1/t1.png", 4.7f, 3.20f-0.5f, 0.4f, 0.1f);
            block6 = new Block(world, "level1/t1.png", 5.55f, 3.20f-0.5f, 0.4f, 0.1f);
            block7 = new Block(world, "level1/t1.png", 4.8f, 3.30f-0.5f, 0.2f, 0.1f);
            block8 = new Block(world, "level1/t1.png", 5.45f, 3.30f-0.5f, 0.2f, 0.1f);
            block9 = new Block(world, "level1/t4.png", 5.13f, 3.40f-0.5f, 0.6f, 0.2f);
//        block1.setRotation(0);
            block2.setRotation(0);
//        block3.setRotation(90);
        }

        //map2_Tower2
        else if("level3/map.png".equals(game.getSelectedMapPath()) && "game_ass/T2.png".equals(game.getSelectedTowerPath())){
            blocks = new ArrayList<>();
            block1 = new Block(world, "level2/t1.png", 4.8f, 2.45f-0.5f, 0.15f, 0.15f);
            block2 = new Block(world, "level2/t1.png", 5.0f, 2.45f-0.5f, 0.15f, 0.15f);
            block3 = new Block(world, "level2/t1.png", 5.2f, 2.45f-0.5f, 0.15f, 0.15f);
            block4 = new Block(world, "level2/t2.png", 5.0f, 2.60f-0.5f, 0.6f, 0.07f);
            block5 = new Block(world, "level2/t3.png", 4.8f, 2.8f-0.5f, 0.12f, 0.3f);
            block6 = new Block(world, "level2/t3.png", 5.0f, 2.8f-0.5f, 0.12f, 0.3f);
            block7 = new Block(world, "level2/t3.png", 5.2f, 2.8f-0.5f, 0.12f, 0.3f);
            block8 = new Block(world, "level2/t2.png", 5.0f, 3.01f-0.5f, 0.8f, 0.07f);
            block9 = new Block(world, "level2/t3.png", 4.7f, 3.27f-0.5f, 0.12f, 0.4f);
            block10 = new Block(world, "level2/t3.png", 5.3f, 3.27f-0.5f, 0.12f, 0.4f);
            block11 = new Block(world, "level2/t2.png", 5.0f, 3.55f-0.5f, 0.8f, 0.07f);
            block12 = new Block(world, "level2/t4.png", 4.7f, 3.84f-0.5f, 0.07f, 0.4f);
            block13 = new Block(world, "level2/t4.png", 5.3f, 3.84f-0.5f, 0.07f, 0.4f);
            //pig2 = new pig(world, "level2/t5.png", 5.02f, 3.72f-0.5f);
            block14 = new Block(world, "level2/t2.png", 5.0f, 4.01f-0.5f, 0.8f, 0.07f);
        }

        //map2_tower3
        else if("level3/map.png".equals(game.getSelectedMapPath()) && "game_ass/T3.png".equals(game.getSelectedTowerPath())){
            blocks = new ArrayList<>();
            block1 = new Block(world, "level3/b1.png", 5.05f, 2.18f, 0.08f, 0.8f);
            block2 = new Block(world, "level3/b1.png", 5.5f, 2.18f, 0.08f, 0.8f);
            block3 = new Block(world, "level3/b1.png", 4.95f, 2.18f, 0.08f, 0.8f);
            block4 = new Block(world, "level3/b1.png", 5.6f, 2.18f, 0.08f, 0.8f);
            block5 = new Block(world, "level3/b1.png", 5.3f, 2.6f, 0.08f, 0.8f);
            block6 = new Block(world, "level3/b1.png", 5.3f, 2.7f, 0.08f, 0.8f);
            // block7 = new Block(world, "level3/b2.png", 5.0f, 2.8f, 0.22f, 0.22f);
            block8 = new Block(world, "level3/b2.png", 5.18f, 2.8f, 0.22f, 0.22f);
            block9 = new Block(world, "level3/b2.png", 5.28f, 2.8f, 0.22f, 0.22f);
            block10 = new Block(world, "level3/b2.png", 5.38f, 2.8f, 0.22f, 0.22f);
            block11 = new Block(world, "level3/b2.png", 5.36f, 2.9f, 0.22f, 0.22f);
            block12 = new Block(world, "level3/b2.png", 5.25f, 2.9f, 0.22f, 0.22f);
            block13 = new Block(world, "level3/b2.png", 5.28f, 3.0f, 0.22f, 0.22f);


//        block1.setRotation(0);
            block6.setRotation(90);
            block5.setRotation(90);
        }

        //map2_tower4
        else if("level3/map.png".equals(game.getSelectedMapPath()) && "game_ass/T4.png".equals(game.getSelectedTowerPath())){
            blocks = new ArrayList<>();
            block1 = new Block(world, "level4/t1.png", 5.05f, 0.89f+1.0f, 1.0f, 0.08f);
            block2 = new Block(world, "level4/t2.png", 5.40f, 1.46f+1.0f, 0.098f, 1.08f);
            block3 = new Block(world, "level4/t2.png", 4.70f, 1.46f+1.0f, 0.098f, 1.08f);
            block4 = new Block(world, "level4/t1.png", 5.05f, 2.0f+1.0f, 1.0f, 0.08f);
            block5 = new Block(world, "level4/t2.png", 5.40f, 2.70f+1.0f, 0.098f, 1.08f);
            block6 = new Block(world, "level4/t2.png", 4.70f, 2.70f+1.0f, 0.098f, 1.08f);
            block7 = new Block(world, "level4/t1.png", 5.05f, 3.19f+1.0f, 1.0f, 0.08f);
        }

        //map3_tower1
        else if("level4/map4.png".equals(game.getSelectedMapPath()) && "game_ass/t1.png".equals(game.getSelectedTowerPath())){
            blocks = new ArrayList<>();
            block1 = new Block(world, "level1/t1.png", 5.2f, 2.45f-1.5f, 1.2f, 0.1f);
            block2 = new Block(world, "level1/t3.png", 5.55f, 2.78f-1.5f, 0.1f, 0.5f);
            block3 = new Block(world, "level1/t3.png", 4.71f, 2.78f-1.5f, 0.1f, 0.5f);
            block4 = new Block(world, "level1/t1.png", 5.15f, 3.10f-1.5f, 1.2f, 0.1f);
            block5 = new Block(world, "level1/t1.png", 4.7f, 3.20f-1.5f, 0.4f, 0.1f);
            block6 = new Block(world, "level1/t1.png", 5.55f, 3.20f-1.5f, 0.4f, 0.1f);
            block7 = new Block(world, "level1/t1.png", 4.8f, 3.30f-1.5f, 0.2f, 0.1f);
            block8 = new Block(world, "level1/t1.png", 5.45f, 3.30f-1.5f, 0.2f, 0.1f);
            block9 = new Block(world, "level1/t4.png", 5.13f, 3.40f-1.5f, 0.6f, 0.2f);
//        block1.setRotation(0);
            block2.setRotation(0);
//        block3.setRotation(90);
        }

        //map3_tower2
        else if("level4/map4.png".equals(game.getSelectedMapPath()) && "game_ass/T2.png".equals(game.getSelectedTowerPath())){
            blocks = new ArrayList<>();
            block1 = new Block(world, "level2/t1.png", 4.8f, 2.45f-1.5f, 0.15f, 0.15f);
            block2 = new Block(world, "level2/t1.png", 5.0f, 2.45f-1.5f, 0.15f, 0.15f);
            block3 = new Block(world, "level2/t1.png", 5.2f, 2.45f-1.5f, 0.15f, 0.15f);
            block4 = new Block(world, "level2/t2.png", 5.0f, 2.60f-1.5f, 0.6f, 0.07f);
            block5 = new Block(world, "level2/t3.png", 4.8f, 2.8f-1.5f, 0.12f, 0.3f);
            block6 = new Block(world, "level2/t3.png", 5.0f, 2.8f-1.5f, 0.12f, 0.3f);
            block7 = new Block(world, "level2/t3.png", 5.2f, 2.8f-1.5f, 0.12f, 0.3f);
            block8 = new Block(world, "level2/t2.png", 5.0f, 3.01f-1.5f, 0.8f, 0.07f);
            block9 = new Block(world, "level2/t3.png", 4.7f, 3.27f-1.5f, 0.12f, 0.4f);
            block10 = new Block(world, "level2/t3.png", 5.3f, 3.27f-1.5f, 0.12f, 0.4f);
            block11 = new Block(world, "level2/t2.png", 5.0f, 3.55f-1.5f, 0.8f, 0.07f);
            block12 = new Block(world, "level2/t4.png", 4.7f, 3.84f-1.5f, 0.07f, 0.4f);
            block13 = new Block(world, "level2/t4.png", 5.3f, 3.84f-1.5f, 0.07f, 0.4f);
            //pig2 = new pig(world, "level2/t5.png", 5.02f, 3.72f-1.5f);
            block14 = new Block(world, "level2/t2.png", 5.0f, 4.01f-1.5f, 0.8f, 0.07f);
        }

        //map3_tower3
        else if("level4/map4.png".equals(game.getSelectedMapPath()) && "game_ass/T3.png".equals(game.getSelectedTowerPath())){
            blocks = new ArrayList<>();
            block1 = new Block(world, "level3/b1.png", 5.05f, 2.18f-1f, 0.08f, 0.8f);
            block2 = new Block(world, "level3/b1.png", 5.5f, 2.18f-1f, 0.08f, 0.8f);
            block3 = new Block(world, "level3/b1.png", 4.95f, 2.18f-1f, 0.08f, 0.8f);
            block4 = new Block(world, "level3/b1.png", 5.6f, 2.18f-1f, 0.08f, 0.8f);
            block5 = new Block(world, "level3/b1.png", 5.3f, 2.6f-1f, 0.08f, 0.8f);
            block6 = new Block(world, "level3/b1.png", 5.3f, 2.7f-1f, 0.08f, 0.8f);
            // block7 = new Block(world, "level3/b2.png", 5.0f, 2.8f, 0.22f, 0.22f);
            block8 = new Block(world, "level3/b2.png", 5.18f, 2.8f-1f, 0.22f, 0.22f);
            block9 = new Block(world, "level3/b2.png", 5.28f, 2.8f-1f, 0.22f, 0.22f);
            block10 = new Block(world, "level3/b2.png", 5.38f, 2.8f-1f, 0.22f, 0.22f);
            block11 = new Block(world, "level3/b2.png", 5.36f, 2.9f-1f, 0.22f, 0.22f);
            block12 = new Block(world, "level3/b2.png", 5.25f, 2.9f-1f, 0.22f, 0.22f);
            block13 = new Block(world, "level3/b2.png", 5.28f, 3.0f-1f, 0.22f, 0.22f);


//        block1.setRotation(0);
            block6.setRotation(90);
            block5.setRotation(90);
        }

        //map3_tower3
        else if("level4/map4.png".equals(game.getSelectedMapPath()) && "game_ass/T4.png".equals(game.getSelectedTowerPath())){
            blocks = new ArrayList<>();
            block1 = new Block(world, "level4/t1.png", 5.05f, 0.89f, 1.0f, 0.08f);
            block2 = new Block(world, "level4/t2.png", 5.40f, 1.46f, 0.098f, 1.08f);
            block3 = new Block(world, "level4/t2.png", 4.70f, 1.46f, 0.098f, 1.08f);
            block4 = new Block(world, "level4/t1.png", 5.05f, 2.0f, 1.0f, 0.08f);
            block5 = new Block(world, "level4/t2.png", 5.40f, 2.70f, 0.098f, 1.08f);
            block6 = new Block(world, "level4/t2.png", 4.70f, 2.70f, 0.098f, 1.08f);
            block7 = new Block(world, "level4/t1.png", 5.05f, 3.19f, 1.0f, 0.08f);
        }

        // Pause button listener
        f_Button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (check.getcheck()) {
                    game.getclickSound().play();
                }
                game.setScreen(new MainScreen(game));
                game.getLOU2().pause();
                // Change to the pause screen
            }
        });
        stage.addActor(f_Button);

        // Create ground
        createGround();
        birds = new ArrayList<>();
        birds.add(new Bird(world,"b/cu.png",3f,4f));
        birds.add(bird); // First bird
        birds.add(b2);   // Second bird
        birds.add(b3);   // Third bird

        sb = birds.get(currentBirdIndex);
        // Initialize launch impulse
        launchImpulse = new Vector2(3f, 3f);
        // Initialize the contact listener
        world.setContactListener(new ContactListener() {
            @Override
            public void beginContact(Contact contact) {
                Fixture fixtureA = contact.getFixtureA();
                Fixture fixtureB = contact.getFixtureB();


                // Handle bird touching ground
                if (isBirdTouchingGround(contact, sb)) {
                    handleBirdTouchGround();
                }

                // Handle specific pig collisions (pig1 and pig2)
                if ((fixtureA.getBody().getUserData() == "grnd" && fixtureB.getBody().getUserData() == "pig1") ||
                    (fixtureA.getBody().getUserData() == "pig1" && fixtureB.getBody().getUserData() == "grnd")) {
                    p1 = false;
                    Body pig1Body = (fixtureA.getBody().getUserData() == "pig1") ? fixtureA.getBody() : fixtureB.getBody();
                    Timer.schedule(new Timer.Task() {
                        @Override
                        public void run() {
                            if (pig1Body != null && !world.isLocked()) {
                                //world.destroyBody(pig1Body);
                            }
                        }
                    }, 1.0f);
                }

                // Check for pig2 and ground collisio


                // Check win condition
//                if (!p1 && !p2) {
//                    game.setScreen(new win(game));
//                }
            }

            @Override
            public void endContact(Contact contact) {
                // Optional: Add logic for when contact ends
            }

            @Override
            public void preSolve(Contact contact, Manifold oldManifold) {
                // Optional: Modify collision before solving
            }

            @Override
            public void postSolve(Contact contact, ContactImpulse impulse) {
                // Optional: Add logic after solving collision
            }

//            private void scheduleBodyDestruction(Body body) {
//                Timer.schedule(new Timer.Task() {
//                    @Override
//                    public void run() {
//                        if (body != null && !world.isLocked()) {
//                            world.destroyBody(body);
//                        }
//                    }
//                }, 1.0f);
//            }

        });
    }


    private void createGround() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(0, 0);
        bodyDef.type = BodyDef.BodyType.StaticBody;

        groundBody = world.createBody(bodyDef);
        groundBody.setUserData("grnd");
        ChainShape chainShape = new ChainShape();


                //Map1_Tower1
        if("12.png".equals(game.getSelectedMapPath()) && "game_ass/t1.png".equals(game.getSelectedTowerPath())) {
            //catapult tak

            chainShape.createChain(new Vector2[] {
            new Vector2(0.0f, 1.35f),
                    new Vector2(1.19f, 1.35f),
                    new Vector2(1.39f, 1.2f),
                    new Vector2(1.45f, 0.71f),
                    new Vector2(1.7107f, 0.72f),
                    new Vector2(1.7259f, 0.72f),
                    new Vector2(1.7411f, 0.72f),
                    new Vector2(1.763f, 0.72f),

                    //stone
                    new Vector2(1.993f, 0.72f),
                    new Vector2(2.03f, 0.92f),
                    new Vector2(2.17f, 0.98f),
                    new Vector2(2.27f, 1.08f),
                    new Vector2(2.47f, 1.10f),
                    new Vector2(2.67f, 1.14f),
                    new Vector2(2.80f, 0.94f),
                    new Vector2(2.85f, 0.87f),
                    new Vector2(2.95f, 0.87f),
                    new Vector2(3.07f, 0.73f),
                    new Vector2(3.15f, 0.73f),

                    //step1
                    new Vector2(3.17f, 0.85f),
                    new Vector2(3.23f, 0.95f),
                    new Vector2(3.23f, 1.05f),
                    new Vector2(3.29f, 1.15f),
                    new Vector2(3.35f, 1.23f),
                    new Vector2(3.63f, 1.23f),

                    //step2
                    new Vector2(3.68f, 1.33f),
                    new Vector2(3.70f, 1.43f),
                    new Vector2(3.80f, 1.45f),
                    new Vector2(3.93f, 1.70f),
                    new Vector2(4.00f, 1.70f),
                    new Vector2(4.08f, 1.75f),
                    new Vector2(4.28f, 1.75f),

                    //step3
                    new Vector2(4.32f, 1.85f),
                    new Vector2(4.32f, 1.93f),
                    new Vector2(4.40f, 1.96f),
                    new Vector2(4.40f, 2.01f),
                    new Vector2(4.43f, 2.10f),
                    new Vector2(4.40f, 2.18f),
                    new Vector2(4.40f, 2.27f),
                    new Vector2(4.70f, 2.30f),

                    //platform
                    new Vector2(5.70f, 2.30f),

                    //rock 2
                    new Vector2(5.75f, 2.40f),
                    new Vector2(5.75f, 2.45f),
                    new Vector2(5.80f, 2.50f),
                    new Vector2(5.84f, 2.60f),
                    new Vector2(5.88f, 2.70f),
                    new Vector2(5.99f, 2.73f),
                    new Vector2(6.06f, 2.75f),
                    new Vector2(6.09f, 2.76f),
                    new Vector2(6.15f, 2.89f),
                    new Vector2(6.35f, 2.93f),

                    //in bound map
                    new Vector2(6.35f, 10.93f),
            });

        }

        //MAP1_towwer2
        else if("12.png".equals(game.getSelectedMapPath()) && "game_ass/T2.png".equals(game.getSelectedTowerPath())){
            //catapult tak

                chainShape.createChain(new Vector2[] {
            new Vector2(0.0f, 1.35f),
                    new Vector2(1.19f, 1.35f),
                    new Vector2(1.39f, 1.2f),
                    new Vector2(1.45f, 0.71f),
                    new Vector2(1.7107f, 0.72f),
                    new Vector2(1.7259f, 0.72f),
                    new Vector2(1.7411f, 0.72f),
                    new Vector2(1.763f, 0.72f),

                    //stone
                    new Vector2(1.993f, 0.72f),
                    new Vector2(2.03f, 0.92f),
                    new Vector2(2.17f, 0.98f),
                    new Vector2(2.27f, 1.08f),
                    new Vector2(2.47f, 1.10f),
                    new Vector2(2.67f, 1.14f),
                    new Vector2(2.80f, 0.94f),
                    new Vector2(2.85f, 0.87f),
                    new Vector2(2.95f, 0.87f),
                    new Vector2(3.07f, 0.73f),
                    new Vector2(3.15f, 0.73f),

                    //step1
                    new Vector2(3.17f, 0.85f),
                    new Vector2(3.23f, 0.95f),
                    new Vector2(3.23f, 1.05f),
                    new Vector2(3.29f, 1.15f),
                    new Vector2(3.35f, 1.23f),
                    new Vector2(3.63f, 1.23f),

                    //step2
                    new Vector2(3.68f, 1.33f),
                    new Vector2(3.70f, 1.43f),
                    new Vector2(3.80f, 1.45f),
                    new Vector2(3.93f, 1.70f),
                    new Vector2(4.00f, 1.70f),
                    new Vector2(4.08f, 1.75f),
                    new Vector2(4.28f, 1.75f),

                    //step3
                    new Vector2(4.32f, 1.85f),
                    new Vector2(4.32f, 1.93f),
                    new Vector2(4.40f, 1.96f),
                    new Vector2(4.40f, 2.01f),
                    new Vector2(4.43f, 2.10f),
                    new Vector2(4.40f, 2.18f),
                    new Vector2(4.40f, 2.27f),
                    new Vector2(4.70f, 2.30f),

                    //platform
                    new Vector2(5.70f, 2.30f),

                    //rock 2
                    new Vector2(5.75f, 2.40f),
                    new Vector2(5.75f, 2.45f),
                    new Vector2(5.80f, 2.50f),
                    new Vector2(5.84f, 2.60f),
                    new Vector2(5.88f, 2.70f),
                    new Vector2(5.99f, 2.73f),
                    new Vector2(6.06f, 2.75f),
                    new Vector2(6.09f, 2.76f),
                    new Vector2(6.15f, 2.89f),
                    new Vector2(6.35f, 2.93f),

                    //in bound map
                    new Vector2(6.35f, 10.93f),
                });

            }

        //map1_tower3
        else if("12.png".equals(game.getSelectedMapPath()) && "game_ass/T3.png".equals(game.getSelectedTowerPath())){
            chainShape.createChain(new Vector2[] {
                    new Vector2(0.0f, 1.35f),
                    new Vector2(1.19f, 1.35f),
                    new Vector2(1.39f, 1.2f),
                    new Vector2(1.45f, 0.71f),
                    new Vector2(1.7107f, 0.72f),
                    new Vector2(1.7259f, 0.72f),
                    new Vector2(1.7411f, 0.72f),
                    new Vector2(1.763f, 0.72f),

                    //stone
                    new Vector2(1.993f, 0.72f),
                    new Vector2(2.03f, 0.92f),
                    new Vector2(2.17f, 0.98f),
                    new Vector2(2.27f, 1.08f),
                    new Vector2(2.47f, 1.10f),
                    new Vector2(2.67f, 1.14f),
                    new Vector2(2.80f, 0.94f),
                    new Vector2(2.85f, 0.87f),
                    new Vector2(2.95f, 0.87f),
                    new Vector2(3.07f, 0.73f),
                    new Vector2(3.15f, 0.73f),

                    //step1
                    new Vector2(3.17f, 0.85f),
                    new Vector2(3.23f, 0.95f),
                    new Vector2(3.23f, 1.05f),
                    new Vector2(3.29f, 1.15f),
                    new Vector2(3.35f, 1.23f),
                    new Vector2(3.63f, 1.23f),

                    //step2
                    new Vector2(3.68f, 1.33f),
                    new Vector2(3.70f, 1.43f),
                    new Vector2(3.80f, 1.45f),
                    new Vector2(3.93f, 1.70f),
                    new Vector2(4.00f, 1.70f),
                    new Vector2(4.08f, 1.75f),
                    new Vector2(4.28f, 1.75f),

                    //step3
                    new Vector2(4.32f, 1.85f),
                    new Vector2(4.32f, 1.93f),
                    new Vector2(4.40f, 1.96f),
                    new Vector2(4.40f, 2.01f),
                    new Vector2(4.43f, 2.10f),
                    new Vector2(4.40f, 2.18f),
                    new Vector2(4.40f, 2.27f),
                    new Vector2(4.70f, 2.30f),

                    //platform
                    new Vector2(5.70f, 2.30f),

                    //rock 2
                    new Vector2(5.75f, 2.40f),
                    new Vector2(5.75f, 2.45f),
                    new Vector2(5.80f, 2.50f),
                    new Vector2(5.84f, 2.60f),
                    new Vector2(5.88f, 2.70f),
                    new Vector2(5.99f, 2.73f),
                    new Vector2(6.06f, 2.75f),
                    new Vector2(6.09f, 2.76f),
                    new Vector2(6.15f, 2.89f),
                    new Vector2(6.35f, 2.93f),

                    //in bound map
                    new Vector2(6.35f, 10.93f),
            });
        }

        //map1_tower4
        else if("12.png".equals(game.getSelectedMapPath()) && "game_ass/T4.png".equals(game.getSelectedTowerPath())){
            chainShape.createChain(new Vector2[] {
                    new Vector2(0.0f, 1.35f),
                    new Vector2(1.19f, 1.35f),
                    new Vector2(1.39f, 1.2f),
                    new Vector2(1.45f, 0.71f),
                    new Vector2(1.7107f, 0.72f),
                    new Vector2(1.7259f, 0.72f),
                    new Vector2(1.7411f, 0.72f),
                    new Vector2(1.763f, 0.72f),

                    //stone
                    new Vector2(1.993f, 0.72f),
                    new Vector2(2.03f, 0.92f),
                    new Vector2(2.17f, 0.98f),
                    new Vector2(2.27f, 1.08f),
                    new Vector2(2.47f, 1.10f),
                    new Vector2(2.67f, 1.14f),
                    new Vector2(2.80f, 0.94f),
                    new Vector2(2.85f, 0.87f),
                    new Vector2(2.95f, 0.87f),
                    new Vector2(3.07f, 0.73f),
                    new Vector2(3.15f, 0.73f),

                    //step1
                    new Vector2(3.17f, 0.85f),
                    new Vector2(3.23f, 0.95f),
                    new Vector2(3.23f, 1.05f),
                    new Vector2(3.29f, 1.15f),
                    new Vector2(3.35f, 1.23f),
                    new Vector2(3.63f, 1.23f),

                    //step2
                    new Vector2(3.68f, 1.33f),
                    new Vector2(3.70f, 1.43f),
                    new Vector2(3.80f, 1.45f),
                    new Vector2(3.93f, 1.70f),
                    new Vector2(4.00f, 1.70f),
                    new Vector2(4.08f, 1.75f),
                    new Vector2(4.28f, 1.75f),

                    //step3
                    new Vector2(4.32f, 1.85f),
                    new Vector2(4.32f, 1.93f),
                    new Vector2(4.40f, 1.96f),
                    new Vector2(4.40f, 2.01f),
                    new Vector2(4.43f, 2.10f),
                    new Vector2(4.40f, 2.18f),
                    new Vector2(4.40f, 2.27f),
                    new Vector2(4.70f, 2.30f),

                    //platform
                    new Vector2(5.70f, 2.30f),

                    //rock 2
                    new Vector2(5.75f, 2.40f),
                    new Vector2(5.75f, 2.45f),
                    new Vector2(5.80f, 2.50f),
                    new Vector2(5.84f, 2.60f),
                    new Vector2(5.88f, 2.70f),
                    new Vector2(5.99f, 2.73f),
                    new Vector2(6.06f, 2.75f),
                    new Vector2(6.09f, 2.76f),
                    new Vector2(6.15f, 2.89f),
                    new Vector2(6.35f, 2.93f),

                    //in bound map
                    new Vector2(6.35f, 10.93f),
            });
        }

        //map2_tower1
        else if("level3/map.png".equals(game.getSelectedMapPath()) && ("game_ass/t1.png".equals(game.getSelectedTowerPath())||"game_ass/T2.png".equals(game.getSelectedTowerPath())||"game_ass/T3.png".equals(game.getSelectedTowerPath())||"game_ass/T4.png".equals(game.getSelectedTowerPath()))){
            chainShape.createChain(new Vector2[] {
                //catapult tak
                new Vector2(0.0f, 10.35f),
                new Vector2(0.0f, 1.80f),
                new Vector2(0.9f, 1.80f),
                new Vector2(1.2f, 1.80f),
                new Vector2(6.8f, 1.80f),
                new Vector2(6.8f, 10.80f),









        });
        }

        else if("level4/map4.png".equals(game.getSelectedMapPath()) && ("game_ass/t1.png".equals(game.getSelectedTowerPath())||"game_ass/T2.png".equals(game.getSelectedTowerPath())||"game_ass/T3.png".equals(game.getSelectedTowerPath())||"game_ass/T4.png".equals(game.getSelectedTowerPath()))){
            chainShape.createChain(new Vector2[] {
                    //catapult tak
                    new Vector2(0.0f, 10.35f),
                    new Vector2(0.0f, 1.35f),
                    new Vector2(1.2f, 1.35f),
                    new Vector2(1.8f, 1.10f),
                    new Vector2(1.9f, 1.05f),
                    new Vector2(1.9f, 1.00f),
                    new Vector2(2.0f, 1.00f),
                    new Vector2(2.0f, 0.80f),
                    new Vector2(6.5f, 0.80f),
                    new Vector2(6.5f, 6.80f),
            });
        }




        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = chainShape;
        fixtureDef.friction = 0.8f;
        groundBody.createFixture(fixtureDef);

        chainShape.dispose();
    }

    private void drawTrajectory(Vector2 launchImpulse) {
        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);

        Vector2 birdPosition = sb.getBody().getPosition();
        float initialVelocityX = launchImpulse.x;
        float initialVelocityY = launchImpulse.y;
        float gravity = world.getGravity().y;

        float timeStep = 0.01f;
        int steps = 50;
        Vector2 previousPoint = new Vector2(birdPosition);

        for (int i = 1; i <= steps; i++) {
            float t = i * timeStep;
            float x = birdPosition.x + initialVelocityX * t;
            float y = birdPosition.y + initialVelocityY * t + 0.5f * gravity * t * t;

            Vector2 currentPoint = new Vector2(x, y);
            shapeRenderer.line(previousPoint, currentPoint);

            previousPoint.set(currentPoint);

            if (y < 0) break;
        }

        shapeRenderer.end();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.5f, 0.8f, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();

        stage.act(delta);
        stage.draw();

        if (!isLaunched) {
            drawTrajectory(launchImpulse);
        }

        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        bird.draw(batch);
        b2.draw(batch);
        b3.draw(batch);

        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                if(l>7){
                    game.setScreen(new lose(game));
                    game.getLOU2().pause();
                }
            }
        }, 1.0f);

        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                if(!p1){
                    game.setScreen(new win(game));
                    game.getLOU2().pause();
                }
            }
        }, 1.0f);
        //MAP1_Tower1
        if(("12.png".equals(game.getSelectedMapPath())||"level3/map.png".equals(game.getSelectedMapPath())||"level4/map4.png".equals(game.getSelectedMapPath())) && "game_ass/t1.png".equals(game.getSelectedTowerPath())) {
            if(p1){pig1.draw(batch);}
            //pig2.draw(batch);
            block1.draw(batch);
            block2.draw(batch);
            block3.draw(batch);
            block4.draw(batch);
            block5.draw(batch);
            block6.draw(batch);
            block7.draw(batch);
            block8.draw(batch);
            block9.draw(batch);
        }

        //map1_tower2
        else if(("12.png".equals(game.getSelectedMapPath())||"level3/map.png".equals(game.getSelectedMapPath())||"level4/map4.png".equals(game.getSelectedMapPath())) && "game_ass/T2.png".equals(game.getSelectedTowerPath())) {
            if(p1){pig1.draw(batch);}
            //pig2.draw(batch);
            block1.draw(batch);
            block2.draw(batch);
            block3.draw(batch);
            block4.draw(batch);
            block5.draw(batch);
            block6.draw(batch);
            block7.draw(batch);
            block8.draw(batch);
            block9.draw(batch);
            block10.draw(batch);
            block11.draw(batch);
            block12.draw(batch);
            block13.draw(batch);
            block14.draw(batch);

        }

        //map1_tower3
        else if(("12.png".equals(game.getSelectedMapPath())||"level3/map.png".equals(game.getSelectedMapPath())||"level4/map4.png".equals(game.getSelectedMapPath())) && "game_ass/T3.png".equals(game.getSelectedTowerPath())){
            if(p1){pig1.draw(batch);}
            block1.draw(batch);
            block2.draw(batch);
            block3.draw(batch);
            block4.draw(batch);
            block5.draw(batch);
            block6.draw(batch);
            //       block7.draw(batch);
            block8.draw(batch);
            block9.draw(batch);
            block10.draw(batch);
            block11.draw(batch);
            block12.draw(batch);
            block13.draw(batch);
            //    block14.draw(batch);
        }

        //map1_tower4
        else if(("12.png".equals(game.getSelectedMapPath())||"level3/map.png".equals(game.getSelectedMapPath())||"level4/map4.png".equals(game.getSelectedMapPath())) && "game_ass/T4.png".equals(game.getSelectedTowerPath())){
            if(p1){pig1.draw(batch);}
            //pig2.draw(batch);
            //pig3.draw(batch);
            b2.draw(batch);
            b3.draw(batch);
            block1.draw(batch);
            block2.draw(batch);
            block3.draw(batch);
            block4.draw(batch);
            block5.draw(batch);
            block6.draw(batch);
            block7.draw(batch);
        }



        batch.end();

        world.step(1 / 60f, 6, 2);
        debugRenderer.render(world, camera.combined);

        handleInput();

        // Update explosions
//        for (Explosion explosion : explosions) {
//            explosion.update(delta);
//        }//explosions.removeIf(Explosion::isFinished); // Remove finished explosions

        // Handle pig destruction and trigger explosions
    }

    private boolean isBirdTouchingGround(Contact contact, Bird bird) {
        // Check if the bird's body is involved in the collision with the ground
        Fixture birdFixture = bird.getBody().getFixtureList().first();
        Fixture groundFixture = groundBody.getFixtureList().first();
        return (contact.getFixtureA() == birdFixture && contact.getFixtureB() == groundFixture) ||
                (contact.getFixtureB() == birdFixture && contact.getFixtureA() == groundFixture);
    }
    private void handleBirdTouchGround() {
        l++;
        if (currentBirdIndex < birds.size() - 1) {
            currentBirdIndex++; // Move to the next bird
            sb = birds.get(currentBirdIndex); // Set the new bird as controllable
            isLaunched = false; // Reset launch status
            launchImpulse.set(5f, 5f);
            world.setGravity(new Vector2(0, -2.5f)); // Reset launch impulse if needed
        } else {
            System.out.println("All birds have been used.");
        }
    }

    private void handleInput() {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) launchImpulse.x += 0.5f;
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) launchImpulse.x -= 0.5f;
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) launchImpulse.y += 0.5f;
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) launchImpulse.y -= 0.5f;

        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE) && !isLaunched) {
            sb.launch(launchImpulse);
            isLaunched = true;
        }
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        batch.dispose();
        stage.dispose();
        pig1.dispose();
        //pig2.dispose();
        bird.dispose();
        b2.dispose();
        b3.dispose();
        shapeRenderer.dispose();
        pause.dispose();
        for (Block block : blocks) block.dispose();
        world.dispose();
        for (pig p : pigs) {
            p.dispose();
        }
    }
}
