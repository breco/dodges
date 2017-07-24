package pixies;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;


public class Pixies {
    private Array<Pixie> pixies;
    private Pixie temp;
    private float x, y, dist;

    public Pixies() {
        pixies = new Array<Pixie>();
        x = 0;
        y = 0;
        dist = 100000;

    }

    public void drawHUD(SpriteBatch batch) {

        for (Pixie pixie : pixies) {
            pixie.drawHUD(batch);
        }

    }

    public void draw(SpriteBatch batch) {

        //make the selected pixie be drawed above other pixies
        temp = null;
        for(Pixie pixie : pixies){
            if(pixie.touched || pixie.touched2)
                temp = pixie;
        }
        if(temp != null){
            pixies.removeValue(temp,false);
            pixies.add(temp);
        }
        //draw pixies
        for (Pixie pixie : pixies) {
            pixie.draw(batch);
        }
    }

    public void update() {
        for (Pixie pixie : pixies) {
            pixie.update();
        }
    }

    public void remove(Pixie pixie) {
        //
        pixies.removeValue(pixie, false);
    }

    public Array<Pixie> getPixies() {
        Array<Pixie> temp = new Array<Pixie>();
        for(Pixie pixie : pixies){
            if(!pixie.status.equals("dead")){
                temp.add(pixie);
            }
        }
        return temp;
    }

    public Array<Pixie> getDeadPixies() {
        Array<Pixie> temp = new Array<Pixie>();
        for(Pixie pixie : pixies){
            if(pixie.status.equals("dead")){
                temp.add(pixie);
            }
        }
        return temp;
    }

    public void add(Pixie pixie) {
        //
        pixies.add(pixie);
    }

    public void long_input(Vector3 vec) {
        for (Pixie pixie : pixies) {
            pixie.long_input(vec);
        }
        for (Pixie pixie : pixies) {
            if (!pixie.longTouched) continue;
            x = pixie.getX() + pixie.getWidth() / 2;
            y = pixie.getY() + pixie.getHeight() / 2;

            if (vec.dst(x, y, 0) < dist) {
                dist = vec.dst(x, y, 0);
                temp = pixie;
            }
        }
        for (Pixie pixie : pixies) {
            if (pixie.equals(temp)) {
                pixie.longTouched = true;
            } else {
                pixie.longTouched = false;
            }
        }
        for (Pixie pixie : pixies) {
            if (!pixie.longTouched) pixie.canBeLongTouched = false;
        }
        temp = null;
        x = 0;
        y = 0;
        dist = 10000;
    }

    public void input(Vector3 vec, int pointer) {
        //get touched pixies
        for (Pixie pixie : pixies) {
            pixie.input(vec, pointer);

        }
        if (pointer == 0) {
            //get nearest pixie from touch position
            for (Pixie pixie : pixies) {
                if (!pixie.touched) continue;
                x = pixie.getX() + pixie.getWidth() / 2;
                y = pixie.getY() + pixie.getHeight() / 2;

                if (vec.dst(x, y, 0) < dist) {
                    dist = vec.dst(x, y, 0);
                    temp = pixie;
                }
            }
            //mark only the nearest pixie
            for (Pixie pixie : pixies) {
                if (pixie.equals(temp)) {
                    pixie.touched = true;
                } else {
                    pixie.touched = false;
                }
            }
            //make other pixies untouchable
            for (Pixie pixie : pixies) {
                if (!pixie.touched) pixie.canBeTouched = false;
            }
        } else if (pointer == 1) {
            //get nearest pixie from touch position
            for (Pixie pixie : pixies) {
                if (!pixie.touched2) continue;
                x = pixie.getX() + pixie.getWidth() / 2;
                y = pixie.getY() + pixie.getHeight() / 2;
                //Gdx.app.log("dist",""+vec.dst(x,y,0));
                if (vec.dst(x, y, 0) < dist) {
                    dist = vec.dst(x, y, 0);
                    temp = pixie;
                }
            }
            //mark only the nearest pixie
            for (Pixie pixie : pixies) {
                if (pixie.equals(temp)) {
                    pixie.touched2 = true;
                } else {
                    pixie.touched2 = false;
                }
            }
            //make other pixies untouchable
            for (Pixie pixie : pixies) {
                if (!pixie.touched2) pixie.canBeTouched2 = false;
            }
        }

        temp = null;
        x = 0;
        y = 0;
        dist = 10000;

    }

    public void setUntouched(int pointer) {
        if (pointer == 0) {

            for (Pixie pixie : pixies) {
                pixie.touched = false;
                pixie.canBeTouched = true;
                pixie.longTouched = false;
                pixie.canBeLongTouched = true;
            }
        } else if (pointer == 1) {

            for (Pixie pixie : pixies) {
                pixie.touched2 = false;
                pixie.canBeTouched2 = true;
                pixie.longTouched2 = false;
                pixie.canBeLongTouched2 = true;
            }
        }

    }


}