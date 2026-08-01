package com.chick;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import java.io.Serializable;

public class Block implements Serializable {
    private static final long serialVersionUID = 1L;
    private Texture texture;
    private Body body;
    private float width, height;
    private boolean isd =false;

    public Block(World world, String texturePath, float x, float y, float width, float height) {
        this.texture = new Texture(texturePath);
        this.width = width;
        this.height = height;

        // Define the Box2D body
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody; // Make it a dynamic body
        bodyDef.position.set(x, y);

        this.body = world.createBody(bodyDef);
        body.setUserData("block");

        // Define the shape
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width / 2, height / 2); // Box2D expects half-sizes

//        FixtureDef fixtureDef = new FixtureDef();
//        fixtureDef.shape = shape;
//        fixtureDef.density = 1f; // Adjust density as needed

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 0.8f;     // Adjust as needed
        fixtureDef.friction = 0.5f;
        fixtureDef.restitution = 0.5f; // Adjust bounce if necessary
        body.createFixture(fixtureDef);

        body.createFixture(fixtureDef);
        shape.dispose();
    }

    public boolean isIsd() {
        return isd;
    }

    public void setIsd(boolean isd) {
        this.isd = isd;
    }

    public void draw(SpriteBatch batch) {
        Vector2 pos = body.getPosition();
        float angle = (float) Math.toDegrees(body.getAngle()); // Convert radians to degrees

        batch.draw(texture,
                pos.x - width / 2,
                pos.y - height / 2,
                width / 2,
                height / 2,
                width,
                height,
                1.0f,  // Scale X
                1.0f,  // Scale Y
                angle, // Rotation angle in degrees
                0,
                0,
                texture.getWidth(),
                texture.getHeight(),
                false,
                false
        );
    }

    public void dispose() {
        texture.dispose();
    }

    // Getter for the Box2D body
    public Body getBody() {
        return body;
    }

    // Method to set rotation to a specific angle
    public void setRotation(float degrees) {
        body.setTransform(body.getPosition(), (float) Math.toRadians(degrees));
    }

    // Method to apply dynamic rotation using torque
    public void applyTorque(float torque) {
        body.applyTorque(torque, true); // Apply torque
    }

    // Method to apply angular velocity
    public void setAngularVelocity(float angularVelocity) {
        body.setAngularVelocity(angularVelocity);
    }
}
