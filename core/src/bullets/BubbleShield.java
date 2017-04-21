package bullets;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.breco.dodges.MainGame;

import pixies.Pixie;
import screens.GameScreen;
import utils.TimeManager;

/**
 * Created by victor on 4/21/17.
 */
public class BubbleShield extends PixieBullet {
    Pixie owner;
    private Rectangle rect;
    private TimeManager time;
    private int finishTime;
    private Bullet bullet;
    public BubbleShield(Texture texture, int x, int y, char oriX, char oriY, Pixie owner) {
        //attack = 0, speed = 0
        super(texture, x, y, oriX, oriY,0,0);
        this.owner = owner;
        finishTime = 10;
        time = new TimeManager();
        time.start();


    }
    public void attack(){
        rect = getBoundingRectangle();
        for (int i = 0;i<GameScreen.bullets.getBullets().size;i++){
            bullet = GameScreen.bullets.getBullets().get(i);
            if(bullet instanceof PixieBullet) continue;
            if(rect.overlaps(bullet.getBoundingRectangle())){
                GameScreen.bullets.remove(bullet);
            }
        }
    }
    public void move(){
        setX(owner.getX() - MainGame.WIDTH/50);
        setY(owner.getY() - MainGame.HEIGHT/100);
    }
    public void destroy(){
        if ((int)time.getTime() == finishTime){
            GameScreen.bullets.remove(this);
        }
    }
}
