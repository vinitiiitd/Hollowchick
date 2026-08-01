package com.chick;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class gametest {
        private Gameplay gameplay;
        private World testWorld;

        @Before
        public void setUp() {
            // Initialize the World instance
            testWorld = new World(new Vector2(0, -9.8f), true);

            // Initialize Gameplay with test dependencies
            gameplay = new Gameplay(null);
            gameplay.world = testWorld;
        }

        @Test
        public void testGroundCreation() {
            // Test that the ground is created properly
            gameplay.createGround();

            // Assert the world has bodies
            assertTrue("The world should contain at least one body.", testWorld.getBodyCount() > 0);
        }

        @Test
        public void testBirdSwitching() {
            gameplay.handleBirdTouchGround();

            // Ensure the bird index increases and updates correctly
            assertEquals(1, gameplay.currentBirdIndex);
            assertNotNull(gameplay.sb);
        }

        @Test
        public void testContactListenerSetup() {
            // Add a body to the world and test interaction logic
            BodyDef bodyDef = new BodyDef();
            bodyDef.type = BodyDef.BodyType.DynamicBody;
            bodyDef.position.set(1, 1);

            Body testBody = testWorld.createBody(bodyDef);
            testBody.setUserData("bird");

            assertNotNull("Body should be added to the world.", testBody);
        }
    }


