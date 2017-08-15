package pixies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import bullets.RockWheel;
import screens.GameScreen;

/**
 * Created by victor on 4/10/17.
 */
public class Tera extends Pixie {
    private Texture rock;
    public Tera(int x, int y, int HP, int ATK, int SPD) {
        super(new Texture(Gdx.files.internal("pixies/tera.png")), x, y, HP, ATK, SPD);
        bulletTexture = new Texture(Gdx.files.internal("bullets/star_bullet.png"));
        rock = new Texture(Gdx.files.internal("effects/stone.png"));
    }
    public void ability(){
        for(int i = 0;i<5;i++){
            GameScreen.bullets.add(new RockWheel(rock,this,72*i));
        }

       // abilityUsed = true;
    }
}
