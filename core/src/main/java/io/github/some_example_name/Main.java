package io.github.some_example_name;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {

    private AssetManager manager;


    @Override
    public void create() {
        manager = new AssetManager();
        manager.load("d2.png", Texture.class);
        manager.load("r2.png", Texture.class);
        manager.load("R.png", Texture.class);
        manager.finishLoading();
        setScreen(new GameScreen(this));

    }

    public AssetManager getManager() {
        return manager;
    }
}
