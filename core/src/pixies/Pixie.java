package pixies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.breco.dodges.MainGame;

import bullets.PixieBullet;
import screens.GameScreen;
import utils.Animator;
import utils.Counter;
import utils.MyGestures;

/**
 * Created by victor on 3/21/17.
 */
public class Pixie extends Sprite {

    //INPUT VARIABLES
    public Rectangle touchRect;
    public boolean pointerTwoTouched = false;
    //input variables finger 1
    public boolean touched = false;
    public int touchDraw = 0;
    public boolean longTouched = false;
    public boolean canBeTouched = true;
    public boolean canBeLongTouched = true;

    //input variables finger 2
    public boolean touched2 = false;
    public boolean longTouched2 = false;
    public boolean canBeTouched2 = true;
    public boolean canBeLongTouched2 = true;

    //stats

    public int HP;
    public int CURRENT_HP;
    //attack
    private int ATK_FIXED, ATK_VARIABLE = 0,ATK_TOTAL;
    //speed
    private int SPD_FIXED,SPD_TOTAL,SPD_VARIABLE =0;

    //STATUS
    public String status;

    //BULLET STATS
    public int BULLET_SPD = 20;


    //variables for hud
    public float PERCENT_HP;
    public char COLOR_HP;
    public Texture green = new Texture(Gdx.files.internal("huds/green.png"));
    public Texture red = new Texture(Gdx.files.internal("huds/red.png"));
    public Texture yellow = new Texture(Gdx.files.internal("huds/yellow.png"));
    public Texture gray = new Texture(Gdx.files.internal("huds/gray.png"));
    public Texture pin = new Texture(Gdx.files.internal("huds/green_pin.png"));
    public Texture curr = green;
    //shoot variables
    private int SPD_CONT = 0;
    public Texture bulletTexture;

    //ability variables
    public boolean abilityUsed = false;

    //impact variables
    private Counter impactCounter;

    //animation variables
    private boolean blink = false;
    Animator animator;

    //TEST NO BORRAR !
    //Sprite rect = new Sprite(new Texture(Gdx.files.internal("huds/gray.png")));

    public Pixie(Texture texture,int x, int y,int HP, int ATK, int SPD){
        super();
        setPosition(x,y);
        setSize(32,32);
        scale(1);

        //touchRect = new Rectangle(x-80,y-80,getWidth()+160,getHeight()+160);


        touchRect = new Rectangle(getX()+getWidth()/2-80,getY() - getHeight() * 3f -80,getWidth()+160,getHeight()+220);

        //game logic

        this.HP = HP;
        CURRENT_HP = HP;
        ATK_FIXED = ATK;
        ATK_TOTAL = ATK_FIXED + ATK_VARIABLE;
        SPD_FIXED = SPD;
        SPD_TOTAL = SPD_FIXED + SPD_VARIABLE;
        PERCENT_HP = 100;
        COLOR_HP = 'G';
        status = "normal";
        bulletTexture = new Texture(Gdx.files.internal("bullets/star_bullet.png"));
        impactCounter = new Counter();
        //Gdx.app.log("CLASS NAME",this.getClass().toString());
    }
    public void fixMove(){
        int horizontalBorder = 230;
        if(getX() > horizontalBorder){
            setX(horizontalBorder);
            touchRect.setX(horizontalBorder+getWidth()/2-80);
        }
        if(getX() < -horizontalBorder-50){
            setX(-horizontalBorder-50);
            touchRect.setX(-horizontalBorder-50+getWidth()/2-80);
        }
        if(getY() > 550){
            setY(550);
            touchRect.setY(550- getHeight() * 3f -80);
        }
        if(getY()< -400){
            setY(-400);
            touchRect.setY(-400- getHeight() * 3f -80);
        }
    }
    public void move(){
        if(touched){
            if(MyGestures.newTouch.y >= 5* MainGame.HEIGHT/6-getHeight()){
                return;
            }
            setX(getX() - MyGestures.diff.x);
            setY(getY() + MyGestures.diff.y);
            touchRect.setX(touchRect.getX() - MyGestures.diff.x);
            touchRect.setY(touchRect.getY() + MyGestures.diff.y);
            fixMove();
            return;
        }

        if(touched2){
            if(MyGestures.newTouch2.y >= 5* MainGame.HEIGHT/6-getHeight()){
                return;
            }
            setX(getX() - MyGestures.diff2.x);
            setY(getY() + MyGestures.diff2.y);
            touchRect.setX(touchRect.getX() - MyGestures.diff2.x);
            touchRect.setY(touchRect.getY() + MyGestures.diff2.y);
            fixMove();
        }



    }
    public void shoot(){
        SPD_CONT++;
        if(SPD_CONT < SPD_TOTAL) return;
        SPD_CONT = 0;
        GameScreen.bullets.add(new PixieBullet(bulletTexture, (int) (getX() + getWidth()*3/4f), (int) (getY() + getHeight()), ' ', 'U', ATK_TOTAL, BULLET_SPD));
    }

