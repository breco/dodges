package items;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import bullets.MirrorShield;
import pixies.Pixie;
import screens.GameScreen;

/**
 * Created by victor on 4/21/17.
 */
public class MagicMirror extends Item {


    public MagicMirror(Texture texture) {
        super(texture, "auto");
    }
    public void effect(){
        for(Pixie pixie: GameScreen.pixies.getPixies()){
            if(pixie.status.equals("dead")) continue;
            GameScreen.bullets.add(new MirrorShield(new Texture(Gdx.files.internal("effects/mirror.png")),pixie));

        }
        used = true;
    }

}
