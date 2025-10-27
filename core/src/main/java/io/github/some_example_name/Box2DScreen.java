package io.github.some_example_name;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class Box2DScreen extends BaseScreen {
    private World world;
    private Box2DDebugRenderer renderer;
    private OrthographicCamera camera;
    private Body hooman;
    private Body floor;
    private Body rock;
    private Fixture hoomanFixture;
    private Fixture floorFixture;
    private Fixture rockFixture;


    public Box2DScreen(Main game) {
        super(game);
    }

    @Override
    public void show() {


        world = new World(new Vector2(0, -10f), true);
//        world = new World(new Vector2((float) 0, (float) (-10+ Math.sin(Math.random())*2)), true);
        renderer = new Box2DDebugRenderer();
        //aspect ratio
        //(1920*1080)=1.7777
        //1.77*50=88

        camera = new OrthographicCamera(88,50 );

        BodyDef playerDef = createDef();
        hooman = world.createBody(playerDef);

        PolygonShape hoomanShape = new PolygonShape();
        hoomanShape.setAsBox(1, 1);

        hooman.createFixture(hoomanShape, 1);
        hoomanShape.dispose();
        /// //////////////////////

        BodyDef floorDef = new BodyDef();
        floorDef.position.set(0, -10);
        floorDef.type = BodyDef.BodyType.StaticBody;

        floor = world.createBody(floorDef);

        PolygonShape floorShape = new PolygonShape();
        floorShape.setAsBox(100, 1);

        floor.createFixture(floorShape, 1);
        floorShape.dispose();

        /// ////////////////

        //Vertices del triangulo
        Vector2[] vertices = new Vector2[3];
        vertices[0] = new Vector2(-0.5f, -0.5f);
        vertices[1] = new Vector2(0.5f, -0.5f);
        vertices[2] = new Vector2(0f, 0.5f);

        rock = world.createBody(rockDef(-15f));

        PolygonShape rockShape = new PolygonShape();
        rockShape.set(vertices);

        rock.createFixture(rockShape, 1);
        rockShape.dispose();


    }

    @Override
    public void render(float delta) {
//        super.render(delta);
//        Gdx.gl.glClearColor(1, 0.5f, 0, 1);

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (Gdx.input.isTouched()){
            saltar();
        }

        hooman.setLinearVelocity(2,hooman.getLinearVelocity().y);


        world.step(delta, 6, 2);
        camera.update();

        renderer.render(world, camera.combined);
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }

    @Override
    public void dispose() {
        world.destroyBody(hooman);
        world.destroyBody(rock);
        world.destroyBody(floor);
        world.dispose();
        renderer.dispose();

    }

    private BodyDef createDef() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(-20, -9);
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        return bodyDef;
    }

    private BodyDef rockDef(float x) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(x, -9);
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        return bodyDef;
    }

    private void saltar() {
        Vector2 position = hooman.getPosition();
        hooman.applyLinearImpulse(0, 7, position.x, position.y, true);
    }

}
