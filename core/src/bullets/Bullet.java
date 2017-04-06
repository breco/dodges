package bullets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.breco.dodges.MainGame;

import screens.GameScreen;

/**
 * Created by victor on 3/24/17.
 */
public abstract class Bullet extends Sprite {
    char oriX,oriY;
    int SPD,ATK;
    public Bullet(Texture texture, int x, int y,char oriX,char oriY,int ATK,int SPD){
        super(texture);
        scale(1.5f);
        setPosition(x,y);
        this.oriX = oriX;
        this.oriY = oriY;
        this.SPD = SPD;
        this.ATK = ATK;
    }
    public void move(){
        if(oriX == 'L'){
            setX(getX()-SPD);
        }
        else if(oriX == 'R'){
            setX(getX()+SPD);
        }
        if(oriY == 'D'){
            setY(getY()-SPD);
        }
        else if(oriY == 'U'){
            setY(getY()+SPD);
        }
    }
    public void destroy(){
        if(getY() <= -MainGame.HEIGHT/2 || getY() >= 1300){
            Gdx.app.log("DELETE","THIS");
            GameScreen.bullets.remove(this);
        }
    }
    public void update(){
        move();
        destroy();
    }
    public void draw(SpriteBatch batch){
        super.draw(batch);
    }
}
