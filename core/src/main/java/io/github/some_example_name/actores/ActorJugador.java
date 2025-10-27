package io.github.some_example_name.actores;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class ActorJugador extends Actor {
    private Texture tJugador;
    private boolean alive;

    public ActorJugador(Texture tJugador) {
        this.tJugador = tJugador;
        setSize(tJugador.getWidth(), tJugador.getHeight());
        this.alive = true;

    }

    @Override
    public void act(float delta) {
        if (isAlive()) setX(getX() + 100 * delta);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(tJugador, getX(), getY());
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }
}
