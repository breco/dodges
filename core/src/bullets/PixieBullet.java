package bullets;

import com.badlogic.gdx.graphics.Texture;

import enemies.Enemy;
import pixies.Pixie;
import screens.GameScreen;

/**
 * Created by victor on 3/25/17.
 */
public class PixieBullet extends Bullet {
    public Pixie owner;
    public PixieBullet(Pixie owner, Texture texture, int x, int y, char oriX, char oriY, int ATK, int SPD) {
        super(texture, x, y, oriX,oriY, ATK,SPD);
        this.owner = owner;
    }
    public void update(){
        move();
        destroy();
        attack();
    }
    public void attack(){
        for(Enemy enemy : GameScreen.enemies.getEnemies()){
            if(enemy.getBoundingRectangle().contains(getBoundingRectangle())){
                enemy.getDamage(ATK);
                GameScreen.bullets.remove(this);
                owner.chargePower(1);
                return;
            }
        }
    }
    public void destroy(){
        if(getY() >= 1300){
            //Gdx.app.log("DELETE BULLET PIXIE","THIS");
            GameScreen.bullets.remove(this);
        }
    }
}
