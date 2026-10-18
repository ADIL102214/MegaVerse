package negaverse.game;

import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class FirstScreen implements Screen {
    HeatBlast heatBlast;
    SpriteBatch spriteBatch;
    ExtendViewport viewport;

    @Override
    public void show() {
        heatBlast = new HeatBlast();
        spriteBatch = new SpriteBatch();
        viewport = new ExtendViewport(1024,336);
    }

    @Override
    public void render(float delta) {
        heatBlast.run(delta);
        heatBlast.flamethrow(delta);
        heatBlast.fly(delta);
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
        heatBlast.draw(spriteBatch);
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
