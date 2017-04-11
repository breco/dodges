package pixies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by victor on 4/10/17.
 */
public class Aqua extends Pixie {
    public Aqua(int x, int y, int HP, int ATK, int SPD) {
        super(new Texture(Gdx.files.internal("pixies/aqua.png")), x, y, HP, ATK, SPD);
        bulletTexture = new Texture(Gdx.files.internal("bullets/star_bullet2.png"));
    }
}
