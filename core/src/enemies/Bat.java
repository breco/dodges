package enemies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.concurrent.ThreadLocalRandom;

import bullets.EnemyBullet;
import screens.GameScreen;

/**
 * Created by victor on 3/24/17.
 */
public class Bat extends Enemy {

    //movement variables
    char ori;
    int MAX_SPD = 150;
    int SPD_X = 3;
    int SPD_Y = 1;
    int SPD_CONT = 75;
    int SHOT_CONT = ThreadLocalRandom.current().nextInt(10,61);


    // HP ATK
    public Bat(int x, int y,int appearance){
        super(new Texture(Gdx.files.internal("enemies/bat.png")),x,y,14,4,appearance);
        int index = ThreadLocalRandom.current().nextInt(0,2);
        char[] orientations = {'L','R'};
        ori = orientations[index];
        //scale(2);
        setPosition(x,y);



    }

    public void move(){
        SPD_CONT++;
        if(ori == 'L'){
            setX(getX() - SPD_X);
            if(SPD_CONT >= MAX_SPD){
                ori = 'R';
                SPD_CONT = 0;
            }
        }
        else if(ori == 'R'){
            setX(getX() + SPD_X);
            if(SPD_CONT >= MAX_SPD){
                ori = 'L';
                SPD_CONT = 0;
            }
        }
        setY(getY()-SPD_Y);
    }
    public void shoot(){
        SHOT_CONT--;
        if(SHOT_CONT == 0){
            SHOT_CONT = ThreadLocalRandom.current().nextInt(100,151);
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
