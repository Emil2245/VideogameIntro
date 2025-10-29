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

public class ObstacleEntity extends Actor {

    private Texture textureObstacle;
    private World worldObstacle;
    private Body bodyPlayer;
    private Fixture fixtureObstacle;

    public ObstacleEntity(Texture textureObstacle, World worldObstacle, Vector2 position) {
        this.textureObstacle = textureObstacle;
        this.worldObstacle = worldObstacle;


        //Vertices del triangulo
        Vector2[] vertices = new Vector2[3];
        vertices[0] = new Vector2(-0.5f, -0.5f);
        vertices[1] = new Vector2(0.5f, -0.5f);
        vertices[2] = new Vector2(0f, 0.5f);



        bodyPlayer = worldObstacle.createBody(rockDef(-15f, position));

        PolygonShape hoomanShape = new PolygonShape();
        hoomanShape.set(vertices);

        bodyPlayer.createFixture(hoomanShape, 1);
        hoomanShape.dispose();
        /// //////////////////////

        setSize(Constants.PIXELS_IN_METERS, Constants.PIXELS_IN_METERS);

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        setPosition(bodyPlayer.getPosition().x * Constants.PIXELS_IN_METERS,
            bodyPlayer.getPosition().y * Constants.PIXELS_IN_METERS);

        batch.draw(textureObstacle, getX(), getY(), getWidth(), getHeight());
    }

    public void detach(){
        bodyPlayer.destroyFixture(fixtureObstacle);
        worldObstacle.destroyBody(bodyPlayer);
    }

    private BodyDef rockDef(float x, Vector2 position) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(position);
//        bodyDef.type = BodyDef.BodyType.DynamicBody;
        return bodyDef;
    }
}
