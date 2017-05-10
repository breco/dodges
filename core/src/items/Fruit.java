package items;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

import pixies.Pixie;
import screens.GameScreen;

/**
 * Created by victor on 3/26/17.
 */
public class Fruit extends Item {
    private int amount;
    public Vector3 vec;
    public Fruit(Texture texture){
        super(texture,"select");
        amount = 10;
        vec = new Vector3();
    }



    @Override
    public void effect() {

        Rectangle rect = getBoundingRectangle();
        vec.set(rect.x,rect.y,0);
        GameScreen.cam.unproject(vec);
        vec.set(vec.x, -vec.y, 0);
        rect.setPosition(vec.x,vec.y);
        for(Pixie pixie: GameScreen.pixies.getPixies()){
            if(pixie.status.equals("dead")) continue;
            Rectangle inter = new Rectangle();
            Intersector.intersectRectangles(pixie.getBoundingRectangle(), rect, inter);
            if(!(inter.x == 0 && inter.y == 0 & inter.width == 0 & inter.height == 0)){
                pixie.heal(amount);

                used = true;
                break;
            }

        }

    }
}
