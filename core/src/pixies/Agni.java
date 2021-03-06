package pixies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

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
        //GameScreen.bullets.add(new AgniBullet(this,fireball, (int) (getX() + getWidth() / 2), (int) (getY() + getHeight()), ' ', 'U'));
        //GameScreen.bullets.add(new AgniBullet(this,fireball,(int) (getX() + getWidth() / 2), (int) (getY() + getHeight()),'L','U'));
        //GameScreen.bullets.add(new AgniBullet(this,fireball,(int) (getX() + getWidth() / 2), (int) (getY() + getHeight()),'R','U'));
    }
}
