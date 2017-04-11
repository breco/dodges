package items;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.breco.dodges.MainGame;


public class Items {
    public Array<Item> items;
    public Rectangle rect;
    public Items() {
        items = new Array<Item>();
        rect = new Rectangle();
        rect.setSize(100,100);

    }
    public void draw(SpriteBatch batch){
        for(Item item  : items){
            item.draw(batch);
        }
    }
    public void update(){
        for(Item item : items){
            item.update();
        }
    }
    public void remove(Item item){
        items.removeValue(item, false);
    }
    public Array<Item> getItems(){
        return items;
    }
    public void add(Item item){
        items.add(item);
    }

    public void input(Vector3 vec){
        vec.set(vec.x, MainGame.HEIGHT - vec.y, 0);

        for(int i = 0;i<3;i++){
            if(items.get(i).used) continue;
            rect.setPosition(MainGame.WIDTH / 10.5f + i * MainGame.WIDTH / 3, MainGame.HEIGHT / 24);
            if(rect.contains(vec.x,vec.y)){

                if(items.get(i).type.equals("auto")){
                    //
                    items.get(i).effect();
                }
                if(items.get(i).type.equals("select")){
                    items.get(i).touched = true;
                    items.get(i).setInitPosition();
                }
            }
        }
    }
    public void touchUp(){
        for(Item item : items){
            if(item.touched) item.effect();
        }
    }
    public void setUntouched(){
        for(Item item : items){
            if(item.touched) item.resetPosition();
            item.touched = false;
        }
    }
}
