package negaverse.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class HeatBlast {
    Texture stance;
    HeatBlast() {
        stance = new Texture("Main Character/Heat Blast/stance.png");
    }
    void draw(SpriteBatch spriteBatch){
        spriteBatch.draw(stance, 50, 32, 48, 58);
    }
}

