package com.chick;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

import java.io.Serializable;

public class Bird implements Serializable {
    private static final long serialVersionUID = 1L;
    private Texture texture;
    private Body body;
    private float width;  // In meters
    private float height; // In meters


    public Bird(World world, String texturePath, float x, float y) {
        this.texture = new Texture(texturePath);
//        this.width = 0.5f; // Bird width in meters
//        this.height = 0.5f; // Bird height in meters

        // Define a Box2D body for the bird
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(x, y);

        this.body = world.createBody(bodyDef);
        body.setUserData("bird");

        // Shape and fixture
        CircleShape shape = new CircleShape();
        shape.setRadius(0.13f); // Adjust to bird size

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1f; // Adjust density for weight
        fixtureDef.restitution = 0.5f; // Bounciness

        body.createFixture(fixtureDef);
        shape.dispose();
    }

    public Body getBody() {
        return body;
    }

    public void draw(SpriteBatch batch) {
        batch.draw(texture, body.getPosition().x - 0.2f, body.getPosition().y - 0.1f, 0.3f, 0.3f);
    }
    public void setPosition(float x, float y) {
        body.setTransform(x, y, body.getAngle());
    }

    public void launch(Vector2 velocity) {
        body.applyLinearImpulse(velocity, body.getWorldCenter(), true);

        // Clamp velocity
        Vector2 currentVelocity = body.getLinearVelocity();
        float maxVelocity = 5f; // Adjust this value as needed
        if (currentVelocity.len() > maxVelocity) {
            body.setLinearVelocity(currentVelocity.nor().scl(maxVelocity));
        }
    }


    public void dispose() {
        texture.dispose();
    }
}
