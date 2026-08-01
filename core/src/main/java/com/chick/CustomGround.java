package com.chick;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import java.util.List;

public class CustomGround {
    private Image groundImage;  // Image for the ground
    private List<Vector2> groundPoints;  // List of points for the ground shape
    private World world;  // Box2D world
    private Body groundBody;  // Static ground body
    private Box2DDebugRenderer debugRenderer;  // Box2D debug renderer
    private OrthographicCamera camera;  // Camera for rendering

    public CustomGround(World world, String imagePath, List<Vector2> groundPoints) {
        this.world = world;
        this.groundPoints = groundPoints;

        // Load ground texture
        Texture groundTexture = new Texture(Gdx.files.internal(imagePath));
        groundImage = new Image(groundTexture);
        groundImage.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight() / 4); // Customize size
        groundImage.setPosition(0, 0);  // Set the image position at the bottom of the screen

        // Create the Box2D body with custom shape
        createGroundBody();

        // Initialize the debug renderer
        debugRenderer = new Box2DDebugRenderer();
    }

    // Method to create the Box2D body with the custom ground shape
    private void createGroundBody() {
        // Define the ground body
        BodyDef groundBodyDef = new BodyDef();
        groundBodyDef.position.set(0, 0);  // Position at the bottom of the screen
        groundBodyDef.type = BodyDef.BodyType.StaticBody;  // Static body (doesn't move)

        // Create the ground body
        groundBody = world.createBody(groundBodyDef);

        // Define the shape for the ground using ChainShape
        ChainShape groundShape = new ChainShape();

        // Convert List<Vector2> to an array of floats for ChainShape
        float[] pointsArray = new float[groundPoints.size() * 2];  // Each Vector2 has x and y
        for (int i = 0; i < groundPoints.size(); i++) {
            pointsArray[i * 2] = groundPoints.get(i).x;
            pointsArray[i * 2 + 1] = groundPoints.get(i).y;
        }

        // Set the points to the chain shape
        groundShape.createChain(pointsArray);

        // Create the fixture (physical properties)
        FixtureDef groundFixtureDef = new FixtureDef();
        groundFixtureDef.shape = groundShape;
        groundFixtureDef.friction = 0.5f;  // Set friction (adjust as needed)
        groundFixtureDef.restitution = 0.2f;  // Set restitution (bounciness)

        // Attach the fixture to the body
        groundBody.createFixture(groundFixtureDef);

        // Dispose the shape after using it
        groundShape.dispose();
    }

    // Method to update and draw the ground (image) on the screen
    public void updateAndDraw() {
        // Draw the ground image on the screen (if needed)
        groundImage.draw(null, 1);

        // Debug rendering (draw Box2D shapes)
        debugRenderer.render(world, camera.combined);  // Use your camera's combined matrix
    }

    public Image getGroundImage() {
        return groundImage;
    }

    public Body getGroundBody() {
        return groundBody;
    }

    public List<Vector2> getGroundPoints() {
        return groundPoints;
    }

    // Dispose the debug renderer when done
    public void dispose() {
        debugRenderer.dispose();
    }
}
