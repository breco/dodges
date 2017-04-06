package pixies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;


public class Pixies {
    public Array<Pixie> pixies;
    private Pixie temp;
    private float x,y,dist;
    public Pixies() {
        pixies = new Array<Pixie>();
        x = 0;
        y = 0;
        dist = 100000;

    }
    public void draw(SpriteBatch batch){
        for(Pixie pixie : pixies){
            pixie.draw(batch);
        }
    }
    public void update(){
        for(Pixie pixie: pixies){
            pixie.update();
        }
    }
    public void remove(Pixie pixie){
        //
         pixies.removeValue(pixie, false);
    }
    public Array<Pixie> getPixies(){
        //
        return pixies;
    }
    public void add(Pixie pixie){
        //
        pixies.add(pixie);
    }
    public void input(Vector3 vec){
        //get touched pixies
        for(Pixie pixie: pixies){
            pixie.input(vec);

        }
        //get nearest pixie from touch position
        for(Pixie pixie: pixies){
            if(!pixie.touched) continue;
            x = pixie.getX()+pixie.getWidth()/2;
            y = pixie.getY()+pixie.getHeight()/2;
            Gdx.app.log("dist",""+vec.dst(x,y,0));
            if (vec.dst(x,y,0)< dist){
                dist = vec.dst(x,y,0);
                temp = pixie;
            }
        }
        //mark only the nearest pixie
        for(Pixie pixie: pixies){
            if(pixie.equals(temp)){
                pixie.touched = true;
            }
            else {
                pixie.touched = false;
            }
        }
        //make other pixies untouchable
        for(Pixie pixie: pixies){
            if(!pixie.touched) pixie.canBeTouched = false;
        }
        temp = null;
        x = 0;
        y = 0;
        dist = 10000;

    }
    public void setUntouched(){
        for(Pixie pixie: pixies){
            pixie.touched = false;
            pixie.canBeTouched = true;
        }
    }
}
