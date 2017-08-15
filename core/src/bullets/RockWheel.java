package bullets;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.breco.dodges.MainGame;

import pixies.Pixie;
import screens.GameScreen;

/**
 * Created by victor on 4/21/17.
 */
public class RockWheel extends PixieBullet {
    private Rectangle rect;
    private Bullet bullet;
    private float grados;
    private int radio;
    private Pixie owner;
    private int BONUS_ATK = 2;
    public RockWheel(Texture texture, Pixie owner,int grados) {
        //ATK = 20, SPD = 1
        super(owner,texture, 1,1, ' ', ' ', 0, 0);
        radio = MainGame.WIDTH/10;
        this.owner = owner;
        this.grados = grados;

        //upgrade atk
        owner.changeATK(BONUS_ATK);
    }
    public void attack(){
        rect = getBoundingRectangle();
        for (int i = 0;i< GameScreen.bullets.getBullets().size;i++){
            bullet = GameScreen.bullets.getBullets().get(i);
            if(bullet instanceof PixieBullet) continue;
            if(rect.overlaps(bullet.getBoundingRectangle())){
                GameScreen.bullets.remove(bullet);
                GameScreen.bullets.remove(this);
                owner.changeATK(-BONUS_ATK);
                return;

            }
        }

    }
    public void move(){
        setX(owner.getX() + owner.getWidth()/3  + (float) (radio * Math.cos(Math.toRadians(grados))));
        setY(owner.getY() + owner.getHeight()/2 + (float) (radio * Math.sin(Math.toRadians(grados))));
        grados+= 5;
        if(grados== 360) grados = 0;
    }
    public void destroy(){

    }
}
