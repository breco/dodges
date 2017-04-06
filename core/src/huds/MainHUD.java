package huds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.breco.dodges.MainGame;

import items.Item;
import items.Items;
import pixies.Pixie;
import pixies.Pixies;

/**
 * Created by victor on 3/25/17.
 */
public class MainHUD {
    Pixies pixies;
    Items items;
    private int cont;
    private Texture green,red,yellow,gray,curr;
    private Texture cross,black,itemSlot;
    public MainHUD(Pixies pixies,Items items){
        this.pixies = pixies;
        this.items = items;
        green = new Texture(Gdx.files.internal("huds/green.png"));
        red = new Texture(Gdx.files.internal("huds/red.png"));
        yellow = new Texture(Gdx.files.internal("huds/yellow.png"));
        gray = new Texture(Gdx.files.internal("huds/gray.png"));
        cross = new Texture(Gdx.files.internal("huds/cross.png"));
        black = new Texture(Gdx.files.internal("huds/black.png"));
        itemSlot = new Texture(Gdx.files.internal("huds/item_slot.png"));
        int i=0;
        for(Item item : items.getItems()){
            item.setPosition(MainGame.WIDTH/10.5f+i*MainGame.WIDTH/3,MainGame.HEIGHT/24);
            i++;
        }
    }
    public void update(){

    }
    public void draw(SpriteBatch batch){

        cont = 0;
        //draw status bars
        for(Pixie pixie: pixies.getPixies()){
            //batch.draw(pixie.getTexture(), 10 + cont * MainGame.WIDTH / 3, MainGame.HEIGHT - 70, 64, 64);
            //if(pixie.status.equals("dead")) batch.draw(cross,10+cont*MainGame.WIDTH/3,MainGame.HEIGHT-70,64,64);
            if(pixie.COLOR_HP == 'G'){
                curr = green;
            }
            else if(pixie.COLOR_HP == 'Y'){
                curr = yellow;
            }
            else if(pixie.COLOR_HP == 'R'){
                curr = red;
            }
            //batch.draw(gray,10+cont*MainGame.WIDTH/3+pixie.getTexture().getWidth()*2+10, MainGame.HEIGHT-50,140,10);
            //batch.draw(curr,10+cont*MainGame.WIDTH/3+pixie.getTexture().getWidth()*2+10, MainGame.HEIGHT-50,pixie.PERCENT_HP*140/100,10);


            cont++;
        }
        //draw item section
        batch.draw(black,0,0,MainGame.WIDTH,MainGame.HEIGHT/6);

        for(int i =0;i<3;i++){
            batch.draw(itemSlot,MainGame.WIDTH/36+i*MainGame.WIDTH/3,MainGame.HEIGHT/256,200,200);
            batch.draw(items.getItems().get(i).getTexture(),MainGame.WIDTH/10.5f+i*MainGame.WIDTH/3,MainGame.HEIGHT/24,100,100);
            if(items.getItems().get(i).used) batch.draw(cross,MainGame.WIDTH/10.5f+i*MainGame.WIDTH/3,MainGame.HEIGHT/24,100,100);
            //items.getItems().get(i).draw(batch);
        }
    }
}
