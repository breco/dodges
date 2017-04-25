package huds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.breco.dodges.MainGame;

import buttons.My_Button;
import items.Item;
import items.Items;
import pixies.Pixies;
import screens.GameScreen;

/**
 * Created by victor on 3/25/17.
 */
public class MainHUD {
    Pixies pixies;
    Items items;
    GameScreen game;

    private Texture cross,black,itemSlot;
    private My_Button pauseButton;
    public MainHUD(GameScreen game){
        this.game = game;
        this.pixies = game.pixies;
        this.items = game.items;


        cross = new Texture(Gdx.files.internal("huds/cross.png"));
        black = new Texture(Gdx.files.internal("huds/black.png"));
        itemSlot = new Texture(Gdx.files.internal("huds/item_slot.png"));
        int i=0;
        for(Item item : items.getItems()){
            item.setPosition(MainGame.WIDTH/10.5f+i*MainGame.WIDTH/3,MainGame.HEIGHT/24);
            i++;
        }
        pauseButton = new My_Button(new Texture(Gdx.files.internal("buttons/pause_button.png")));
        pauseButton.scale(0.6f);
        pauseButton.setPosition(MainGame.WIDTH-pauseButton.getWidth()*1.1f,MainGame.HEIGHT-pauseButton.getHeight()*1.1f);
    }
    public void update(){

    }
    public void draw(SpriteBatch batch){



        //draw item section
        batch.draw(black,0,0,MainGame.WIDTH,MainGame.HEIGHT/6);

        for(int i =0;i<3;i++){
            batch.draw(itemSlot,MainGame.WIDTH/36+i*MainGame.WIDTH/3,MainGame.HEIGHT/256,200,200);
            batch.draw(items.getItems().get(i).getTexture(),MainGame.WIDTH/10.5f+i*MainGame.WIDTH/3,MainGame.HEIGHT/24,100,100);
            if(items.getItems().get(i).used) batch.draw(cross,MainGame.WIDTH/10.5f+i*MainGame.WIDTH/3,MainGame.HEIGHT/24,100,100);
            //items.getItems().get(i).draw(batch);
        }
        for(Item item : items.getItems()){
            if(item.touched || item.touched2) item.draw(batch);
        }
        pauseButton.draw(batch);
    }
    public void input(Vector3 vec){
        vec.set(vec.x, MainGame.HEIGHT - vec.y, 0);
        if(pauseButton.getBoundingRectangle().contains(vec.x,vec.y)){
            game.pause();
        }

    }
}
