package io.github.some_example_name;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;

import java.time.format.TextStyle;

import io.github.some_example_name.actores.ActorJugador;
import io.github.some_example_name.actores.ActorRoca;

public class MainGameScreen extends BaseScreen {

    private Stage stage;
    private ActorJugador actorJugador;
    private ActorRoca actorRoca;

    private Texture tJugador;
    private Texture tRoca;

    public MainGameScreen(Main game) {
        super(game);
        tJugador = new Texture("d2.png");
        tRoca = new Texture("r2.png");
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1);

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(delta);
        verifyCollision();


        stage.draw();
    }

    @Override
    public void show() {

        stage = new Stage();
        stage.setDebugAll(true);
        actorRoca = new ActorRoca(tRoca);
        actorJugador = new ActorJugador(tJugador);

        stage.addActor(actorJugador);
        stage.addActor(actorRoca);

        actorJugador.setPosition(20, 100);
        actorRoca.setPosition(1500, 100);

    }

    @Override
    public void hide() {
        stage.dispose();
    }

    @Override
    public void dispose() {
        tJugador.dispose();
    }

    private void verifyCollision() {
//        if (
////            actorJugador.isAlive() &&
//            actorJugador.getX() + actorJugador.getWidth() > actorRoca.getX()) {
//            System.out.println("choco savaina");
////            actorJugador.setAlive(false);
//        }


        if (actorJugador.getX() + actorJugador.getWidth() > actorRoca.getX()) {
            System.out.println("choco savaina");
        }

    }

}
