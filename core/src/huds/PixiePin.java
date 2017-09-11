package huds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import pixies.Pixie;
import utils.Animator;

/**
 * Created by victor on 9/11/17.
 */

public class PixiePin extends Sprite {
    Pixie owner;
    Animator animator;
    public PixiePin(Pixie owner, String color){
        setSize(40,51);
        this.owner = owner;
        int[] size = {40,51};
        animator = new Animator(new Texture(Gdx.files.internal("huds/"+color+"_pin.png")),1,4,4,0.2f,size);
    }
    public void draw(SpriteBatch batch){
        setX(owner.getX()+owner.getWidth()/3f);
        setY(owner.getY() - owner.getHeight() * 3f);
        animator.draw(this,batch);
        //batch.draw(pin,owner.getX()+owner.getWidth()/2,owner.getY() - owner.getHeight() * 3f);
    }
}
