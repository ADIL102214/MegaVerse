package negaverse.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.Input.Buttons;

public abstract class MainCharacter {
    float x = 50f;
    float y = 32f;
    float speed;
    float scale;
    boolean facingRight = true;
    float bodyWidth = 48f;

    TextureRegion currentFrame;

    Texture stance;
    TextureRegion stanceFrame;

    Texture runSheet;
    Animation<TextureRegion> runAnimation;
    boolean running;
    float runTime = 0;

    Texture jumpSheet;
    Animation<TextureRegion> jumpAnimation;
    boolean jumping;
    float jumpTime = 0;
    float u = 400f;
    float g = -1200f;
    float t;
    float h;
    float groundY = 32f;
    float initialHeight;

    Texture leftAttackSheet;
    Animation<TextureRegion> leftAttackAnimation;
    boolean leftAttacking;
    float leftAttackTime = 0;

    Texture rightAttackSheet;
    Animation<TextureRegion> rightAttackAnimation;
    boolean rightAttacking;
    float rightAttackTime = 0;

    MainCharacter(String folder, float  speed, int runFrames, int jumpFrames,int leftAttackFrames, int rightAttackFrames){
        this.speed = speed;

        stance = new Texture(folder + "stance.png");
        stanceFrame = new TextureRegion(stance);
        currentFrame = stanceFrame;
        scale = 58f/stance.getHeight();

        runSheet = new Texture(folder + "run.png");
        TextureRegion[][] runFrameGrid = TextureRegion.split(runSheet, runSheet.getWidth()/runFrames, runSheet.getHeight());
        runAnimation = new Animation<>(0.12f, runFrameGrid[0]);

        jumpSheet = new Texture(folder + "fly.png");
        TextureRegion[][] jumpFrameGrid = TextureRegion.split(jumpSheet, jumpSheet.getWidth()/jumpFrames, jumpSheet.getHeight());
        jumpAnimation = new Animation<>(0.1f, jumpFrameGrid[0]);

        rightAttackSheet = new Texture(folder + "flamethrowing.png");
        TextureRegion[][] rightAttackFrameGrid = TextureRegion.split(rightAttackSheet, rightAttackSheet.getWidth()/5, rightAttackSheet.getHeight());
        rightAttackAnimation = new Animation<>(0.1f, rightAttackFrameGrid[0]);

        leftAttackSheet = new Texture(folder + "punch.png");
        TextureRegion[][] leftAttackFrameGrid = TextureRegion.split(leftAttackSheet, leftAttackSheet.getWidth()/5, leftAttackSheet.getHeight());
        leftAttackAnimation = new Animation<>(0.1f, leftAttackFrameGrid[0]);
    }
    void update(float delta){
        currentFrame = stanceFrame;
        run(delta);
        jump(delta);
        leftAttack(delta);
        rightAttack(delta);
        if(currentFrame.isFlipX() == facingRight){
            currentFrame.flip(true, false);
        }
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
            currentFrame = runAnimation.getKeyFrame(runTime, true);
        }else runTime = 0;
    }
    void jump(float delta){
        if(Gdx.input.isKeyPressed(Input.Keys.SPACE)){
            initialHeight = y;
            t = 0;
            jumping = true;
        }
        if(jumping){
            t += delta;
            jumpTime += delta;
            h = u*t + 0.5f*g*t*t + initialHeight;
            if(h <= groundY){
                h = groundY;
                jumping = false;
            }
            y = h;
            currentFrame = jumpAnimation.getKeyFrame(jumpTime, true);
        }else {
            t = 0;
            jumpTime = 0;
        }
    }
    void leftAttack(float delta){
        leftAttacking = false;
        if(Gdx.input.isButtonPressed(Buttons.LEFT)){
            leftAttacking = true;
        }
        if(leftAttacking){
            leftAttackTime += delta;
            currentFrame = leftAttackAnimation.getKeyFrame(leftAttackTime, false);
        }else leftAttackTime = 0;
    }
    void rightAttack(float delta){
        rightAttacking = false;
        if(Gdx.input.isButtonPressed(Buttons.RIGHT)){
            rightAttacking = true;
        }
        if(rightAttacking){
            rightAttackTime += delta;
            currentFrame = rightAttackAnimation.getKeyFrame(rightAttackTime, true);
        }else rightAttackTime = 0;
    }

    void draw(SpriteBatch spriteBatch){
        float drawWidth = currentFrame.getRegionWidth() * scale;
        float drawHeight = currentFrame.getRegionHeight() * scale;
        float drawX = x;
        if(!facingRight){
            drawX = x + bodyWidth - drawWidth;
        }
        spriteBatch.draw(currentFrame, drawX, y, drawWidth, drawHeight);
    }
    void dispose(){
        stance.dispose();
        runSheet.dispose();
        jumpSheet.dispose();
        leftAttackSheet.dispose();
        rightAttackSheet.dispose();
    }
}
