package enemies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.concurrent.ThreadLocalRandom;

import bullets.EnemyBullet;
import screens.GameScreen;
import utils.Animator;

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
    boolean CURTAIN_SHOOTING = false;

    Color color = Color.GOLD;
    public Bigbat(int x, int y, int appearance) {
        //HP ATK
        super(x, y, 200, 5, appearance);
        scale(3);
        ori = 'L';
        animator = new Animator(new Texture(Gdx.files.internal("enemies/tuzzu.png")),1,4,4,0.15f);
        setColor(color);
        int[] size = {15,15};
        bulletAnimator = new Animator(new Texture(Gdx.files.internal("bullets/tuzzu bullet.png")),1,2,2,0.8f,size);
    }


    public void move(){
        SPD_CONT++;
        if(ori == 'L'){
            setX(getX() - SPD_X);
            if(SPD_CONT >= MAX_SPD){
                ori = 'R';
                SPD_CONT = 0;
                //STOP_SHOOT = true;
                int isCurtain = ThreadLocalRandom.current().nextInt(0,2);
                if(isCurtain == 0){
                    CURTAIN_SHOOTING = true;
                }
                else{
                    CURTAIN_SHOOTING = false;
                }
            }
        }
        else if(ori == 'R'){
            setX(getX() + SPD_X);
            if(SPD_CONT >= MAX_SPD){
                ori = 'L';
                SPD_CONT = 0;
                //STOP_SHOOT = false;
                int isCurtain = ThreadLocalRandom.current().nextInt(0,2);
                if(isCurtain == 0){
                    CURTAIN_SHOOTING = true;
                }
                else{
                    CURTAIN_SHOOTING = false;
                }
            }
        }
        SPD_Y_CONT++;
        if(SPD_Y_CONT <= MAX_Y){
            setY(getY()-SPD_Y);
        }


    }
    public void shoot(){

        if(STOP_SHOOT) return;
        SHOT_CONT--;
        if(SHOT_CONT == 0){
            if(CURTAIN_SHOOTING){
                SHOT_CONT = 25;
            }
            else{
                SHOT_CONT = ThreadLocalRandom.current().nextInt(30,41);
            }

            createBullet();
        }
    }
    public void createBullet(){
        float x = getX()+ getWidth()*3;
        float y = getY()+ getHeight()/2;
        GameScreen.bullets.add(new EnemyBullet(bulletAnimator,(int)x,(int)y,' ','D',ATK,6));
    }
    public void update(){
        this.attack();
        move();
        shoot();
        animation();
        impactCounter.update();
        if(impactCounter.check()){
            impactCounter.reset();
        }
    }
    public void animation(){
        if(impactCounter.started()){
            if(blink){
                blink = false;
                setColor(color);
            }
            else{
                setColor(Color.BLACK);
                blink = true;
            }
        }
        else{
            setColor(color);
        }
    }
    public void draw(SpriteBatch batch){
        animator.draw(this,batch);
    }
}
