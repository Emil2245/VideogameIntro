package io.github.some_example_name.actores;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class ActorJugador extends Actor {
    private Texture tJugador;
    private boolean alive;

    // --- Variables del salto ---
    private float velocityY = 0;
    private float gravity = -400; // píxeles/segundo²
    private boolean isJumping = false;
    private float groundY = 100; // altura del piso

    public ActorJugador(Texture tJugador) {
        this.tJugador = tJugador;
        setSize(tJugador.getWidth(), tJugador.getHeight());
        this.alive = true;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if (!alive) return;

        // Movimiento horizontal simple (si quieres mantenerlo)
        setX(getX() + 300 * delta);

        // --- Aplicar gravedad y movimiento vertical ---
        velocityY += gravity * delta;
        setY(getY() + velocityY * delta);

        // --- Verificar si toca el piso ---
        if (getY() <= groundY) {
            setY(groundY);
            velocityY = 0;
            isJumping = false;
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(tJugador, getX(), getY());
    }

    // --- Método para saltar ---
    public void jump() {
        if (!isJumping) {
            velocityY = 500; // impulso hacia arriba
            isJumping = true;
        }
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }
}
