package enemies;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;


public class Enemies {
    public Array<Enemy> enemies;
    public Array<Enemy> onScreenEnemies;
    public Array<Enemy> deadEnemies;
    public Enemies() {
        enemies = new Array<Enemy>();
        onScreenEnemies = new Array<Enemy>();
        deadEnemies = new Array<Enemy>();

    }
    public void draw(SpriteBatch batch){
        for(Enemy enemy : onScreenEnemies){
            enemy.draw(batch);
        }
    }
    public void update(){

        for(Enemy enemy : enemies){
            if(enemy.onScreen() && !onScreenEnemies.contains(enemy,false) && !deadEnemies.contains(enemy,false)){
                onScreenEnemies.add(enemy);
            }
        }
        for(Enemy enemy : onScreenEnemies){
            enemy.update();
        }
    }
    public void remove(Enemy enemy){
        onScreenEnemies.removeValue(enemy, false);
        deadEnemies.add(enemy);
    }
    public Array<Enemy> getEnemies(){
        return onScreenEnemies;
    }
    public void add(Enemy enemy){
        enemies.add(enemy);
    }
}
