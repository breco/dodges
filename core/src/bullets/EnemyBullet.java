package bullets;

import com.breco.dodges.MainGame;

import pixies.Pixie;
import screens.GameScreen;
import utils.Animator;

/**
 * Created by victor on 3/25/17.
 */
public class EnemyBullet extends Bullet {
    public EnemyBullet(Animator animator, int x, int y, char oriX, char oriY, int ATK, int SPD) {
        super(animator, x, y, oriX, oriY, ATK, SPD);
    }
    public void update(){
        move();
        destroy();
        attack();
    }
    public void attack(){
        for(Pixie pixie : GameScreen.pixies.getPixies()){

            if(pixie.getBoundingRectangle().contains(getBoundingRectangle())){
                pixie.getDamage(ATK);
                GameScreen.bullets.remove(this);
                return;
            }
        }
    }
    public void destroy(){
        if(getY() <= -MainGame.HEIGHT/2){
            //Gdx.app.log("DELETE ENEMY BULLET","THIS");
            GameScreen.bullets.remove(this);
        }
    }
}
