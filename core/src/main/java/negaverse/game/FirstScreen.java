package negaverse.game;

import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class FirstScreen implements Screen {
    Texture stance;

    SpriteBatch spriteBatch;
    FitViewport viewport;

    @Override
    public void show() {
        stance = new Texture("Main Character/Heat Blast/stance.png");

        spriteBatch = new SpriteBatch();
        viewport = new FitViewport(1024,336);
    }

    @Override
    public void render(float delta) {
        draw();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }
    void draw(){
        ScreenUtils.clear(Color.WHITE);
        viewport.apply();
        spriteBatch.setProjectionMatrix(viewport.getCamera().combined);
        spriteBatch.begin();
        spriteBatch.draw(stance, 50, 32, 48, 58);
        spriteBatch.end();
    }
    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {

    }
}
