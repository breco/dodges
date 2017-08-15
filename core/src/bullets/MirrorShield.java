package bullets;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

import pixies.Pixie;
import screens.GameScreen;
import utils.TimeManager;

/**
 * Created by victor on 4/21/17.
 */
public class MirrorShield extends PixieBullet {
    private Pixie owner;
    private TimeManager time;
    private int finishTime = 10;
    private Rectangle rect;
    private Bullet bullet;
    public MirrorShield(Texture texture, Pixie owner) {
        super(owner,texture, 1, 1, ' ', ' ', 0, 0);
        scale(2);
        this.owner = owner;
        time = new TimeManager();
        time.start();
        setX(owner.getX() + owner.getWidth()/12);
        setY(owner.getY() + owner.getHeight()*2);
    }
    public void move(){
        setX(owner.getX() + owner.getWidth()/12);
        setY(owner.getY() + owner.getHeight()*2);
    }
    public void destroy(){
        if ((int)time.getTime() == finishTime){
            GameScreen.bullets.remove(this);
        }
    }
    public void attack(){
        rect = getBoundingRectangle();
        for (int i = 0;i< GameScreen.bullets.getBullets().size;i++){
            bullet = GameScreen.bullets.getBullets().get(i);
            if(bullet instanceof PixieBullet) continue;
            if(rect.overlaps(bullet.getBoundingRectangle())){
                GameScreen.bullets.remove(bullet);
                Bullet newBullet = new PixieBullet(owner,bullet.getTexture(),(int)bullet.getX(),(int)bullet.getY(),' ','U',bullet.ATK,bullet.SPD);
                newBullet.setColor(Color.LIGHT_GRAY);
                GameScreen.bullets.add(newBullet);
                return;

            }
        }

    }


}
