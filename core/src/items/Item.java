package items;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

import utils.MyGestures;

/**
 * Created by victor on 3/26/17.
 */
public abstract class Item extends Sprite {
    public String type;
    public boolean touched = false;
    public boolean touched2 = false;
    public boolean used = false;
    public Vector3 vec;

    public Item(Texture texture,String type){
        super(texture);
        setSize(100, 100);
        this.type = type;
        vec = new Vector3();

    }
    public void update(){
        move();
    }
    public abstract void effect();

    public void move(){
        if(touched){
            setX(getX() - MyGestures.diff.x);
            setY(getY() + MyGestures.diff.y);
        }
        if(touched2){
            Gdx.app.log("MOVING",""+MyGestures.diff2.x);

            setX(getX() - MyGestures.diff2.x);
            setY(getY() + MyGestures.diff2.y);
        }



    }
    public void draw(SpriteBatch batch){
        super.draw(batch);

        //batch.draw();
    }
    public void resetPosition(){
        Gdx.app.log("RESES POSITION","MYSTERY");
        setPosition(vec.x,vec.y);
    }
    public void setInitPosition(){
        vec.set(getX(),getY(),0);
    }
}
