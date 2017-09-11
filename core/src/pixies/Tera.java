package pixies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import huds.PixiePin;
import utils.Animator;

/**
 * Created by victor on 4/10/17.
 */
public class Tera extends Pixie {
    private Texture rock;
    public Tera(int x, int y, int HP, int ATK, int SPD) {
        super(new Texture(Gdx.files.internal("pixies/terro.png")), x, y, HP, ATK, SPD);
        bulletTexture = new Texture(Gdx.files.internal("bullets/crystal bullet.png"));
        rock = new Texture(Gdx.files.internal("effects/stone.png"));
        animator = new Animator(new Texture(Gdx.files.internal("pixies/terro.png")),1,4,4,0.2f);
        int[] size = {15,15};
        bulletAnimator = new Animator(new Texture(Gdx.files.internal("bullets/crystal bullet.png")),1,4,4,0.5f,size);
        PP = 10;
        PP_COST = 10;
        pin = new PixiePin(this,"orange");
    }
    public void ability(){
        if(CURRENT_PP < PP_COST) return;
        CURRENT_PP -= PP_COST;
        for(int i = 0;i<5;i++){
            //GameScreen.bullets.add(new RockWheel(rock,this,72*i));
        }
    }
}
