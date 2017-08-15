package bullets;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

import enemies.Enemy;
import pixies.Pixie;
import screens.GameScreen;

/**
 * Created by victor on 4/18/17.
 */
public class AgniBullet extends PixieBullet {
    private Array<Enemy> enemiesSelected;
    private int SPD_X,SPD_Y;
    private Rectangle rect;
    public AgniBullet(Pixie owner, Texture texture, int x, int y, char oriX, char oriY) {
        //// atk = 5;speed = 3
        super(owner,texture, x, y, oriX, oriY, 50, 20);
        enemiesSelected = new Array<Enemy>();
        SPD_X = 5;
        SPD_Y = 15;
    }

    public void attack(){
        rect = getBoundingRectangle();
        for(Enemy enemy : GameScreen.enemies.getEnemies()){
            if(enemiesSelected.contains(enemy,false)) continue;
            if(rect.overlaps(enemy.getBoundingRectangle())){
                enemiesSelected.add(enemy);
                enemy.getDamage(ATK);
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
