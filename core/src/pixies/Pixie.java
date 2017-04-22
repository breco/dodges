package pixies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.breco.dodges.MainGame;

import bullets.PixieBullet;
import screens.GameScreen;
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

    public String status;
    public int BULLET_SPD = 20;


    //variables for hud
    public float PERCENT_HP;
    public char COLOR_HP;
    public Texture green = new Texture(Gdx.files.internal("huds/green.png"));
    public Texture red = new Texture(Gdx.files.internal("huds/red.png"));
    public Texture yellow = new Texture(Gdx.files.internal("huds/yellow.png"));
    public Texture gray = new Texture(Gdx.files.internal("huds/gray.png"));
    public Texture curr = green;
    //shoot variables
    private int SPD_CONT = 0;
    public Texture bulletTexture;

    //ability variables
    public boolean abilityUsed = false;
    public Pixie(Texture texture,int x, int y,int HP, int ATK, int SPD){
        //graphics
        super(texture);
        setPosition(x, y);
        scale(2);
        touchRect = new Rectangle(x-80,y-80,getWidth()+160,getHeight()+160);
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
        }


        if(touched2){
            if(MyGestures.newTouch2.y >= 5* MainGame.HEIGHT/6-getHeight()){
                return;
            }
            setX(getX() - MyGestures.diff2.x);
            setY(getY() + MyGestures.diff2.y);
            touchRect.setX(touchRect.getX() - MyGestures.diff2.x);
            touchRect.setY(touchRect.getY() + MyGestures.diff2.y);
        }



    }
    public void shoot(){
        SPD_CONT++;
        if(SPD_CONT < SPD_TOTAL) return;
        SPD_CONT = 0;
        GameScreen.bullets.add(new PixieBullet(bulletTexture, (int) (getX() + getWidth() / 2), (int) (getY() + getHeight()), ' ', 'U', ATK_TOTAL, BULLET_SPD));
    }
    public void update(){
        if(status.equals("dead")) return;
        move();
        shoot();
        ability();
    }
    public void ability(){

    }
    public void draw(SpriteBatch batch) {
        if(status.equals("dead")) return;
        super.draw(batch);
    }
    public void drawHUD(SpriteBatch batch){
        if(status.equals("dead")) return;
        batch.draw(gray,getX()-getWidth()*1.3f,getY()+getHeight()*2.3f,120,10);
        batch.draw(curr, getX() - getWidth() * 1.3f, getY() + getHeight() * 2.3f, PERCENT_HP * 120 / 100, 10);
    }
    public void long_input(Vector3 vec){
        if(!canBeLongTouched) return;
        if (touchRect.contains(vec.x, vec.y)){
            longTouched = true;
            return;
        }
        longTouched = false;
    }
    public void input(Vector3 vec,int pointer){

        Gdx.app.log("CURR POINTER",""+(pointer+1));
        if(pointer == 0){

            if(!canBeTouched) return;
            if (touchRect.contains(vec.x,vec.y)){
                touched = true;
                return;
            }
            touched = false;
        }
        else if(pointer == 1){
            pointerTwoTouched = true;
            if(!canBeTouched2) return;
            if (touchRect.contains(vec.x,vec.y)){
                touched2 = true;
                return;
            }
            touched2 = false;
        }


    }
    public void heal(int amount){
        if(status.equals("dead")) return;
        CURRENT_HP+= amount;
        if(CURRENT_HP > HP){
            CURRENT_HP = HP;
        }
        PERCENT_HP = CURRENT_HP*100/HP;
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
    public void damage(int dmg){
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
    }
    public void changeATK(int amount){
        ATK_VARIABLE += amount;
        ATK_TOTAL = ATK_FIXED + ATK_VARIABLE;
        if(ATK_TOTAL < 0) ATK_TOTAL = 0;
    }
    public void changeSPD(int amount){
        SPD_VARIABLE += amount;
        SPD_TOTAL = SPD_FIXED + SPD_VARIABLE;
        Gdx.app.log("ChangeSPD",""+SPD_TOTAL);
    }
}
