package enemies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.concurrent.ThreadLocalRandom;

import bullets.EnemyBullet;
import screens.GameScreen;

/**
 * Created by victor on 4/3/17.
 */
public class Bigbat extends Enemy {


    //movement variables
    char ori;
    int MAX_SPD = 150;
    int SPD_X = 3;
    int SPD_Y = 1;
    int SPD_CONT = 75;
    int SHOT_CONT = 10;

    int MAX_Y = 200;
    int SPD_Y_CONT = 0;
    boolean STOP_SHOOT = false;
    public Bigbat(Texture texture, int x, int y, int HP, int ATK, int appearance) {
        super(texture, x, y, HP, ATK, appearance);
        scale(3);
        ori = 'L';
    }


    public void move(){
        SPD_CONT++;
        if(ori == 'L'){
            setX(getX() - SPD_X);
            if(SPD_CONT >= MAX_SPD){
                ori = 'R';
                SPD_CONT = 0;
                STOP_SHOOT = true;
            }
        }
        else if(ori == 'R'){
            setX(getX() + SPD_X);
            if(SPD_CONT >= MAX_SPD){
                ori = 'L';
                SPD_CONT = 0;
                STOP_SHOOT = false;
            }
        }
        SPD_Y_CONT++;
        if(SPD_Y_CONT <= MAX_Y)
            setY(getY()-SPD_Y);
    }
    public void shoot(){
        if(STOP_SHOOT) return;
        SHOT_CONT--;
        if(SHOT_CONT == 0){
            SHOT_CONT = ThreadLocalRandom.current().nextInt(10,21);
            float x = getX()+ getWidth();
            float y = getY();
            GameScreen.bullets.add(new EnemyBullet(new Texture(Gdx.files.internal("bullets/normal_bullet.png")),(int)x,(int)y,' ','D',ATK,6));
        }
    }
    public void update(){

        move();
        shoot();
    }
    public void draw(SpriteBatch batch){
        super.draw(batch);
    }
}
