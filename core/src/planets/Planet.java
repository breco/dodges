package planets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by victor on 5/9/17.
 */
public class Planet extends Sprite {
    public boolean touched;
    public Planet(Texture texture, int x, int y){
        super(texture);
        super.setPosition(x,y);
        touched = false;

    }
    public void update(){

    }

    public void input(Vector3 vec){
        if(getBoundingRectangle().contains(vec.x,vec.y)){
            Gdx.app.log("PLANET",""+getBoundingRectangle());
            touched = true;
        }
    }
}
