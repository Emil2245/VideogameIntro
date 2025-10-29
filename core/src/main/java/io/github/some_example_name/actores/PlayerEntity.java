package io.github.some_example_name.actores;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;

import io.github.some_example_name.Constants;

public class PlayerEntity extends Actor {

    private Texture texturePlayer;
    private World worldPlayer;
    private Body bodyPlayer;
    private Fixture fixturePlayer;

    public PlayerEntity(Texture texturePlayer, World worldPlayer, Vector2 position) {
        this.texturePlayer = texturePlayer;
        this.worldPlayer = worldPlayer;


        BodyDef playerDef = new BodyDef();
        playerDef.position.set(position);
        playerDef.type = BodyDef.BodyType.DynamicBody;
        bodyPlayer = worldPlayer.createBody(playerDef);

        PolygonShape hoomanShape = new PolygonShape();
        hoomanShape.setAsBox(1, 1);

        bodyPlayer.createFixture(hoomanShape, 1);
        hoomanShape.dispose();
        /// //////////////////////

        setSize(Constants.PIXELS_IN_METERS, Constants.PIXELS_IN_METERS);

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        setPosition(bodyPlayer.getPosition().x * Constants.PIXELS_IN_METERS,
            bodyPlayer.getPosition().y * Constants.PIXELS_IN_METERS);

        batch.draw(texturePlayer, getX(), getY(), getWidth(), getHeight());
    }

    public void detach(){
        bodyPlayer.destroyFixture(fixturePlayer);
        worldPlayer.destroyBody(bodyPlayer);
    }

}
