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
import com.badlogic.gdx.math.collision.BoundingBox;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Gameplay implements Screen {
    SpriteBatch batch;
    private Bird bird;
    private Bird b2;
    private Bird b3;
    //private World world;
    World world;
    private static List<Block> blocks;
    private Texture pause;
    private ShapeRenderer shapeRenderer;
    private OrthographicCamera camera;
    private Box2DDebugRenderer debugRenderer;
    Stage stage;
    public static final float PPM = 100; // Pixels Per Meter
    Body groundBody;
    private Texture c;
    private Block block1,block2,block3,block4,block5,block6,block7,block8,block9;

    Vector2 launchImpulse;
    private boolean isLaunched;
    ImageButton f_Button;
    ImageButton save_s;
    private chick game;
    private int a=3;// Reference to your main game class\
    private boolean p1 = true;
    private boolean p2 = true;

    private int l=4;

    Bird sb;

    private pig pig1,pig2;
    private List<pig> pigs = new ArrayList<>();
    private List<pig> pigsToDestroy = new ArrayList<>();
    private List<Explosion> explosions = new ArrayList<>();
    private String[] explosionFrames = {"ex/1.png", "ex/2.png", "ex/3.png","ex/4.png","ex/5.png"}; // Explosion frames


    int currentBirdIndex = 0;
    private static List<Bird> birds;
    private List<Body> bodiesToDestroy = new ArrayList<>();
    private Map<Body, Integer> touchCounters = new HashMap<>();

    private boolean b_1 = true;
    private boolean b_2 = true;
    private boolean b_3 = true;
    private boolean b_4 = true;
    private boolean b_5 = true;
    private boolean b_6 = true;
    private boolean b_7 = true;
    private boolean b_8 = true;
    private boolean b_9 = true;


    public Gameplay(chick game) {
        this.game = game; }

    @Override
    public void show() {
        world = new World(new Vector2(0, -2.5f), true);
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        isLaunched = false;

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

        // Add background and ground to stage
        Image backgroundImage = new Image(new Texture("12.png"));
        backgroundImage.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        stage.addActor(backgroundImage);

        Image groundImage = new Image(new Texture("l1.png"));
        groundImage.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        groundImage.setPosition(0, 0);
        stage.addActor(groundImage);

        Image catapult = new Image(new Texture("c.png"));
        catapult.setSize(50, 80);
        catapult.setPosition(56, 120);
        stage.addActor(catapult);

        // Create bird
        if(p1){pig1 = new pig(world, "game2/pig1.png", 5.32f, 2.5f);}
        pig1.getBody().setUserData("pig1");
        pigs.add(pig1);
        if(p2){pig2 = new pig(world, "game2/pig1.png", 4.99f, 2.5f);}
        pig2.getBody().setUserData("pig2");
        pigs.add(pig2);
        bird = new Bird(world, "b/bird.png", 1, 1.5f);
        b2 = new Bird(world, "b/bb.png", 0.5f, 1.5f);
        b3 = new Bird(world, "b/ch.png", 0.1f, 1.5f);
        bird.getBody().setUserData("bird");
        b2.getBody().setUserData("bird");
        b3.getBody().setUserData("bird");
//

//        bird = global.birds_selected.get(0);
//        bird.setPosition(1,1.5f);
//        b2 =  global.birds_selected.get(1);
//        b2.setPosition(0.5f,1.5f);
//        b3 =  global.birds_selected.get(2);
//        b3.setPosition(0.1f,1.5f);

        // Create blocks
        blocks = new ArrayList<>();
        if(b_1 = true){block1 = new Block(world, "level1/t1.png", 5.2f, 2.45f, 1.2f, 0.1f);}
        block1.getBody().setUserData("b1");
        if(b_2 = true){block2 = new Block(world, "level1/t3.png", 5.55f, 2.78f, 0.1f, 0.5f);}
        block2.getBody().setUserData("b2");
        if(b_3 = true){block3 = new Block(world, "level1/t3.png", 4.71f, 2.78f, 0.1f, 0.5f);}
        block2.getBody().setUserData("b3");
        if(b_4 = true){block4 = new Block(world, "level1/t1.png", 5.15f, 3.10f, 1.2f, 0.1f);}
        block4.getBody().setUserData("b4");
        if(b_5 = true){block5 = new Block(world, "level1/t1.png", 4.7f, 3.20f, 0.4f, 0.1f);}
        block5.getBody().setUserData("b5");
        if(b_6 = true){block6 = new Block(world, "level1/t1.png", 5.55f, 3.20f, 0.4f, 0.1f);}
        block6.getBody().setUserData("b6");
        if(b_7 = true){block7 = new Block(world, "level1/t1.png", 4.8f, 3.30f, 0.2f, 0.1f);}
        block7.getBody().setUserData("b7");
        if(b_8 = true){block8 = new Block(world, "level1/t1.png", 5.45f, 3.30f, 0.2f, 0.1f);}
        block8.getBody().setUserData("b8");
        if(b_9 = true){block9 = new Block(world, "level1/t4.png", 5.13f, 3.40f, 0.6f, 0.2f);}
        block9.getBody().setUserData("b9");

        blocks.add(block1);
        blocks.add(block2);
        blocks.add(block3);
        blocks.add(block4);
        blocks.add(block5);
        blocks.add(block6);
        blocks.add(block7);
        blocks.add(block8);
        blocks.add(block9);


//        block1.setRotation(0);
        block2.setRotation(0);
//        block3.setRotation(90);

        // Pause button listener
        f_Button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (check.getcheck()) {
                    game.getclickSound().play();
                }
                Gameplay gameplay = (Gameplay) game.getScreen();
                game.getHK1().pause();
                game.setScreen(new Pause(game ,gameplay)); // Change to the pause screen
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

        saveGameState(pigs,"game_p.dat");
        saveGameState_b(blocks,"game_bl.dat");
        saveGameState_bi(birds,"game_bi.dat");


        sb = birds.get(currentBirdIndex);
        launchImpulse = new Vector2(3f, 3f);

        world.setContactListener(new ContactListener() {
            @Override
            public void beginContact(Contact contact) {
                Fixture fixtureA = contact.getFixtureA();
                Fixture fixtureB = contact.getFixtureB();

                if (isBirdTouchingGround(contact, sb)) {
                    handleBirdTouchGround();
                }
                if ((fixtureA.getBody().getUserData() == "grnd" && fixtureB.getBody().getUserData() == "pig1") ||
                    (fixtureA.getBody().getUserData() == "pig1" && fixtureB.getBody().getUserData() == "grnd") ||
                    (fixtureA.getBody().getUserData() == "grnd" && fixtureB.getBody().getUserData() == "pig2") ||
                    (fixtureA.getBody().getUserData() == "pig2" && fixtureB.getBody().getUserData() == "grnd") ||
                    (fixtureA.getBody().getUserData() == "bird" && fixtureB.getBody().getUserData() == "pig1") ||
                    (fixtureA.getBody().getUserData() == "pig1" && fixtureB.getBody().getUserData() == "bird")||
                    (fixtureA.getBody().getUserData() == "bird" && fixtureB.getBody().getUserData() == "pig2") ||
                    (fixtureA.getBody().getUserData() == "pig2" && fixtureB.getBody().getUserData() == "bird")) {

                    // Determine the pig body
                    Body pigBody = (fixtureA.getBody().getUserData().toString().startsWith("pig")) ? fixtureA.getBody() : fixtureB.getBody();
                    String pigId = pigBody.getUserData().toString(); // "pig1" or "pig2"

                    synchronized (this) { // Thread-safe modification of shared state
                        touchCounters.putIfAbsent(pigBody, 0);
                        touchCounters.put(pigBody, touchCounters.get(pigBody) + 1);

                        // Check if the pig has reached the destruction threshold
                        if (touchCounters.get(pigBody) >= 3) {
                            if ("pig1".equals(pigId)) p1 = false;
                            if ("pig2".equals(pigId)) p2 = false;

                            touchCounters.remove(pigBody); // Cleanup counter

                            Timer.schedule(new Timer.Task() {
                                @Override
                                public void run() {
                                    synchronized (this) { // Ensure safe access to shared data
                                        if (pigBody != null && !world.isLocked() && !bodiesToDestroy.contains(pigBody)) {
                                            bodiesToDestroy.add(pigBody);
                                        }
                                    }
                                }
                            }, 0.0f);
                        }
                    }
                }

                if((fixtureA.getBody().getUserData() == "bird" && fixtureB.getBody().getUserData() == "b1") ||
                    (fixtureA.getBody().getUserData() == "b1" && fixtureB.getBody().getUserData() == "bird")||
                    (fixtureA.getBody().getUserData() == "bird" && fixtureB.getBody().getUserData() == "b2") ||
                    (fixtureA.getBody().getUserData() == "b2" && fixtureB.getBody().getUserData() == "bird") ||
                    (fixtureA.getBody().getUserData() == "bird" && fixtureB.getBody().getUserData() == "b3") ||
                    (fixtureA.getBody().getUserData() == "b3" && fixtureB.getBody().getUserData() == "bird") ||
                    (fixtureA.getBody().getUserData() == "bird" && fixtureB.getBody().getUserData() == "b4") ||
                    (fixtureA.getBody().getUserData() == "b4" && fixtureB.getBody().getUserData() == "bird") ||
                    (fixtureA.getBody().getUserData() == "bird" && fixtureB.getBody().getUserData() == "b5") ||
                    (fixtureA.getBody().getUserData() == "b5" && fixtureB.getBody().getUserData() == "bird") ||
                    (fixtureA.getBody().getUserData() == "bird" && fixtureB.getBody().getUserData() == "b6") ||
                    (fixtureA.getBody().getUserData() == "b6" && fixtureB.getBody().getUserData() == "bird") ||
                    (fixtureA.getBody().getUserData() == "bird" && fixtureB.getBody().getUserData() == "b7") ||
                    (fixtureA.getBody().getUserData() == "b7" && fixtureB.getBody().getUserData() == "bird") ||
                    (fixtureA.getBody().getUserData() == "bird" && fixtureB.getBody().getUserData() == "b8") ||
                    (fixtureA.getBody().getUserData() == "b8" && fixtureB.getBody().getUserData() == "bird") ||
                    (fixtureA.getBody().getUserData() == "bird" && fixtureB.getBody().getUserData() == "b9") ||
                    (fixtureA.getBody().getUserData() == "b9" && fixtureB.getBody().getUserData() == "bird")
                ){
                    Body block = (fixtureA.getBody().getUserData().toString().startsWith("b")) ? fixtureA.getBody() : fixtureB.getBody();
                    String bId = block.getUserData().toString();

                    synchronized (this) { // Thread-safe modification of shared state
                        touchCounters.putIfAbsent(block, 0);
                        touchCounters.put(block, touchCounters.get(block) + 1);

                        // Check if the pig has reached the destruction threshold
                        if (touchCounters.get(block) >= 2) {
                            if ("b1".equals(bId)) b_1 = false;
                            if ("b2".equals(bId)) b_2 = false;
                            if ("b3".equals(bId)) b_3 = false;
                            if ("b4".equals(bId)) b_4 = false;
                            if ("b5".equals(bId)) b_5 = false;
                            if ("b6".equals(bId)) b_6 = false;
                            if ("b7".equals(bId)) b_7 = false;
                            if ("b8".equals(bId)) b_8 = false;
                            if ("b9".equals(bId)) b_9 = false;

                            touchCounters.remove(block); // Cleanup counter

                            Timer.schedule(new Timer.Task() {
                                @Override
                                public void run() {
                                    synchronized (this) { // Ensure safe access to shared data
                                        if (block != null && !world.isLocked() && !bodiesToDestroy.contains(block)) {
                                            bodiesToDestroy.add(block);
                                        }
                                    }
                                }
                            }, 0.0f);
                        }
                    }
                }



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
//

    }
    public static void saveGameState(List<pig> pigs, String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(pigs); // Serializing the list of pigs
            System.out.println("Game state saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving game state: " + e.getMessage());
        }
    }
    public static void saveGameState_b(List<Block> pigs, String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(pigs); // Serializing the list of pigs
            System.out.println("Game state saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving game state: " + e.getMessage());
        }
    }
    public static void saveGameState_bi(List<Bird> pigs, String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(pigs); // Serializing the list of pigs
            System.out.println("Game state saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving game state: " + e.getMessage());
        }
    }
//    public static void saveGameState_b(List<Block> b, String filename) {
//        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
//            oos.writeObject(blocks); // Serializing the list of pigs
//            System.out.println("Game state saved successfully.");
//        } catch (IOException e) {
//            System.out.println("Error saving game state: " + e.getMessage());
//        }
//    }


    void createGround() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(0, 0);
        bodyDef.type = BodyDef.BodyType.StaticBody;

        groundBody = world.createBody(bodyDef);
        groundBody.setUserData("grnd");

        ChainShape chainShape = new ChainShape();
        chainShape.createChain(new Vector2[] {
            //catapult tak
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

        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                if(l>7){
                    game.setScreen(new lose(game));
                    game.getHK1().pause();
                }
            }
        }, 5.0f);

        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                if(!p1&&!p2){
                    game.setScreen(new win(game));
                    game.getHK1().pause();
                }
            }
        }, 5.0f);


        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        if(p1){pig1.draw(batch);}
        if(p2){pig2.draw(batch);}
        bird.draw(batch);
        b2.draw(batch);
        b3.draw(batch);
        if(b_1){block1.draw(batch);}
        if(b_2){block2.draw(batch);}
        if(b_3){block3.draw(batch);}
        if(b_4){block4.draw(batch);}
        if(b_5){block5.draw(batch);}
        if(b_6){block6.draw(batch);}
        if(b_7){block7.draw(batch);}
        if(b_8){block8.draw(batch);}
        if(b_9){block9.draw(batch);}

        batch.end();

        world.step(1 / 60f, 6, 2);
        Iterator<Body> iterator = bodiesToDestroy.iterator();
        while (iterator.hasNext()) {
            Body body = iterator.next();
            if (!world.isLocked()) { // Ensure the world is not locked
                world.destroyBody(body);
                iterator.remove(); // Remove from list after destruction
            }
        }
        debugRenderer.render(world, camera.combined);

        handleInput();

        // Update explosions
