package com.chick;//// LevelSelect.java
//package com.chick;
//
//import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.math.Rectangle;
//
//import com.badlogic.gdx.Screen;
//import com.badlogic.gdx.audio.Sound;
//import com.badlogic.gdx.audio.Music; // Import Music for background music
//import com.badlogic.gdx.graphics.GL20;
//import com.badlogic.gdx.graphics.Texture;
//import com.badlogic.gdx.math.Vector2;
//import com.badlogic.gdx.physics.box2d.World;
//import com.badlogic.gdx.scenes.scene2d.Actor;
//import com.badlogic.gdx.scenes.scene2d.InputListener;
//import com.badlogic.gdx.scenes.scene2d.Stage;
//import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
//import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
//import com.badlogic.gdx.utils.viewport.FitViewport;
//import com.badlogic.gdx.utils.viewport.ScreenViewport;
//import com.badlogic.gdx.scenes.scene2d.InputEvent;
//import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
//import com.badlogic.gdx.scenes.scene2d.ui.Image;
//import com.badlogic.gdx.utils.viewport.Viewport;
//import com.badlogic.gdx.scenes.scene2d.actions.Actions;
//
//import java.nio.ByteBuffer;
//import java.util.ArrayList;
//
//
//public class bs implements Screen {
//    private Stage stage;
//    private chick game;
//    private Viewport viewport;
//
//    // Textures for buttons and images
//    private Texture blur;//
//    private Texture bb;
//    private Texture ch;
//    private Texture cu;
//    private Texture red;
//    private Texture speed;
//    private Texture su;
//    private Texture sw;
//    private Texture tr;
//    private Texture rec;
//    private Texture sb;
//    private Texture back;//
//    private Texture done;
//    private Texture done_g;
//
//    private tick t1;
//    private tick t2;
//    private tick t3;
//    private tick t4;
//    private tick t5;
//    private tick t6;
//    private tick t7;
//    private tick t8;
//
//
//    private Image blur_i;
//    private Image rec_i;
//    private Image sb_i;
//
//    private Sound clickSound;
//    private Sound hover;
//
//
//
//
//    private ArrayList<Bird> birds= new ArrayList<>();
//
////    private bird red_b = new bird("b/red.png", -26, 120, 120, 50);;
////    private bird bb_b  = new bird("b/bb.png", -26, 120, 120, 50);
////    private bird su_b  = new bird("b/su.png", -26, 120, 120, 50);
////    private bird cu_b  = new bird("b/cu.png", -26, 120, 120, 50);
////    private bird tr_b  = new bird("b/tr.png", -26, 120, 120, 50);
////    private bird sp_b  = new bird("b/sp.png", -26, 120, 120, 50);
////    private bird sw_b  = new bird("b/sw.png", -26, 120, 120, 50);
////    private bird ch_b  = new bird("b/ch.png", -26, 120, 120, 50);
//
//
//
//    // Declare Music variable for new BGM
//
//    public bs(chick game) {
//        this.game = game;
//        viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
//        stage = new Stage(viewport);
//
//
//        clickSound = Gdx.audio.newSound(Gdx.files.internal("click.mp3"));
//        hover = Gdx.audio.newSound(Gdx.files.internal("hover.mp3"));
//
//
//        // Load textures
//
//        blur = new Texture(Gdx.files.internal("bird_s/blur.png"));//
//        back = new Texture(Gdx.files.internal("c6.png"));//
//        bb = new Texture(Gdx.files.internal("bird_s/Bouncer.png"));
//        ch = new Texture(Gdx.files.internal("bird_s/Chick.png"));
//        cu = new Texture(Gdx.files.internal("bird_s/CUTY.png"));
//        red = new Texture(Gdx.files.internal("bird_s/RED.png"));
//        speed = new Texture(Gdx.files.internal("bird_s/Speed.png"));
//        su = new Texture(Gdx.files.internal("bird_s/Suicide.png"));
//        sw = new Texture(Gdx.files.internal("bird_s/Swinger.png"));
//        tr = new Texture(Gdx.files.internal("bird_s/Triple.png"));
//        rec = new Texture(Gdx.files.internal("bird_s/1.png"));
//        sb = new Texture(Gdx.files.internal("bird_s/2.png"));
//        done = new Texture(Gdx.files.internal("dg.png"));
//        done_g = new Texture(Gdx.files.internal("d.png"));
//
//
////        bb = new Texture(Gdx.files.internal("1.png"));
////        ch = new Texture(Gdx.files.internal("1.png"));
////        cu = new Texture(Gdx.files.internal("1.png"));
////        red = new Texture(Gdx.files.internal("1.png"));
////        speed = new Texture(Gdx.files.internal("1.png"));
////        su = new Texture(Gdx.files.internal("1.png"));
////        sw = new Texture(Gdx.files.internal("1.png"));
////        tr = new Texture(Gdx.files.internal("1.png"));
//
//
//            // Create buttons for l1, l2, and back
//        ImageButton bb_Button = new ImageButton(new TextureRegionDrawable(bb));
//        ImageButton back_button = new ImageButton(new TextureRegionDrawable(back));
//        ImageButton ch_Button = new ImageButton(new TextureRegionDrawable(ch));
//        ImageButton cu_Button = new ImageButton(new TextureRegionDrawable(cu));
//        ImageButton red_Button = new ImageButton(new TextureRegionDrawable(red));
//        ImageButton speed_Button = new ImageButton(new TextureRegionDrawable(speed));
//        ImageButton su_Button = new ImageButton(new TextureRegionDrawable(su));
//        ImageButton sw_Button = new ImageButton(new TextureRegionDrawable(sw));
//        ImageButton tr_Button = new ImageButton(new TextureRegionDrawable(tr));
//        ImageButton done_Button = new ImageButton(new TextureRegionDrawable(done));
//        ImageButton doneg_Button = new ImageButton(new TextureRegionDrawable(done_g));
//
//        done_Button.setPosition(502, 370);
//        done_Button.setSize(100, 150);
//        doneg_Button.setPosition(502, 370);
//        doneg_Button.setSize(100, 150);
//
//        // Set positions and sizes for buttons
//        bb_Button.setPosition(102, 215);
//        bb_Button.setSize(bb.getWidth()-180, bb.getHeight()-60);
//        bb_Button.setBounds(102, 215, bb.getWidth()-180, bb.getHeight()-60);
//        ch_Button.setPosition(398, 15);
//        ch_Button.setSize(ch.getWidth()-180, ch.getHeight()-60);
//        cu_Button.setPosition(398, 215);
//        cu_Button.setSize(cu.getWidth()-180, cu.getHeight()-60);
//        red_Button.setPosition(-62, 215);//
//        red_Button.setSize(red.getWidth()-180, red.getHeight()-60);//
//        red_Button.setBounds(-62, 215, red.getWidth()-180, red.getHeight()-60);
//        speed_Button.setPosition(115, 15);
//        speed_Button.setSize(speed.getWidth()-180, speed.getHeight()-60);
//        su_Button.setPosition(253, 215);
//        su_Button.setSize(su.getWidth()-180, su.getHeight()-60);
//        sw_Button.setPosition(253, 15);
//        sw_Button.setSize(sw.getWidth()-180, sw.getHeight()-60);
//        tr_Button.setPosition(-62, 15);
//        tr_Button.setSize(tr.getWidth()-180, tr.getHeight()-60);
//
//        back_button.setPosition(30, 370);
//        back_button.setSize(100, 150);
//
//        // Add blur background image
//        blur_i = new Image(blur);
//        blur_i.setSize(stage.getWidth(), stage.getHeight());
//        blur_i.setPosition(0, 0);
//        stage.addActor(blur_i);
//
//        rec_i = new Image(rec);
//        rec_i.setSize(stage.getWidth()-20, stage.getHeight()-40);
//        rec_i.setPosition(5, 20);
//        stage.addActor(rec_i);
//
//        sb_i = new Image(sb);
//        sb_i.setPosition(204, 420);
//        sb_i.setSize(240, 70);
//        stage.addActor(sb_i);
//
//        if (global.birds_selected.size()<2) {
//            doneg_Button.setVisible(false);
//        } else {
//            done_Button.setVisible(false);
//        }
//
//        // Add ImageButtons
//        stage.addActor(bb_Button);
//        stage.addActor(back_button);
//        stage.addActor(ch_Button);
//        stage.addActor(cu_Button);
//        stage.addActor(red_Button);
//        stage.addActor(speed_Button);
//        stage.addActor(su_Button);
//        stage.addActor(sw_Button);
//        stage.addActor(tr_Button);
//        stage.addActor(done_Button);
//        stage.addActor(doneg_Button);
//
//        bird_i birdI = new bird_i("bird1","bs/Component 41.png",-30, 50,stage.getWidth()-30, stage.getHeight()-60);
//        bird_i bird2 = new bird_i("bird2","bs/Bouncer (2).png",-30, 50,stage.getWidth()-30, stage.getHeight()-60);
//        bird_i bird3 = new bird_i("bird3","bs/Chick (2).png",-30, 50,stage.getWidth()-30, stage.getHeight()-60);
//        bird_i bird4 = new bird_i("bird4","bs/CUTY (2).png",-30, 50,stage.getWidth()-30, stage.getHeight()-60);
//        bird_i bird5 = new bird_i("bird5","bs/Speed (1).png",-30, 50,stage.getWidth()-30, stage.getHeight()-60);
//        bird_i bird6 = new bird_i("bird6","bs/Suicide (1).png",-30, 50,stage.getWidth()-30, stage.getHeight()-60);
//        bird_i bird7 = new bird_i("bird7","bs/Swinger (1).png",-30, 50,stage.getWidth()-30, stage.getHeight()-60);
//        bird_i bird8 = new bird_i("bird8","bs/TRIPLE (2).png",-30, 50,stage.getWidth()-30, stage.getHeight()-60);
//
//        global.m.put(birdI.getbirdId(),global.getRed());
//        global.m.put(bird2.getbirdId(),global.getBb());
//        global.m.put(bird3.getbirdId(),global.getCh());
//        global.m.put(bird4.getbirdId(),global.getCu());
//        global.m.put(bird5.getbirdId(),global.getSp());
//        global.m.put(bird6.getbirdId(),global.getSu());
//        global.m.put(bird7.getbirdId(),global.getSw());
//        global.m.put(bird8.getbirdId(),global.getTr());
//
//
//
//        t1 = new tick("tick.png",72, 245,bb.getWidth()-380, bb.getHeight()-200);
//        //t1.renderImage(stage,game);
//        t2 = new tick("tick.png",552, 245,bb.getWidth()-380, bb.getHeight()-200);
//        //t2.renderImage(stage,game);
//        t3 = new tick("tick.png",253, 245,bb.getWidth()-380, bb.getHeight()-200);
//        //t3.renderImage(stage,game);
//        t4 = new tick("tick.png",398, 245,bb.getWidth()-380, bb.getHeight()-200);
//        //t4.renderImage(stage,game);
//        t5 = new tick("tick.png",72, 15,bb.getWidth()-380, bb.getHeight()-200);
//        //t5.renderImage(stage,game);
//        t6 = new tick("tick.png",398, 15,bb.getWidth()-380, bb.getHeight()-200);
//        //t6.renderImage(stage,game);
//        t7 = new tick("tick.png",253, 15,bb.getWidth()-380, bb.getHeight()-200);
//        //t7.renderImage(stage,game);
//        t8 = new tick("tick.png",552, 15,bb.getWidth()-380, bb.getHeight()-200);
//        t8.renderImage(stage,game);
//
//        Rectangle buttonBounds = new Rectangle(bb_Button.getX(), bb_Button.getY(), bb_Button.getWidth(), bb_Button.getHeight());
//
//        done_Button.addListener(new ClickListener() {
//            @Override
//            public void clicked(InputEvent event, float x, float y) {
////                if (game.isClickEnabled()) {
////                    game.getclickSound().play();
////                }
//                if(check.getcheck()){
//                    game.getclickSound().play();
//                }// Disable click sound
//            }
//        });
//
//        doneg_Button.addListener(new ClickListener() {
//            @Override
//            public void clicked(InputEvent event, float x, float y) {
//                if(check.getcheck()){
//                    game.getclickSound().play();
//                }
//                game.setScreen( new Gameplay(game));// Enable click sound
//            }
//        });
//        // Add listeners to buttons
//        t3.renderImage(stage,game);
//        if (StateManager.getCheck(bird2.getbirdId())) {
//
//            t3.hide();
//        } else {
//
//            t3.see();
//        }
//        bb_Button.addListener(new ClickListener() {
//            @Override
//            public void clicked(InputEvent event, float x, float y) {
//                clickSound.play();
//
//                // Create a sequence of animations
//                bb_Button.addAction(Actions.sequence(
//                    Actions.scaleTo(0.9f, 0.9f, 0.1f), // Scale down
//                    Actions.scaleTo(1.1f, 1.1f, 0.1f), // Scale up slightly larger than original
//                    Actions.scaleTo(1.0f, 1.0f, 0.1f), // Return to original scale
//                    Actions.rotateBy(15f, 0.2f),       // Rotate 15 degrees
//                    Actions.rotateBy(-15f, 0.2f),      // Rotate back to original position
//                    Actions.delay(0.1f),               // Delay for a moment
//                    Actions.fadeOut(0.1f),             // Fade out
//                    Actions.fadeIn(0.1f), // Fade in back
//
//                    Actions.run(() -> {
//                        bird2.renderImage(stage, game);
//                    })
//                ));
//            }
//        });
//        t1.renderImage(stage,game);
//        if (StateManager.getCheck(birdI.getbirdId())) {
//
//            t1.hide();
//        } else {
//
//            t1.see();
//        }
//        red_Button.addListener(new ClickListener() {
//            @Override
//            public void clicked(InputEvent event, float x, float y) {
//                clickSound.play();
//
//                // Create a sequence of animations
//                red_Button.addAction(Actions.sequence(
//                    Actions.scaleTo(0.9f, 0.9f, 0.1f), // Scale down
//                    Actions.scaleTo(1.1f, 1.1f, 0.1f), // Scale up slightly larger than original
//                    Actions.scaleTo(1.0f, 1.0f, 0.1f), // Return to original scale
//                    Actions.rotateBy(15f, 0.2f),       // Rotate 15 degrees
//                    Actions.rotateBy(-15f, 0.2f),      // Rotate back to original position
//                    Actions.delay(0.1f),               // Delay for a moment
//                    Actions.fadeOut(0.1f),             // Fade out
//                    Actions.fadeIn(0.1f), // Fade in back
//
//                    Actions.run(() -> {
//                        birdI.renderImage(stage, game);
//                    })
//
//                ));
//            }
//        });
//
//        //t8.renderImage(stage,game);
//        if (StateManager.getCheck(bird3.getbirdId())) {
//            t8.hide();
//        } else {
//            t8.see();
//        }
//
//        ch_Button.addListener(new ClickListener() {
//            @Override
//            public void clicked(InputEvent event, float x, float y) {
//                clickSound.play();
//
//                // Create a sequence of animations
//                ch_Button.addAction(Actions.sequence(
//                    Actions.scaleTo(0.9f, 0.9f, 0.1f), // Scale down
//                    Actions.scaleTo(1.1f, 1.1f, 0.1f), // Scale up slightly larger than original
//                    Actions.scaleTo(1.0f, 1.0f, 0.1f), // Return to original scale
//                    Actions.rotateBy(15f, 0.2f),       // Rotate 15 degrees
//                    Actions.rotateBy(-15f, 0.2f),      // Rotate back to original position
//                    Actions.delay(0.1f),               // Delay for a moment
//                    Actions.fadeOut(0.1f),             // Fade out
//                    Actions.fadeIn(0.1f), // Fade in back
//
//                    Actions.run(() -> {
//                        bird3.renderImage(stage, game);
//                    })
//                ));
//            }
//        });
//        t2.renderImage(stage,game);
//        if (StateManager.getCheck(bird4.getbirdId())) {
//
//            t2.hide();
//        } else {
//
//            t2.see();
//        }
//        cu_Button.addListener(new ClickListener() {
//            @Override
//            public void clicked(InputEvent event, float x, float y) {
//                clickSound.play();
//
//                // Create a sequence of animations
//                cu_Button.addAction(Actions.sequence(
//                    Actions.scaleTo(0.9f, 0.9f, 0.1f), // Scale down
//                    Actions.scaleTo(1.1f, 1.1f, 0.1f), // Scale up slightly larger than original
//                    Actions.scaleTo(1.0f, 1.0f, 0.1f), // Return to original scale
//                    Actions.rotateBy(15f, 0.2f),       // Rotate 15 degrees
//                    Actions.rotateBy(-15f, 0.2f),      // Rotate back to original position
//                    Actions.delay(0.1f),               // Delay for a moment
//                    Actions.fadeOut(0.1f),             // Fade out
//                    Actions.fadeIn(0.1f), // Fade in back
//
//                    Actions.run(() -> {
//                        bird4.renderImage(stage, game);
//                    })
//                ));
//
//                // Call your bird image rendering
//                //birdI.renderImage(stage, game);
//            }
//        });
//        t7.renderImage(stage,game);
//        if (StateManager.getCheck(bird5.getbirdId())) {
//
//            t7.hide();
//        } else {
//
//            t7.see();
//        }
//        speed_Button.addListener(new ClickListener() {
//            @Override
//            public void clicked(InputEvent event, float x, float y) {
//                clickSound.play();
//
//                // Create a sequence of animations
//                speed_Button.addAction(Actions.sequence(
//                    Actions.scaleTo(0.9f, 0.9f, 0.1f), // Scale down
//                    Actions.scaleTo(1.1f, 1.1f, 0.1f), // Scale up slightly larger than original
//                    Actions.scaleTo(1.0f, 1.0f, 0.1f), // Return to original scale
//                    Actions.rotateBy(15f, 0.2f),       // Rotate 15 degrees
//                    Actions.rotateBy(-15f, 0.2f),      // Rotate back to original position
//                    Actions.delay(0.1f),               // Delay for a moment
//                    Actions.fadeOut(0.1f),             // Fade out
//                    Actions.fadeIn(0.1f), // Fade in back
//
//                    Actions.run(() -> {
//                        bird5.renderImage(stage, game);
//                    })
//                ));
//
//                // Call your bird image rendering
//                //birdI.renderImage(stage, game);
//            }
//        });
//        t4.renderImage(stage,game);
//        if (StateManager.getCheck(bird6.getbirdId())) {
//
//            t4.hide();
//        } else {
//
//            t4.see();
//        }
//        su_Button.addListener(new ClickListener() {
//            @Override
//            public void clicked(InputEvent event, float x, float y) {
//                clickSound.play();
//
//                // Create a sequence of animations
//                su_Button.addAction(Actions.sequence(
//                    Actions.scaleTo(0.9f, 0.9f, 0.1f), // Scale down
//                    Actions.scaleTo(1.1f, 1.1f, 0.1f), // Scale up slightly larger than original
//                    Actions.scaleTo(1.0f, 1.0f, 0.1f), // Return to original scale
//                    Actions.rotateBy(15f, 0.2f),       // Rotate 15 degrees
//                    Actions.rotateBy(-15f, 0.2f),      // Rotate back to original position
//                    Actions.delay(0.1f),               // Delay for a moment
//                    Actions.fadeOut(0.1f),             // Fade out
//                    Actions.fadeIn(0.1f), // Fade in back
//
//                    Actions.run(() -> {
//                        bird6.renderImage(stage, game);
//                    })
//                ));
//
//                // Call your bird image rendering
//                //birdI.renderImage(stage, game);
//            }
//        });
//        t6.renderImage(stage,game);
//        if (StateManager.getCheck(bird7.getbirdId())) {
//
//            t6.hide();
//        } else {
//
//            t6.see();
//        }
//        sw_Button.addListener(new ClickListener() {
//            @Override
//            public void clicked(InputEvent event, float x, float y) {
//                clickSound.play();
//
//                // Create a sequence of animations
//                sw_Button.addAction(Actions.sequence(
//                    Actions.scaleTo(0.9f, 0.9f, 0.1f), // Scale down
//                    Actions.scaleTo(1.1f, 1.1f, 0.1f), // Scale up slightly larger than original
//                    Actions.scaleTo(1.0f, 1.0f, 0.1f), // Return to original scale
//                    Actions.rotateBy(15f, 0.2f),       // Rotate 15 degrees
//                    Actions.rotateBy(-15f, 0.2f),      // Rotate back to original position
//                    Actions.delay(0.1f),               // Delay for a moment
//                    Actions.fadeOut(0.1f),             // Fade out
//                    Actions.fadeIn(0.1f), // Fade in back
//
//                    Actions.run(() -> {
//                        bird7.renderImage(stage, game);
//                    })
//                ));
//
//                // Call your bird image rendering
//                //birdI.renderImage(stage, game);
//            }
//        });
//        t5.renderImage(stage,game);
//        if (StateManager.getCheck(bird8.getbirdId())) {
//
//            t5.hide();
//        } else {
//
//            t5.see();
//        }
//        tr_Button.addListener(new ClickListener() {
//            @Override
//            public void clicked(InputEvent event, float x, float y) {
//                clickSound.play();
//
//                // Create a sequence of animations
//                tr_Button.addAction(Actions.sequence(
//                    Actions.scaleTo(0.9f, 0.9f, 0.1f), // Scale down
//                    Actions.scaleTo(1.1f, 1.1f, 0.1f), // Scale up slightly larger than original
//                    Actions.scaleTo(1.0f, 1.0f, 0.1f), // Return to original scale
//                    Actions.rotateBy(15f, 0.2f),       // Rotate 15 degrees
//                    Actions.rotateBy(-15f, 0.2f),      // Rotate back to original position
//                    Actions.delay(0.1f),               // Delay for a moment
//                    Actions.fadeOut(0.1f),             // Fade out
//                    Actions.fadeIn(0.1f), // Fade in back
//
//                    Actions.run(() -> {
//                        bird8.renderImage(stage, game);
//                    })
//                ));
//
//                // Call your bird image rendering
//                //birdI.renderImage(stage, game);
//            }
//        });
//
//        back_button.addListener(new ClickListener() {
//            @Override
//            public void clicked(InputEvent event, float x, float y) {
//                clickSound.play();
//                game.setScreen(new Loading(game, new MapSelect(game) )); // Switch to MapSelect screen
//            }
//        });
//
//        // Set the stage as the input processor
//        Gdx.input.setInputProcessor(stage);
//        addHoverEffect1(bb_Button,bb.getWidth()-180, bb.getHeight()-60);
//        addHoverEffect1(ch_Button,ch.getWidth()-180, ch.getHeight()-60);
//        addHoverEffect1(cu_Button,cu.getWidth()-180,cu.getHeight()-60);
//        addHoverEffect1(red_Button,red.getWidth()-180, red.getHeight()-60);
//        addHoverEffect1(speed_Button,speed.getWidth()-180, speed.getHeight()-60);
//        addHoverEffect1(su_Button,su.getWidth()-180, su.getHeight()-60);
//        addHoverEffect1(sw_Button,sw.getWidth()-180, sw.getHeight()-60);
//        addHoverEffect1(tr_Button,tr.getWidth()-180, tr.getHeight()-60);
//
//        addHoverEffect1(back_button,100, 150);
//        addHoverEffect1(done_Button,100, 150);
//        addHoverEffect1(doneg_Button,100, 150);
//
//    }
//
//    private void addHoverEffect1(final ImageButton button, final float originalWidth, final float originalHeight) {
//        button.addListener(new InputListener() {
//
//            @Override
//            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
//                hover.play();
//                // Enlarge the button on hover
//                button.setSize(originalWidth + 20, originalHeight + 20);  // Increase size
//                button.setPosition(button.getX() - 10, button.getY() - 10); // Adjust position to center
//            }
//
//            @Override
//            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
//                // Revert to original size
//                button.setSize(originalWidth, originalHeight);  // Reset size
//                button.setPosition(button.getX() + 10, button.getY() + 10); // Reset position
//            }
//        });
//    }
//
//    @Override
//    public void show() {
//    }
//
//    @Override
//    public void render(float delta) {
//        // Clear the screen with a white background
//        Gdx.gl.glClearColor(1, 1, 1, 1);
//        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//
//        // Update and draw the stage
//        stage.act(delta);
//        stage.draw();
//    }
//
//    @Override
//    public void resize(int width, int height) {
//        viewport.update(width, height, true);
//    }
//
//    @Override
//    public void pause() { }
//
//    @Override
//    public void resume() { }
//
//    @Override
//    public void hide() {
//        // Dispose of the new background music when hiding
////        if (newBackgroundMusic != null) {
////            newBackgroundMusic.stop();
////            newBackgroundMusic.dispose();
////        }
//    }
//
//    @Override
//    public void dispose() {
//        blur.dispose();
//        back.dispose();
//        bb.dispose();
//        ch.dispose();
//        cu.dispose();
//        red.dispose();
//        speed.dispose();
//        su.dispose();
//        sw.dispose();
//        tr.dispose();
//        rec.dispose();
//        sb.dispose();
//        clickSound.dispose();
//        hover.dispose();
//        // Don't dispose of newBackgroundMusic here; do it in hide()
//    }
//}
