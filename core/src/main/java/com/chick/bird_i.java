package com.chick;//package com.chick;
//
//import com.badlogic.gdx.Game;
//import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.audio.Sound;
//import com.badlogic.gdx.graphics.Texture;
//import com.badlogic.gdx.graphics.g2d.SpriteBatch;
//import com.badlogic.gdx.scenes.scene2d.InputEvent;
//import com.badlogic.gdx.scenes.scene2d.Stage;
//import com.badlogic.gdx.scenes.scene2d.ui.Image;
//import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
//import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
//import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
//import com.badlogic.gdx.graphics.g2d.TextureRegion;
//
//public class bird_i {
//
//    private String birdId;
//    private Texture texture;
//    private float x, y, width, height;
//    private Image blur_i;
//    private Texture back;
//    private Texture sel;
//    private Texture dsel;
//    private Sound clickSound;
//    private boolean c1 = true;
//
//    public bird_i(String birdId, String imagePath, float x, float y, float width, float height) {
//        this.birdId = birdId; // Unique ID for each bird
//        this.texture = new Texture(Gdx.files.internal(imagePath));
//        this.x = x;
//        this.y = y;
//        this.width = width;
//        this.height = height;
//
//        // Load sounds
//        clickSound = Gdx.audio.newSound(Gdx.files.internal("click.mp3"));
//
//        // Load textures for buttons
//        back = new Texture(Gdx.files.internal("bs/BACK.png"));
//        sel = new Texture(Gdx.files.internal("bs/SELECT.png"));
//        dsel = new Texture(Gdx.files.internal("SELECT.png"));
//    }
//
//    public String getbirdId() {
//        return birdId;
//    }
//
//    public boolean isC1() {
//        return c1;
//    }
//
//    public void renderImage(Stage stage, chick game) {
//        blur_i = new Image(texture);
//        blur_i.setSize(width, height);
//        blur_i.setPosition(x, y);
//        stage.addActor(blur_i);
//
//        // Create buttons
//        ImageButton backButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(back)));
//        ImageButton selButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(sel)));
//        ImageButton dselButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(dsel)));
//
//        // Position and size
//        backButton.setPosition(298, 99);
//        backButton.setSize(80, 100);
//        selButton.setPosition(408, 105);
//        selButton.setSize(100, 90);
//        dselButton.setPosition(408, 105);
//        dselButton.setSize(100, 90);
//
//        // Set initial visibility based on state
//        if (StateManager.getCheck(birdId)) {
//            dselButton.setVisible(false);
//        } else {
//            selButton.setVisible(false);
//        }
//
//        // Add listeners
//        backButton.addListener(new ClickListener() {
//            @Override
//            public void clicked(InputEvent event, float x, float y) {
//                clickSound.play();
//                game.setScreen(new bs(game));
//            }
//        });
//
//        selButton.addListener(new ClickListener() {
//            @Override
//            public void clicked(InputEvent event, float x, float y) {
//                clickSound.play();
//                StateManager.setCheck(birdId, false); // Update state in shared manager
//                selButton.setVisible(false);
//                dselButton.setVisible(true);
//                global.birds_selected.add(global.m.get(getbirdId()));
//                c1 = true;
//            }
//        });
//
//        dselButton.addListener(new ClickListener() {
//            @Override
//            public void clicked(InputEvent event, float x, float y) {
//                clickSound.play();
//                StateManager.setCheck(birdId, true); // Update state in shared manager
//                dselButton.setVisible(false);
//                selButton.setVisible(true);
//                global.birds_selected.remove(global.m.get(getbirdId()));
//                c1 = false;
//            }
//        });
//
//        // Add buttons to stage
//        stage.addActor(backButton);
//        stage.addActor(selButton);
//        stage.addActor(dselButton);
//    }
//
//    public void dispose() {
//        texture.dispose();
//        back.dispose();
//        sel.dispose();
//        clickSound.dispose();
//    }
//}
