package pixies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by victor on 4/10/17.
 */
public class Tera extends Pixie {
    public Tera(int x, int y, int HP, int ATK, int SPD) {
        super(new Texture(Gdx.files.internal("pixies/tera.png")), x, y, HP, ATK, SPD);
        bulletTexture = new Texture(Gdx.files.internal("bullets/star_bullet.png"));
    }
}
