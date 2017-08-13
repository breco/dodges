package utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import enemies.Enemy;

/**
 * Created by victor on 8/12/17.
 */

public class Animator {
    Texture sheet;
    TextureRegion[] animationFrames;
    Animation animation;
    float elapsedTime;
    public Animator(Texture texture,int rows, int columns, int frames){
        sheet = texture;
        TextureRegion[][] tmpFrames = TextureRegion.split(sheet,32,32);
        animationFrames = new TextureRegion[frames];
        int index = 0;
        for(int i = 0; i < rows;i++){
            for(int j = 0; j < columns; j++) {
                animationFrames[index++] = tmpFrames[i][j];
            }
        }



        animation = new Animation(0.15f,animationFrames);
    }
    public void draw(Enemy enemy, SpriteBatch batch){
        elapsedTime += Gdx.graphics.getDeltaTime();
        enemy.setRegion(getTextureRegion());
        ((Sprite)(enemy)).draw(batch);
    }
    public TextureRegion getTextureRegion(){
        return animation.getKeyFrame(elapsedTime,true);
    }
}
