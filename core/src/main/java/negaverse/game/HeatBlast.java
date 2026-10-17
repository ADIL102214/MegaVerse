package negaverse.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class HeatBlast {
    Texture stance;
    Texture runSheet;
    Animation<TextureRegion> runAnimation;
    boolean running;
    float x = 50f;
    float speed = 150f;
    float runTime = 0;

    HeatBlast() {
        stance = new Texture("Main Character/Heat Blast/stance.png");
        runSheet = new Texture("Main Character/Heat Blast/run.png");
        TextureRegion[][] runFrames = TextureRegion.split(runSheet, runSheet.getWidth()/6, runSheet.getHeight());
        runAnimation = new Animation<>(0.12f, runFrames[0]);
    }
    void run(float delta){
        running = false;
        if(Gdx.input.isKeyPressed(Input.Keys.D)){
            running = true;
            x += speed * delta;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.A)){
            running = true;
            x -= speed * delta;
        }
        if(running){
            runTime += delta;
        }else runTime = 0;
    }
    void draw(SpriteBatch spriteBatch){
        if(running){
            TextureRegion frame = runAnimation.getKeyFrame(runTime, true);
            spriteBatch.draw(frame, x, 32, 48, 58);
        }
        else spriteBatch.draw(stance, x, 32, 48, 58);
    }
}

