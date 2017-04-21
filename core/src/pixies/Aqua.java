package pixies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import bullets.BubbleShield;
import screens.GameScreen;

/**
 * Created by victor on 4/10/17.
 */
public class Aqua extends Pixie {
    private Texture bubble;
    public Aqua(int x, int y, int HP, int ATK, int SPD) {
        super(new Texture(Gdx.files.internal("pixies/aqua.png")), x, y, HP, ATK, SPD);
        bulletTexture = new Texture(Gdx.files.internal("bullets/star_bullet2.png"));
        bubble = new Texture(Gdx.files.internal("effects/bubble.png"));
    }
    public void ability(){
        if(!longTouched) return;
        if(abilityUsed) return;
        GameScreen.bullets.add(new BubbleShield(bubble,(int) getX(),(int) getY(),' ',' ',this));
        abilityUsed = true;
    }
}
