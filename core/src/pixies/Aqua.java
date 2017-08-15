package pixies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import bullets.BubbleShield;
import screens.GameScreen;
import utils.Animator;

/**
 * Created by victor on 4/10/17.
 */
public class Aqua extends Pixie {
    private Texture bubble;
    public Aqua(int x, int y, int HP, int ATK, int SPD) {
        super(new Texture(Gdx.files.internal("pixies/aqualis.png")), x, y, HP, ATK, SPD);
        bulletTexture = new Texture(Gdx.files.internal("bullets/star_bullet2.png"));
        bubble = new Texture(Gdx.files.internal("effects/bubble.png"));
        animator = new Animator(new Texture(Gdx.files.internal("pixies/aqualis.png")),1,2,2,0.4f);
    }
    public void ability(){
        if(!longTouched) return;
        if(abilityUsed) return;
        GameScreen.bullets.add(new BubbleShield(bubble,1,1,' ',' ',this));
        abilityUsed = true;
    }
}
