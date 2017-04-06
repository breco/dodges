package items;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by victor on 3/26/17.
 */
public abstract class Item extends Sprite {
    public String type;
    public boolean isTouched = false;
    public boolean used = false;
    public Item(Texture texture,String type){
        super(texture);
        setSize(100,100);
        this.type = type;

    }
    public abstract void update();
    public abstract void effect();


    public void draw(SpriteBatch batch){
        super.draw(batch);
        //batch.draw();
    }
}
