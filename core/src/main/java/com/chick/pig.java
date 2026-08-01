package com.chick;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

import java.io.Serializable;

public class pig implements Serializable {
    private static final long serialVersionUID = 1L;
    private Texture texture;
    private Body body;
    private float width;  // In meters
    private float height; // In meters
    private boolean isDestroyed = false;
    private boolean isd = false;
    private int hitCount;
    private int maxHit;


    public int getMaxhit() {
        return maxHit;
    }
    public void hit() {
        hitCount++;
    }


    public void setMaxhit(int maxhit) {
        this.maxHit = maxhit;
    }

    public void setIsd(boolean isd) {
        this.isd = isd;
    }
    public boolean isIsd() {
        return isd;
    }
    public boolean isDestroyed() {
        return hitCount >= maxHit;
    }

    public void destroy() {
        isDestroyed = true;
        body.getWorld().destroyBody(body);
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public pig(World world, String texturePath, float x, float y) {
        this.texture = new Texture(texturePath);
//        this.width = 0.5f; // Bird width in meters
//        this.height = 0.5f; // Bird height in meters

        // Define a Box2D body for the bird
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(x, y);

        this.body = world.createBody(bodyDef);
        body.setUserData("pig");

        // Shape and fixture
        CircleShape shape = new CircleShape();
        shape.setRadius(0.12f); // Adjust to bird size
        shape.setPosition(new Vector2(-0.03f, 0.0175f));

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.friction = 2f;
        fixtureDef.shape = shape;
        fixtureDef.density = 1f; // Adjust density for weight
        fixtureDef.restitution = 0f; // Bounciness

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

    public Vector2 getPosition() {
        return body.getPosition();
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
