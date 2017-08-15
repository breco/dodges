package utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by victor on 8/12/17.
 */

public class Animator {
    public Texture sheet;
    TextureRegion[] animationFrames;
    Animation animation;
    float elapsedTime;
    public Animator(Texture texture,int rows, int columns, int frames,float speed){
        elapsedTime = 0f;
        sheet = texture;
        TextureRegion[][] tmpFrames = TextureRegion.split(sheet,32,32);
        animationFrames = new TextureRegion[frames];
        int index = 0;
        for(int i = 0; i < rows;i++){
            for(int j = 0; j < columns; j++) {
                animationFrames[index++] = tmpFrames[i][j];
            }
        }



        animation = new Animation(speed,animationFrames);
    }
    public void draw(Sprite sprite, SpriteBatch batch){
        elapsedTime += Gdx.graphics.getDeltaTime();
        sprite.setRegion(animation.getKeyFrame(elapsedTime,true));
        sprite.draw(batch);
    }
    public TextureRegion getTextureRegion(){
        return animation.getKeyFrame(elapsedTime,true);
    }
}
