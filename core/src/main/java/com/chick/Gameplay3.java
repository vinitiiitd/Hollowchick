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
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Gameplay3 implements Screen {
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
    private Block block1,block2,block3,block4,block5,block6,block7,block8,block9,block10,block11,block12,block13;

    private Vector2 launchImpulse;
    private boolean isLaunched;
    private ImageButton f_Button;
    private chick game;
    private int a=3;// Reference to your main game class\

    private Bird sb;

    private pig pig1,pig2;
    private boolean p1 = true;


    private boolean b_1,b_2,b_3,b_4,b_5,b_6,b_7,b_8,b_9,b_10,b_11,b_12,b_13,b_14 = true;
    private List<pig> pigs = new ArrayList<>();
    private List<pig> pigsToDestroy = new ArrayList<>();
    private List<Explosion> explosions = new ArrayList<>();
    private String[] explosionFrames = {"ex/1.png", "ex/2.png", "ex/3.png","ex/4.png","ex/5.png"}; // Explosion frames


    private int currentBirdIndex = 0;

    private List<Body> bodiesToDestroy = new ArrayList<>();
    private Map<Body, Integer> touchCounters = new HashMap<>();
    private List<Bird> birds;

    private int l = 4;


    public Gameplay3(chick game) {
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

        // Add background and ground to stage
        Image backgroundImage = new Image(new Texture("level3/map.png"));
        backgroundImage.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        stage.addActor(backgroundImage);

        Image catapult = new Image(new Texture("level3/gulel.png"));
        catapult.setSize(180, 140);
        catapult.setPosition(56, 158);
        stage.addActor(catapult);

        // Create bird
        if(p1){pig1 = new pig(world, "level2/p1.png", 5.28f, 1.8f);}
        pig1.getBody().setUserData("pig1");

        bird = new Bird(world, "b/bird.png", 1, 2.0f);
        b2 = new Bird(world, "b/bb.png", 0.5f, 2.2f);
        b3 = new Bird(world, "b/ch.png", 0.1f, 2.5f);
        bird.getBody().setUserData("bird");
        b2.getBody().setUserData("bird");
        b3.getBody().setUserData("bird");

        blocks = new ArrayList<>();
        if(b_1 = true){block1 = new Block(world, "level3/b1.png", 5.05f, 2.18f, 0.08f, 0.8f);}
        block1.getBody().setUserData("b1");
        if(b_2 = true){block2 = new Block(world, "level3/b1.png", 5.5f, 2.18f, 0.08f, 0.8f);}
        block2.getBody().setUserData("b2");
        if(b_3 = true){block3 = new Block(world, "level3/b1.png", 4.95f, 2.18f, 0.08f, 0.8f);}
        block2.getBody().setUserData("b3");
        if(b_4 = true){block4 = new Block(world, "level3/b1.png", 5.6f, 2.18f, 0.08f, 0.8f);}
        block4.getBody().setUserData("b4");
        if(b_5 = true){block5 = new Block(world, "level3/b1.png", 5.3f, 2.6f, 0.08f, 0.8f);}
        block5.getBody().setUserData("b5");
        if(b_6 = true){block6 = new Block(world, "level3/b1.png", 5.3f, 2.7f, 0.08f, 0.8f);}
        block6.getBody().setUserData("b6");
        if(b_7 = true){block7 = new Block(world, "level3/b2.png", 5.18f, 2.8f, 0.22f, 0.22f);}
        block7.getBody().setUserData("b7");
        if(b_8 = true){block8 = new Block(world, "level3/b2.png", 5.28f, 2.8f, 0.22f, 0.22f);}
        block8.getBody().setUserData("b8");
        if(b_9 = true){block9 = new Block(world, "level3/b2.png", 5.38f, 2.8f, 0.22f, 0.22f);}
        block9.getBody().setUserData("b9");
        if(b_10 = true){block10 = new Block(world,  "level3/b2.png", 5.36f, 2.9f, 0.22f, 0.22f);}
        block10.getBody().setUserData("b10");
        if(b_11 = true){block11 = new Block(world,  "level3/b2.png", 5.25f, 2.9f, 0.22f, 0.22f);}
        block11.getBody().setUserData("b11");
        if(b_12 = true){block12 = new Block(world,  "level3/b2.png", 5.28f, 3.0f, 0.22f, 0.22f);}
        block12.getBody().setUserData("b12");



//        block1.setRotation(0);
        block6.setRotation(90);
        block5.setRotation(90);

        // Pause button listener
        f_Button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (check.getcheck()) {
                    game.getclickSound().play();
                }
                Gameplay gameplay = (Gameplay) game.getScreen();
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

        sb = birds.get(currentBirdIndex);
        // Initialize launch impulse
        launchImpulse = new Vector2(3f, 3f);
        // Initialize the contact listener
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
                    (fixtureA.getBody().getUserData() == "bird" && fixtureB.getBody().getUserData() == "pig1") ||
                    (fixtureA.getBody().getUserData() == "pig1" && fixtureB.getBody().getUserData() == "bird")) {

                    // Determine the pig body
                    Body pigBody = (fixtureA.getBody().getUserData().toString().startsWith("pig")) ? fixtureA.getBody() : fixtureB.getBody();
                    String pigId = pigBody.getUserData().toString(); // "pig1" or "pig2"

                    synchronized (this) { // Thread-safe modification of shared state
                        touchCounters.putIfAbsent(pigBody, 0);
                        touchCounters.put(pigBody, touchCounters.get(pigBody) + 1);

                        // Check if the pig has reached the destruction threshold
                        if (touchCounters.get(pigBody) >= 3) {
                            if ("pig1".equals(pigId)) p1 = false;

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
                    (fixtureA.getBody().getUserData() == "b9" && fixtureB.getBody().getUserData() == "bird") ||
                    (fixtureA.getBody().getUserData() == "bird" && fixtureB.getBody().getUserData() == "b10") ||
                    (fixtureA.getBody().getUserData() == "b10" && fixtureB.getBody().getUserData() == "bird") ||
                    (fixtureA.getBody().getUserData() == "bird" && fixtureB.getBody().getUserData() == "b11") ||
                    (fixtureA.getBody().getUserData() == "b11" && fixtureB.getBody().getUserData() == "bird") ||
                    (fixtureA.getBody().getUserData() == "bird" && fixtureB.getBody().getUserData() == "b12") ||
                    (fixtureA.getBody().getUserData() == "b12" && fixtureB.getBody().getUserData() == "bird")
                ){
                    Body block = (fixtureA.getBody().getUserData().toString().startsWith("b")) ? fixtureA.getBody() : fixtureB.getBody();
                    String bId = block.getUserData().toString();

                    synchronized (this) { // Thread-safe modification of shared state
                        touchCounters.putIfAbsent(block, 0);
                        touchCounters.put(block, touchCounters.get(block) + 1);

                        // Check if the pig has reached the destruction threshold
                        if (touchCounters.get(block) >= 1) {
                            if ("b1".equals(bId)) b_1 = false;
                            if ("b2".equals(bId)) b_2 = false;
                            if ("b3".equals(bId)) b_3 = false;
                            if ("b4".equals(bId)) b_4 = false;
                            if ("b5".equals(bId)) b_5 = false;
                            if ("b6".equals(bId)) b_6 = false;
                            if ("b7".equals(bId)) b_7 = false;
                            if ("b8".equals(bId)) b_8 = false;
                            if ("b9".equals(bId)) b_9 = false;
                            if ("b10".equals(bId)) b_10 = false;
                            if ("b11".equals(bId)) b_11 = false;
                            if ("b12".equals(bId)) b_12 = false;


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
            }

            @Override
            public void endContact(Contact contact) {
            }

            @Override
            public void preSolve(Contact contact, Manifold oldManifold) {
            }

            @Override
            public void postSolve(Contact contact, ContactImpulse impulse) {
            }

        });
    }


    private void createGround() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(0, 0);
        bodyDef.type = BodyDef.BodyType.StaticBody;

        groundBody = world.createBody(bodyDef);
        groundBody.setUserData("grnd");

        ChainShape chainShape = new ChainShape();
        chainShape.createChain(new Vector2[] {
            //catapult tak
            new Vector2(0.0f, 10.35f),
            new Vector2(0.0f, 1.80f),
            new Vector2(0.9f, 1.80f),
            new Vector2(1.2f, 1.80f),
            new Vector2(6.8f, 1.80f),
            new Vector2(6.35f, 10.93f),

        });


        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = chainShape;
        fixtureDef.friction = 0.8f;
        groundBody.createFixture(fixtureDef);

        chainShape.dispose();
    } // unknown

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
                if(!p1){
                    game.setScreen(new win(game));
                    game.getHK1().pause();
                }
            }
        }, 5.0f);

        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        bird.draw(batch);
        b2.draw(batch);
        b3.draw(batch);

        if(p1){pig1.draw(batch);}

        if(b_1){block1.draw(batch);}
        if(b_2){block2.draw(batch);}
        if(b_3){block3.draw(batch);}
        if(b_4){block4.draw(batch);}
        if(b_5){block5.draw(batch);}
        if(b_6){block6.draw(batch);}
        if(b_7){block7.draw(batch);}
        if(b_8){block8.draw(batch);}
        if(b_9){block9.draw(batch);}
        if(b_10){block10.draw(batch);}
        if(b_11){block11.draw(batch);}
        if(b_12){block12.draw(batch);}


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
        if (bird != null)bird.dispose();
        if (b2 != null)b2.dispose();
        if (b3 != null)b3.dispose();

        if (pig1 != null)pig1.dispose();

        if(!block1.isIsd()){shapeRenderer.dispose();}
        shapeRenderer.dispose();
        pause.dispose();
        if (world != null)world.dispose();
    }
}
