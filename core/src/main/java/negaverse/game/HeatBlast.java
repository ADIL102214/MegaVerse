package negaverse.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import  com.badlogic.gdx.Input.Buttons;

public class HeatBlast {
    Texture stance;
    Texture runSheet;
    Animation<TextureRegion> runAnimation;
    boolean running;
    Texture flamethrowingSheet;
    Animation<TextureRegion>flamethrowingAnimation;
    boolean flamethrowing = false;
    float flamethrowingTime = 0;
    float x = 50f;
    float y = 32f;
    float speed = 150f;
    float runTime = 0;
    float scale;
    boolean facingRight = true;
    float u = 400f;
    float g = -1200f;
    float t;
    float h;
    float groundY = 32f;
    float flyTime = 0;
    float initialHeight;
    boolean flying;
    Texture flySheet;
    Animation<TextureRegion>flyingAnimation;
    Texture punchSheet;
    Animation<TextureRegion> punchAnimation;
    boolean punching = false;
    float punchTime = 0;

    HeatBlast() {
        stance = new Texture("Main Character/Heat Blast/stance.png");
        scale = 58f / stance.getHeight();
        runSheet = new Texture("Main Character/Heat Blast/run.png");
        TextureRegion[][] runFrames = TextureRegion.split(runSheet, runSheet.getWidth()/6, runSheet.getHeight());
        runAnimation = new Animation<>(0.12f, runFrames[0]);
        flamethrowingSheet = new Texture("Main Character/Heat Blast/flamethrowing.png");
        TextureRegion[][] flamethrowingFrames = TextureRegion.split(flamethrowingSheet, flamethrowingSheet.getWidth()/5, flamethrowingSheet.getHeight());
        flamethrowingAnimation = new Animation<>(0.1f, flamethrowingFrames[0]);
        flySheet = new Texture("Main Character/Heat Blast/fly.png");
        TextureRegion[][] flyFrames = TextureRegion.split(flySheet, flySheet.getWidth()/12, flySheet.getHeight());
        flyingAnimation = new Animation<>(0.1f, flyFrames[0]);
        punchSheet = new Texture("Main Character/Heat Blast/punch.png");
        TextureRegion[][] punchFrames = TextureRegion.split(punchSheet, punchSheet.getWidth()/5, punchSheet.getHeight());
        punchAnimation = new Animation<>(0.1f, punchFrames[0]);
    }
    void run(float delta){
        running = false;
        if(Gdx.input.isKeyPressed(Input.Keys.D)){
            facingRight = true;
            running = true;
            x += speed * delta;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.A)){
            facingRight = false;
            running = true;
            x -= speed * delta;
        }
        if(running){
            runTime += delta;
        }else runTime = 0;
    }
    void flamethrow(float delta){
        flamethrowing = false;
        if(Gdx.input.isButtonPressed(Buttons.RIGHT)){
            flamethrowing = true;
        }
        if(flamethrowing){
            flamethrowingTime += delta;
        }else flamethrowingTime = 0;
    }
    void fly(float delta){
        if(Gdx.input.isKeyPressed(Input.Keys.SPACE)){
            initialHeight = y;
            t = 0;
            flying = true;
        }
        if(flying){
            t += delta;
            flyTime += delta;
            h = u*t + 0.5f*g*t*t + initialHeight;
            if(h <= groundY){
                h = groundY;
                flying = false;
            }
            y = h;
        }else {
            t = 0;
            flyTime = 0;
        }
    }
    void punch(float delta){
        punching = false;
        if(Gdx.input.isButtonPressed(Buttons.LEFT)){
            punching = true;
        }
        if(punching){
            punchTime += delta;
        }else punchTime = 0;
    }
    void draw(SpriteBatch spriteBatch){
        if(flying){
            TextureRegion flyframe = flyingAnimation.getKeyFrame(flyTime, true);
            float w = flyframe.getRegionWidth() * scale;
            float h = flyframe.getRegionHeight() * scale;
            if(facingRight) spriteBatch.draw(flyframe, x, y, w, h);
            else spriteBatch.draw(flyframe, x+48, y, -w, h);
        }else if(running){
            TextureRegion frame = runAnimation.getKeyFrame(runTime, true);
            if(facingRight) spriteBatch.draw(frame, x, y, 48, 58);
            else spriteBatch.draw(frame, x+48, y, -48, 58);
        }else if(flamethrowing){
            TextureRegion flamethrowingframe = flamethrowingAnimation.getKeyFrame(flamethrowingTime, true);
            float w = flamethrowingframe.getRegionWidth() * scale;
            float h = flamethrowingframe.getRegionHeight() * scale;
            if(facingRight) spriteBatch.draw(flamethrowingframe, x, y, w, h);
            else spriteBatch.draw(flamethrowingframe, x+48, y, -w, h);
        }else if (punching) {
            TextureRegion punchframe = punchAnimation.getKeyFrame(punchTime, true);
            float w = punchframe.getRegionWidth() * scale;
            float h = punchframe.getRegionHeight() * scale;
            if(facingRight) spriteBatch.draw(punchframe, x, y, w, h);
            else spriteBatch.draw(punchframe, x+48, y, -w, h);
        } else{
            if(facingRight) spriteBatch.draw(stance, x, y, 48, 58);
            else spriteBatch.draw(stance, x+48, y, -48, 58);
        }
    }
}
