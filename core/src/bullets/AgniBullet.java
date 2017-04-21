package bullets;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;

import enemies.Enemy;
import screens.GameScreen;

/**
 * Created by victor on 4/18/17.
 */
public class AgniBullet extends PixieBullet {
    private Array<Enemy> enemiesSelected;
    private int SPD_X,SPD_Y;
    public AgniBullet(Texture texture, int x, int y, char oriX, char oriY) {
        //// atk = 5;speed = 3
        super(texture, x, y, oriX, oriY, 100, 20);
        enemiesSelected = new Array<Enemy>();
        SPD_X = 5;
        SPD_Y = 20;
    }

    public void attack(){

        for(Enemy enemy : GameScreen.enemies.getEnemies()){
            if(getBoundingRectangle().overlaps(enemy.getBoundingRectangle())){

                enemy.getDamage(ATK);
                GameScreen.bullets.remove(this);
                return;
            }
        }

    }
    public void move(){
        if(oriX == 'L'){
            setX(getX()-SPD_X);
        }
        else if(oriX == 'R'){
            setX(getX()+SPD_X);
        }
        if(oriY == 'D'){
            setY(getY()-SPD_Y);
        }
        else if(oriY == 'U'){
            setY(getY()+SPD_Y);
        }
    }
}
