package items;

import com.badlogic.gdx.graphics.Texture;

import pixies.Pixie;
import screens.GameScreen;

/**
 * Created by victor on 3/26/17.
 */
public class Fruit extends Item {
    private int amount;
    public Fruit(Texture texture,String type){
        super(texture,type);
        amount = 10;
    }


    @Override
    public void update() {

    }

    @Override
    public void effect() {
        used = true;
        for(Pixie pixie: GameScreen.pixies.getPixies()){
            pixie.heal(amount);
        }

    }
}
