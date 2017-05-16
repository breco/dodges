package huds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.breco.dodges.MainGame;

import buttons.My_Button;
import screens.GameScreen;
import screens.WorldScreen;

/**
 * Created by victor on 4/25/17.
 */
public class PauseHUD {
    private GameScreen game;
    private My_Button resume,quit;
    public PauseHUD(GameScreen game){
        this.game = game;
        resume = new My_Button(new Texture(Gdx.files.internal("buttons/resume_button.png")));
        resume.scale(2);
        resume.setPosition(MainGame.WIDTH / 2 - resume.getWidth() / 2, MainGame.HEIGHT / 2 + 2*resume.getHeight());

        quit = new My_Button(new Texture(Gdx.files.internal("buttons/quit_button.png")));
        quit.scale(2);
        quit.setPosition(MainGame.WIDTH / 2 - quit.getWidth() / 2, MainGame.HEIGHT / 2 - 2*quit.getHeight());

    }
    public void draw(SpriteBatch batch){
        resume.draw(batch);
        quit.draw(batch);
    }
    public void update(){

    }
    public void input(Vector3 vec){
        vec.set(vec.x, MainGame.HEIGHT - vec.y, 0);
        if(resume.getBoundingRectangle().contains(vec.x,vec.y)){
            game.resume();
        }
        if(quit.getBoundingRectangle().contains(vec.x,vec.y)){
            game.game.setScreen(new WorldScreen(game.game));
            game.dispose();
        }
    }

}
