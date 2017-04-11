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

    //input variables
    public boolean touched = false;
    public boolean canBeTouched = true;
    public Rectangle touchRect;

    //stats

    public int HP,ATK,SPD;
    public int CURRENT_HP;
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

    public Pixie(Texture texture,int x, int y,int HP, int ATK, int SPD){
        //graphics
        super(texture);
        setPosition(x, y);
        scale(2);
        touchRect = new Rectangle(x-80,y-80,getWidth()+160,getHeight()+160);
        //game logic
        this.HP = HP;
        CURRENT_HP = HP;
        this.ATK = ATK;
        this.SPD = SPD;
        PERCENT_HP = 100;
        COLOR_HP = 'G';
        status = "normal";
        bulletTexture = new Texture(Gdx.files.internal("bullets/star_bullet.png"));



    }
    public void move(){
        if(!touched) return;
        if(MyGestures.newTouch.y >= 5* MainGame.HEIGHT/6-getHeight()){
            return;
        }
        setX(getX() - MyGestures.diff.x);
        setY(getY() + MyGestures.diff.y);
        touchRect.setX(touchRect.getX() - MyGestures.diff.x);
        touchRect.setY(touchRect.getY() + MyGestures.diff.y);

    }
    public void shoot(){
        SPD_CONT++;
        if(SPD_CONT < SPD) return;
        SPD_CONT = 0;
        GameScreen.bullets.add(new PixieBullet(bulletTexture, (int) (getX() + getWidth() / 2), (int) (getY() + getHeight()), ' ', 'U', ATK, BULLET_SPD));
    }
    public void update(){
        if(status.equals("dead")) return;
        move();
        shoot();
    }
    public void draw(SpriteBatch batch) {
        if(status.equals("dead")) return;
        batch.draw(gray,getX()-getWidth(),getY()+getHeight()*2.1f,120,10);
        batch.draw(curr,getX()-getWidth(),getY()+getHeight()*2.1f,PERCENT_HP*120/100,10);
        super.draw(batch);
    }
    public void input(Vector3 vec){
        if(!canBeTouched) return;
        if (touchRect.contains(vec.x,vec.y)){
            touched = true;
            return;
        }
        touched = false;

    }
    public void heal(int amount){
        Gdx.app.log("HEAL","HEAL");
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
}
