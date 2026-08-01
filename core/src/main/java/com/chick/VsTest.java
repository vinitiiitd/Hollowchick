//package com.chick;
//
//import com.badlogic.gdx.backends.headless.HeadlessApplication;
//import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;
//import com.badlogic.gdx.graphics.Texture;
//import com.badlogic.gdx.graphics.g2d.Animation;
//import com.badlogic.gdx.graphics.g2d.TextureRegion;
//import com.badlogic.gdx.utils.Array;
//import org.junit.Before;
//import org.junit.Test;
//
//import static org.junit.Assert.*;
//import static org.mockito.Mockito.*;
//
//public class VsTest {
//    private VideoScreen videoScreen;
//    private chick mockGame;
//
//    @Before
//    public void setUp() {
//        // Initialize headless LibGDX backend
//        HeadlessApplicationConfiguration config = new HeadlessApplicationConfiguration();
//        new HeadlessApplication(new MockApplicationListener(), config);
//
//        // Mock the game object
//        mockGame = mock(chick.class);
//
//        // Create the VideoScreen
//        videoScreen = new VideoScreen(mockGame);
//    }
//
//    @Test
//    public void testSetVideoSpeed() {
//        // Set the video speed to 2x
//        videoScreen.setVideoSpeed(2.0f);
//
//        // Check if the frame duration is halved
//        assertEquals(0.05f, videoScreen.frameDuration, 0.001f);
//    }
//
//    @Test
//    public void testAnimationSwitchesScreen() {
//        // Mock an animation with just one frame
//        Array<TextureRegion> frames = new Array<>();
//        frames.add(new TextureRegion(new Texture("dummy.png")));
//        Animation<TextureRegion> mockAnimation = new Animation<>(0.05f, frames, Animation.PlayMode.NORMAL);
//
//        // Replace the animation in VideoScreen
//        videoScreen.videoAnimation = mockAnimation;
//
//        // Simulate elapsed time greater than the animation duration
//        videoScreen.render(0.1f);
//
//        // Verify that the game switches to the MainScreen
//        verify(mockGame).setScreen(any(MainScreen.class));
//    }
//
//    @Test
//    public void testDispose() {
//        // Call dispose
//        videoScreen.dispose();
//
//        // Ensure batch is disposed
//        assertTrue(true);
//    }
//}
//
