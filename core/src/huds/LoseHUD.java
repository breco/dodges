package huds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.breco.dodges.MainGame;

import buttons.My_Button;
import screens.GameScreen;
import screens.WorldScreen;

/**
 * Created by victor on 7/24/17.
 */

public class LoseHUD {
    private MainGame game;
    private My_Button volver,retry;
    private Sprite bg,text;
    private Sound jingleLose;
    public LoseHUD(MainGame game){
        this.game = game;
        bg = new Sprite(new Texture(Gdx.files.internal("buttons/stage_cleared_bg.png")));
        bg.scale(2.5f);
        bg.setPosition(MainGame.WIDTH / 2 - bg.getWidth()/2, MainGame.HEIGHT / 2 );
        text = new Sprite(new Texture(Gdx.files.internal("huds/stage_failed_text.png")));
        text.setSize(text.getWidth()-MainGame.WIDTH/32,text.getHeight());

        text.setPosition(MainGame.WIDTH / 2 - text.getWidth() / 2, MainGame.HEIGHT /2 + 1.8f*text.getHeight());
        volver = new My_Button(new Texture(Gdx.files.internal("buttons/return_button.png")));
        volver.scale(1.1f);
        volver.setPosition(MainGame.WIDTH / 2 - 3.25f*volver.getWidth() / 2, MainGame.HEIGHT /2 - 4f*volver.getHeight());
        retry = new My_Button(new Texture(Gdx.files.internal("buttons/retry_button.png")));
        retry.scale(1.1f);
        retry.setPosition(MainGame.WIDTH /2 + retry.getWidth() / 2, MainGame.HEIGHT / 2 -4f*volver.getHeight());
        jingleLose = Gdx.audio.newSound(Gdx.files.internal("sound effects/02.wav"));

    }

    public void sound(){
        jingleLose.play(1f);
    }
    public void draw(SpriteBatch batch){
        bg.draw(batch);
        text.draw(batch);
        volver.draw(batch);
        retry.draw(batch);


    }
    public void update(){

    }
    public void input(Vector3 vec){
        vec.set(vec.x, MainGame.HEIGHT - vec.y, 0);
        if(volver.getBoundingRectangle().contains(vec.x,vec.y)){
            game.getScreen().dispose();
            game.setScreen(new WorldScreen(game));
        }
        if(retry.getBoundingRectangle().contains(vec.x,vec.y)){
            game.getScreen().dispose();
            game.setScreen(new GameScreen(game));
        }
    }
}
