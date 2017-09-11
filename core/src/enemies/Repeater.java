package enemies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import bullets.EnemyBullet;
import screens.GameScreen;
import utils.Animator;
import utils.Counter;

/**
 * Created by victor on 8/15/17.
 */

public class Repeater extends Enemy {

    //movement variables
    int SPD_Y = 3;
    //animation variables
    Color color = Color.RED;
    //shoot variables
    Counter shootCounter,waitCounter;
    boolean shooting = false;
    int SHOT_INTERVAL = 10;
    int WAIT_INTERVAL = 100;
    public Repeater(int x, int y,int appearance) {
        super(x, y, 25, 2, appearance);
        animator = new Animator(new Texture(Gdx.files.internal("enemies/tuzzu.png")),1,4,4,0.15f);
        setColor(color);
        shootCounter = new Counter();
        waitCounter = new Counter();
        shootCounter.setLimit(SHOT_INTERVAL);
        waitCounter.setLimit(WAIT_INTERVAL);
        pium = Gdx.audio.newSound(Gdx.files.internal("sound effects/04.wav"));
        int[] size = {15,15};
        bulletAnimator = new Animator(new Texture(Gdx.files.internal("bullets/tuzzu bullet red.png")),1,2,2,0.8f,size);
    }

    @Override
    public void shoot(){
        if(shooting){
            shootCounter.update();
            if (shootCounter.check()) {
                shootCounter.reset();
                shootCounter.setLimit(SHOT_INTERVAL);
                float x = getX() + getWidth();// * 1.3f;
                float y = getY();

                GameScreen.bullets.add(new EnemyBullet(bulletAnimator, (int) x, (int) y, ' ', 'D', ATK, 6));
            }
        }
        waitCounter.update();
        if(waitCounter.check()) {
            shooting = !shooting;
            waitCounter.reset();
            waitCounter.setLimit(WAIT_INTERVAL);
        }
    }

    @Override
    public void draw(SpriteBatch batch){
        animator.draw(this,batch);
    }
    @Override
    public void update() {
        this.attack();
        move();
        shoot();
        animation();
        impactCounter.update();
        if(impactCounter.check()){
            impactCounter.reset();
        }
    }

    @Override
    public void move() {
        if(getY() <= 450) return;
        setY(getY()-SPD_Y);
        Gdx.app.log("Y POS",getY()+"");
    }

    @Override
    public void animation() {

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
}
