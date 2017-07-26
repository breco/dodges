package enemies;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.breco.dodges.MainGame;

import pixies.Pixie;
import screens.GameScreen;
import utils.Counter;

/**
 * Created by victor on 3/24/17.
 */
public abstract class Enemy extends Sprite {
    //stats
    int HP,ATK;
    int CURRENT_HP;
    //others
    int appearance;
    //animation
    boolean blink = false;
    Counter impactCounter;
    public Enemy(Texture texture, int x, int y,int HP,int ATK, int appearance){
        super(texture);
        setPosition(x, y);
        scale(2);
        this.HP = HP;
        this.ATK = ATK;
        CURRENT_HP = HP;
        this.appearance = appearance;
        //animation
        impactCounter = new Counter();
    }
    public abstract void update();
    public abstract void move();
    public abstract void animation();

    public boolean onScreen(){
        if(getY() <= - MainGame.HEIGHT/2) return false;
        if(appearance <= GameScreen.time.getTime()) return true;
        return false;
    }
    public void draw(SpriteBatch batch){
        super.draw(batch);
    }
    public void getDamage(int dmg) {
        if(!impactCounter.started())  impactCounter.setLimit(25);
        CURRENT_HP -= dmg;
        if (CURRENT_HP < 0) {
            CURRENT_HP = 0;
            GameScreen.enemies.remove(this);
        }

    }
    public void attack(){
        for(Pixie pixie : GameScreen.pixies.getPixies()){
            if(pixie.getBoundingRectangle().overlaps(getBoundingRectangle())){
                pixie.impact(ATK);
                return;
            }
        }
    }
}