    public void ability(){

    }

    public void update(){
        if(status.equals("dead")) return;
        animation();
        move();
        shoot();
        ability();
        impactCounter.update();
        if(impactCounter.check()){
            impactCounter.reset();
        }
    }

    //ANIMATION METHODS

    public void animation(){
        if(impactCounter.started()){
            if(blink){
                blink = false;
                setColor(Color.WHITE);
            }
            else{
                setColor(Color.BLACK);
                blink = true;
            }
        }
        else{
            setColor(Color.WHITE);
        }
    }
    //DRAW METHODS
    public void draw(SpriteBatch batch) {
        if(status.equals("dead")) return;


        //batch.draw(rect.getTexture(),touchRect.getX(),touchRect.getY(),touchRect.getWidth(),touchRect.getHeight());
        animator.draw(this,batch);

        batch.draw(pin,getX()+getWidth()/2,getY() - getHeight() * 3f);
    }
    public void drawHUD(SpriteBatch batch){
        if(status.equals("dead")) return;
        batch.draw(gray,getX() - getWidth(),getY()+getHeight()*3.5f,MainGame.WIDTH*0.2f ,10);

        batch.draw(curr, getX() - getWidth(), getY() + getHeight()*3.5f, PERCENT_HP * MainGame.WIDTH*0.2f / 100f, 10);


    }

    //INPUTS

    public void long_input(Vector3 vec){
        if(!canBeLongTouched) return;
        if (touchRect.contains(vec.x, vec.y)){
            longTouched = true;
            return;
        }
        longTouched = false;
    }
    public void input(Vector3 vec,int pointer){

        //Gdx.app.log("CURR POINTER",""+(pointer+1));
        if(pointer == 0){

            if(!canBeTouched) return;
            if (touchRect.contains(vec.x,vec.y)){
                touched = true;
                touchDraw++;
                return;
            }
            touched = false;
            touchDraw--;
        }
        else if(pointer == 1){
            pointerTwoTouched = true;
            if(!canBeTouched2) return;
            if (touchRect.contains(vec.x,vec.y)){
                touched2 = true;
                touchDraw++;
                return;
            }
            touched2 = false;
            touchDraw--;
        }


    }

    //HP CHANGES METHODS

    public void heal(int amount){
        if(status.equals("dead")) return;
        CURRENT_HP+= amount;
        if(CURRENT_HP > HP){
            CURRENT_HP = HP;
        }
        PERCENT_HP = CURRENT_HP*100f/HP;
        if(PERCENT_HP >= 50){
            curr = green;
            COLOR_HP = 'G';
        }
        else if(PERCENT_HP >= 25){
            curr = yellow;
            COLOR_HP = 'Y';
        }
        else{
            curr = red;
            COLOR_HP = 'R';
        }
    }

    public void impact(int dmg){
        if(impactCounter.started()){
            return;
        }
        getDamage(dmg);
    }

    public void getDamage(int dmg){
        if(!impactCounter.started())  impactCounter.setLimit(50);
        CURRENT_HP-= dmg;
        if(CURRENT_HP < 0){
            CURRENT_HP = 0;
            status = "dead";

        }
        PERCENT_HP = CURRENT_HP*100/HP;
        if(PERCENT_HP >= 50){
            curr = green;
            COLOR_HP = 'G';
        }
        else if(PERCENT_HP >= 25){
            COLOR_HP = 'Y';
            curr = yellow;
        }

        else{
            curr = red;
            COLOR_HP = 'R';
        }
        if(PERCENT_HP < 1){
            PERCENT_HP = 1;
        }

    }

    //STAT CHANGE METHODS
    public void changeATK(int amount){
        ATK_VARIABLE += amount;
        ATK_TOTAL = ATK_FIXED + ATK_VARIABLE;
        if(ATK_TOTAL < 0) ATK_TOTAL = 0;
    }
    public void changeSPD(int amount){
        SPD_VARIABLE += amount;
        SPD_TOTAL = SPD_FIXED + SPD_VARIABLE;
    }

}
