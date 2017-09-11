package items;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

import pixies.Pixie;
import screens.GameScreen;
import utils.TimeManager;

/**
 * Created by victor on 4/21/17.
 */
public class SpeedStar extends Item {
    private float amount;
    private TimeManager time;
    private int EFFECT_DURATION = 30;
    private boolean finished = false;
    private Pixie affected;
    public Vector3 vec;
    public SpeedStar(Texture texture) {
        super(texture, "select");
        amount = 0.25f;
        time = new TimeManager();
        vec = new Vector3();

    }
    public void effect(){
        Rectangle rect = getBoundingRectangle();
        vec.set(rect.x,rect.y,0);
        GameScreen.cam.unproject(vec);
        vec.set(vec.x, -vec.y, 0);
        rect.setPosition(vec.x,vec.y);
        for(Pixie pixie: GameScreen.pixies.getPixies()){

            Rectangle inter = new Rectangle();
            Intersector.intersectRectangles(pixie.getBoundingRectangle(), rect, inter);
            if(!(inter.x == 0 && inter.y == 0 & inter.width == 0 & inter.height == 0)){
                pixie.changeSPD(amount);
                affected = pixie;
                used = true;
                time.start();
                break;
            }

        }
    }
    public void update(){
        move();
        finishEffect();
    }
    public void finishEffect(){
        if(finished) return;
        if((int)time.getTime() == EFFECT_DURATION){
            finished = true;
            affected.changeSPD(-amount);

        }
    }
}