//        for (Explosion explosion : explosions) {
//            explosion.update(delta);
//        }//explosions.removeIf(Explosion::isFinished); // Remove finished explosions

        // Handle pig destruction and trigger explosions
    }

    boolean isBirdTouchingGround(Contact contact, Bird bird) {
        // Check if the bird's body is involved in the collision with the ground
        Fixture birdFixture = bird.getBody().getFixtureList().first();
        Fixture groundFixture = groundBody.getFixtureList().first();

        return (contact.getFixtureA() == birdFixture && contact.getFixtureB() == groundFixture) ||
            (contact.getFixtureB() == birdFixture && contact.getFixtureA() == groundFixture);
    }
    void handleBirdTouchGround() {
        l++;
        if (currentBirdIndex < birds.size() - 1) {
            currentBirdIndex++; // Move to the next bird
            sb = birds.get(currentBirdIndex); // Set the new bird as controllable
            isLaunched = false; // Reset launch status
            launchImpulse.set(5f, 5f); // Reset launch impulse if needed

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

//        pig1.dispose();
//        pig2.dispose();
        if (bird != null)bird.dispose();
        if (b2 != null)b2.dispose();
        if (b3 != null)b3.dispose();
        if(!block1.isIsd()){shapeRenderer.dispose();}
        pause.dispose();
        //for (Block block : blocks) block.dispose();
        if (world != null)world.dispose();
//        for (pig p : pigs) {
//            p.dispose();
//        }
    }
}
