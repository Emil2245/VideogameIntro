package io.github.some_example_name;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.InputProcessor;

import io.github.some_example_name.actores.ActorJugador;
import io.github.some_example_name.actores.ActorRoca;

public class MainGameScreen extends BaseScreen {

    private Stage stage;
    private ActorJugador actorJugador;
    private ActorRoca actorRoca;

    private Texture tJugador;
    private Texture tRoca;
    private BitmapFont font;
    private SpriteBatch batch;
    private boolean showGameOver;
    private boolean gameOverShown;

    public MainGameScreen(Main game) {
        super(game);
        tJugador = new Texture("d2.png");
        tRoca = new Texture("r2.png");
        // Crear fuente por defecto para los textos
        font = new BitmapFont();
        batch = new SpriteBatch();
        showGameOver = false;
        gameOverShown = false;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1);

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(delta);
        verifyCollision();

        stage.draw();

        // Dibujar Game Over si es necesario
        if (showGameOver) {
            drawGameOver();
        }
    }

    @Override
    public void show() {

        stage = new Stage();
        stage.setDebugAll(true);
        actorRoca = new ActorRoca(tRoca);
        actorJugador = new ActorJugador(tJugador);

        stage.addActor(actorJugador);
        stage.addActor(actorRoca);

        actorJugador.setPosition(100, 100);
        actorRoca.setPosition(1020, 100);

    }

    @Override
    public void hide() {
        stage.dispose();
    }

    @Override
    public void dispose() {
        tJugador.dispose();
        tRoca.dispose();
        font.dispose();
        batch.dispose();
    }

    private void verifyCollision() {
        if (!actorJugador.isAlive()) {
            Gdx.app.log("COLISION", "Jugador ya está muerto, no verificando colisiones");
            return;
        }

        float jugadorLeft = actorJugador.getX();
        float jugadorRight = actorJugador.getX() + actorJugador.getWidth();
        float jugadorTop = actorJugador.getY() + actorJugador.getHeight();
        float jugadorBottom = actorJugador.getY();

        float rocaLeft = actorRoca.getX();
        float rocaRight = actorRoca.getX() + actorRoca.getWidth();
        float rocaTop = actorRoca.getY() + actorRoca.getHeight();
        float rocaBottom = actorRoca.getY();

        // Verificar si hay intersección en ambos ejes (X e Y)
        boolean colisionX = jugadorRight > rocaLeft && jugadorLeft < rocaRight;
        boolean colisionY = jugadorTop > rocaBottom && jugadorBottom < rocaTop;

        // Solo hay colisión si ambos ejes se intersectan
        if (colisionX && colisionY) {
            Gdx.app.log("COLISION", "¡¡¡ COLISIÓN DETECTADA !!!");
            actorJugador.setAlive(false);

            // Mostrar Game Over
            if (!gameOverShown) {
                showGameOver = true;
                gameOverShown = true;
                Gdx.app.log("GAME_OVER", "Game Over activado");
            }
        }
    }

    private void drawGameOver() {
        batch.begin();
        font.setColor(Color.WHITE);
        font.getData().setScale(6f);

        String gameOverText = "GAME OVER";
        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();

        // Para un cálculo aproximado de ancho
        GlyphLayout layout = new GlyphLayout(font, gameOverText);

        font.draw(batch, gameOverText,
            (screenWidth - layout.width) / 2,
            screenHeight / 2 + layout.height / 2);

        batch.end();
    }

}
