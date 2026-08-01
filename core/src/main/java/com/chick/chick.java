package com.chick;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;


public class chick extends Game {
    protected Music backgroundMusic;
    private Music newBackgroundMusic;

    private Music eldenring,LOU,HK1,HK2,HK3,LOU2;
    private SpriteBatch batch; // For rendering the custom cursor
    private Texture customCursorTexture; // Custom cursor texture
    private Viewport viewport; // For handling resizing
    private Sound clickSound;
    private Sound hover;
    private Boolean clickEnabled = true;
    private Boolean hoverEnabled = true;
    private Boolean backenabled = true;

    // Textures for map and tower
    private Texture selectedMap;
    private Texture selectedTower;

    // Paths to store file locations for comparison
    private String selectedMapPath;
    private String selectedTowerPath;

    // Getter for the selected map texture
    public Texture getSelectedMap() {
        return selectedMap;
    }

    // Setter for the selected map texture using file path
    public void setSelectedMap(String mapPath) {
        this.selectedMapPath = mapPath; // Store the path for later use
        this.selectedMap = new Texture(Gdx.files.internal(mapPath)); // Load the texture
    }

    // Getter for the selected tower texture
    public Texture getSelectedTower() {
        return selectedTower;
    }

    // Setter for the selected tower texture using file path
    public void setSelectedTower(String mapPath) {
        this.selectedTowerPath = mapPath; // Store the path for later use
        this.selectedTower = new Texture(Gdx.files.internal(mapPath)); // Load the texture
    }

    public String getSelectedMapPath() {
        return selectedMapPath;
    }

    public String getSelectedTowerPath() {
        return selectedTowerPath;
    }

//    public void setSelectedTower(Texture tower) {
//        selectedTower = tower;
//    }




    @Override
    public void create() {
        // Load the background music
        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("b.mp3"));
        newBackgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("b2.mp3"));
        clickSound = Gdx.audio.newSound(Gdx.files.internal("click.mp3"));
        hover = Gdx.audio.newSound(Gdx.files.internal("hover.mp3"));
        eldenring = Gdx.audio.newMusic(Gdx.files.internal("eldenring.mp3"));
        LOU = Gdx.audio.newMusic(Gdx.files.internal("LOU.mp3"));
        LOU2 = Gdx.audio.newMusic(Gdx.files.internal("LOU2.mp3"));
        HK1 = Gdx.audio.newMusic(Gdx.files.internal("HK1.mp3"));
        HK2 = Gdx.audio.newMusic(Gdx.files.internal("HK2.mp3"));
        HK3 = Gdx.audio.newMusic(Gdx.files.internal("HK3.mp3"));

        // Initialize the SpriteBatch for rendering
        batch = new SpriteBatch();

        // Load the custom cursor texture
        customCursorTexture = new Texture(Gdx.files.internal("cursor.png"));

        // Hide the default system cursor
        Gdx.graphics.setSystemCursor(Cursor.SystemCursor.None);

        // Initialize the viewport to handle resizing
        viewport = new ScreenViewport();

        // Loop the background music and start playing
        // backgroundMusic.setLooping(true);
        // backgroundMusic.setVolume(1f);  // Full volume
        // backgroundMusic.play();
        // Set the initial screen
        this.setScreen(new Video2(this)); // Example screen
    }

    @Override
    public void render() {
        // Clear the screen
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Update the viewport to match the new screen size
        viewport.apply();

        // Call the render method of the current screen
        super.render();

        // Render the custom cursor
        batch.begin();
        // Get the mouse position relative to the current viewport
        float mouseX = Gdx.input.getX() * (float) Gdx.graphics.getWidth() / viewport.getScreenWidth() - 10;
        float mouseY = (Gdx.graphics.getHeight() - Gdx.input.getY()) * (float) Gdx.graphics.getHeight() / viewport.getScreenHeight() - 30;

        // Draw the custom cursor at the corrected mouse position
        batch.draw(customCursorTexture, mouseX, mouseY);
        batch.end();
    }

    // Switch to the new background music and stop the old one
    public void switchToNewBackgroundMusic() {
        if (backgroundMusic.isPlaying()) {
            backgroundMusic.stop();  // Stop the current background music
        }
        newBackgroundMusic.setLooping(true);
        newBackgroundMusic.setVolume(0.5f);  // Set volume for the new music
        newBackgroundMusic.play();  // Start the new background music
    }



    // Getter for background music
    public Music getBackgroundMusic() {

        backgroundMusic.setLooping(true);
        backgroundMusic.setVolume(0.2f);
        return backgroundMusic;
    }


    // Getter for new background music
    public Music getNewBackgroundMusic() {
        return newBackgroundMusic;
    }
    public Music geteldenring() {

        eldenring.setLooping(true);
        eldenring.setVolume(2.0f);
        return eldenring;
    }

    public Music getLOU() {

        LOU.setLooping(true);
        LOU.setVolume(2.0f);
        return LOU;
    }
    public Music getLOU2() {

        LOU2.setLooping(true);
        LOU2.setVolume(2.0f);
        return LOU2;
    }
    public Music getHK1() {

        HK1.setLooping(true);
        HK1.setVolume(2.0f);
        return HK1;
    }
    public Music getHK3() {

        HK3.setLooping(true);
        HK3.setVolume(2.0f);
        return HK3;
    }
    public Music getHK2() {

        HK2.setLooping(true);
        HK2.setVolume(2.0f);
        return HK2;
    }
    public Sound getclickSound() {
        return clickSound;
    }
    public Sound gethover() {
        return hover;
    }
    public void setclickSound(Sound click){
        this.clickSound = click;
    }
    public void sethover() {
        this.hover = hover;
    }
    public Boolean isClickEnabled() {
        return clickEnabled;
    }
    public void setClickEnabled(boolean clickEnabled) {
        this.clickEnabled = clickEnabled;
    }

    // Check if hover sound is enabled
    public Boolean isHoverEnabled() {
        return hoverEnabled;
    }
    public void setHoverEnabled(boolean hoverEnabled) {
        this.hoverEnabled = hoverEnabled;
    }
    public Boolean isback() {
        return backenabled;
    }
    public void setback(boolean backenabled) {
        this.backenabled = backenabled;
    }


    @Override
    public void resize(int width, int height) {
        // Handle resizing and update the viewport
        viewport.update(width, height, true);
    }

    @Override
    public void dispose() {
        // Dispose of all resources when the game is closed
        if (newBackgroundMusic != null) {
            newBackgroundMusic.dispose();
        }
        if (batch != null) {
            batch.dispose();
        }
        if (customCursorTexture != null) {
            customCursorTexture.dispose();
        }
    }
}
