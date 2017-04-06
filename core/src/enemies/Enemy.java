package enemies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import screens.GameScreen;

/**
 * Created by victor on 3/24/17.
 */
public abstract class Enemy extends Sprite {
    //stats
    int HP,ATK;
    int CURRENT_HP;
    //others
    int appearance;
    public Enemy(Texture texture, int x, int y,int HP,int ATK, int appearance){
        super(texture);
        setPosition(x, y);
        scale(2);
        this.HP = HP;
        this.ATK = ATK;
        CURRENT_HP = HP;
        this.appearance = appearance;
        Gdx.app.log("APP",""+appearance);
    }
    public abstract void update();
    public abstract void move();
    public boolean onScreen(){
        if(appearance <= GameScreen.time.getTime()) return true;
        return false;
    }
    public void draw(SpriteBatch batch){
        super.draw(batch);
    }
    public void getDamage(int dmg){
        CURRENT_HP-= dmg;
        if(CURRENT_HP < 0){
            CURRENT_HP = 0;
            GameScreen.enemies.remove(this);
        }
        //Gdx.app.log("DAMAGE",""+CURRENT_HP);
    }
}
