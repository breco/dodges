package backgrounds;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.breco.dodges.MainGame;

/**
 * Created by victor on 3/27/17.
 */
public class Background{
    Texture bg;
    int cont;
    float bg1,bg2;
    public Background(Texture texture){
        bg = texture;
        bg1 = -MainGame.HEIGHT/3;
        bg2 = -MainGame.HEIGHT/3+MainGame.HEIGHT;
        cont = 0;
    }
    public void update(){
        bg1-=1;
        bg2-=1;
        if(bg1 + MainGame.HEIGHT <= -MainGame.HEIGHT/3){
            bg1 = -MainGame.HEIGHT/3+MainGame.HEIGHT;
        }
        if(bg2 + MainGame.HEIGHT <= -MainGame.HEIGHT/3){
            bg2 = -MainGame.HEIGHT/3+MainGame.HEIGHT;
        }
    }
    public void draw(SpriteBatch batch){

        batch.draw(bg,-MainGame.WIDTH/2,bg2,MainGame.WIDTH,MainGame.HEIGHT); // 2
        batch.draw(bg,-MainGame.WIDTH/2,bg1,MainGame.WIDTH,MainGame.HEIGHT); // 1

    }
}
