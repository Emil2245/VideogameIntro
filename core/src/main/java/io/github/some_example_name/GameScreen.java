package io.github.some_example_name;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;

import io.github.some_example_name.actores.FloorEntity;
import io.github.some_example_name.actores.ObstacleEntity;
import io.github.some_example_name.actores.PlayerEntity;

public class GameScreen extends BaseScreen {
    private Stage stage;
    private World world;
    private Texture texturePlayer;
    private Texture textureRock;
    private Texture textureFloor;
    private PlayerEntity player;
    private ObstacleEntity obstacle;
    private FloorEntity floor;

    public GameScreen(Main game) {
        super(game);
        texturePlayer = game.getManager().get("d2.png");
        textureRock = game.getManager().get("r2.png");
        textureFloor = game.getManager().get("R.png");
//        texture = new Texture("d2.png");

        stage = new Stage(new FitViewport(640, 360));
        world = new World(new Vector2(0, -10f), true);
    }

    @Override
    public void show() {
        player = new PlayerEntity(texturePlayer,world,new Vector2(1.6f,2));
        obstacle = new ObstacleEntity(textureRock,world,new Vector2(5,0));
        floor = new FloorEntity(textureFloor,world,new Vector2(0,0));
        stage.addActor(player);
        stage.addActor(obstacle);
        stage.addActor(floor);


    }

    @Override
    public void hide() {
        player.detach();
        player.remove();

        obstacle.detach();
        obstacle.remove();

        floor.detach();
        floor.remove();

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.4f, 0.5f, 0.8f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        stage.act();
        world.step(delta,6,2);
        stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
        world.dispose();
    }
}
