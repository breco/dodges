package pixies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import huds.PixiePin;
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
        int[] size = {15,15};
        bulletAnimator = new Animator(new Texture(Gdx.files.internal("bullets/bubble bullet.png")),1,4,4,0.6f,size);
        PP = 40;
        PP_COST = 40;
        pin = new PixiePin(this,"light blue");
    }
    public void ability(){
        if(CURRENT_PP < PP_COST) return;
        CURRENT_PP -= PP_COST;

        //GameScreen.bullets.add(new BubbleShield(bubble,1,1,' ',' ',this));
        //abilityUsed = true;
    }
}
