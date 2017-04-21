package bullets;

import com.badlogic.gdx.graphics.Texture;
import com.breco.dodges.MainGame;

import pixies.Pixie;
import screens.GameScreen;

/**
 * Created by victor on 3/25/17.
 */
public class EnemyBullet extends Bullet {
    public EnemyBullet(Texture texture, int x, int y, char oriX, char oriY, int ATK,int SPD) {
        super(texture, x, y, oriX, oriY, ATK, SPD);
    }
    public void update(){
        move();
        destroy();
        attack();
    }
    public void attack(){
        for(Pixie pixie : GameScreen.pixies.getPixies()){
            if(pixie.status.equals("dead")) continue;
            if(pixie.getBoundingRectangle().contains(getBoundingRectangle())){
                pixie.damage(ATK);
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
