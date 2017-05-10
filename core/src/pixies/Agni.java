package pixies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import bullets.AgniBullet;
import screens.GameScreen;

/**
 * Created by victor on 4/10/17.
 */
public class Agni extends Pixie {
    Texture fireball;
    public Agni(int x, int y, int HP, int ATK, int SPD) {
        super(new Texture(Gdx.files.internal("pixies/agni.png")), x, y, HP, ATK, SPD);
        bulletTexture = new Texture(Gdx.files.internal("bullets/star_bullet3.png"));
        fireball = new Texture(Gdx.files.internal("effects/fireball.png"));
    }
    public void ability(){
        if(!longTouched) return;
        if(abilityUsed) return;

        GameScreen.bullets.add(new AgniBullet(fireball, (int) (getX() + getWidth() / 2), (int) (getY() + getHeight()), ' ', 'U'));
        GameScreen.bullets.add(new AgniBullet(fireball,(int) (getX() + getWidth() / 2), (int) (getY() + getHeight()),'L','U'));
        GameScreen.bullets.add(new AgniBullet(fireball,(int) (getX() + getWidth() / 2), (int) (getY() + getHeight()),'R','U'));
        abilityUsed = true;
    }
}
