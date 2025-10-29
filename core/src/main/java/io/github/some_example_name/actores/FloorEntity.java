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

public class FloorEntity extends Actor {

    private Texture textureObstacle;
    private World worldFloor;
    private Body bodyFloor;
    private Fixture fixtureObstacle;

    public FloorEntity(Texture textureObstacle, World worldFloor, Vector2 position) {
        this.textureObstacle = textureObstacle;
        this.worldFloor = worldFloor;



        //Vertices del triangulo



        BodyDef playerDef = new BodyDef();
        playerDef.position.set(position);
        playerDef.type = BodyDef.BodyType.StaticBody;
        bodyFloor = worldFloor.createBody(playerDef);

        PolygonShape floorShape = new PolygonShape();
        floorShape.setAsBox(100, 0.5f);

        bodyFloor.createFixture(floorShape, 1);
        floorShape.dispose();
        /// ///

        /// //////////////////////

        setSize(Constants.PIXELS_IN_METERS, Constants.PIXELS_IN_METERS);

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        setPosition(bodyFloor.getPosition().x * Constants.PIXELS_IN_METERS,
            bodyFloor.getPosition().y * Constants.PIXELS_IN_METERS);

        batch.draw(textureObstacle, getX(), getY(), getWidth(), getHeight());
    }

    public void detach(){
        bodyFloor.destroyFixture(fixtureObstacle);
        worldFloor.destroyBody(bodyFloor);
    }

}
