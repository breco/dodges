package huds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.breco.dodges.MainGame;

import buttons.My_Button;
import screens.WorldScreen;

/**
 * Created by victor on 7/24/17.
 */

public class WinHUD {
    private MainGame game;
    private My_Button volver;
    private Sprite bg,text;
    private Sprite star;
    public WinHUD(MainGame game){
        this.game = game;
        bg = new Sprite(new Texture(Gdx.files.internal("buttons/stage_cleared_bg.png")));
        bg.scale(2.5f);
        bg.setPosition(MainGame.WIDTH / 2 - bg.getWidth()/2, MainGame.HEIGHT / 2 );
        text = new Sprite(new Texture(Gdx.files.internal("huds/stage_cleared_text.png")));
        text.setPosition(MainGame.WIDTH / 2 - text.getWidth() / 2, MainGame.HEIGHT /2 + 2f*text.getHeight());
        volver = new My_Button(new Texture(Gdx.files.internal("buttons/return_button.png")));
        volver.scale(1.1f);
        volver.setPosition(MainGame.WIDTH / 2 - volver.getWidth() / 2, MainGame.HEIGHT /2 - 4f*volver.getHeight());
        star = new Sprite(new Texture(Gdx.files.internal("huds/star.png")));
        star.setPosition(MainGame.WIDTH / 2 - star.getWidth() / 2, MainGame.HEIGHT / 2 + star.getHeight());

    }
    public void draw(SpriteBatch batch){
        bg.draw(batch);
        text.draw(batch);
        volver.draw(batch);
        star.draw(batch);

    }
    public void update(){

    }
    public void input(Vector3 vec){
        vec.set(vec.x, MainGame.HEIGHT - vec.y, 0);
        if(volver.getBoundingRectangle().contains(vec.x,vec.y)){
            game.setScreen(new WorldScreen(game));
        }
    }
}
