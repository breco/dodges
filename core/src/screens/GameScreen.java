package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.breco.dodges.MainGame;

import backgrounds.Background;
import bullets.Bullets;
import enemies.Bat;
import enemies.Bigbat;
import enemies.Enemies;
import huds.MainHUD;
import items.Fruit;
import items.Items;
import pixies.Agni;
import pixies.Aqua;
import pixies.Pixies;
import pixies.Tera;
import utils.MyGestures;
import utils.TimeManager;


public class GameScreen implements Screen {
    private MainGame game;
    public static OrthographicCamera cam;
    public Vector3 vec;

    //GAME LOGIC OBJECTS
    public static Pixies pixies;
    public static Enemies enemies;
    public static Bullets bullets;
    public static Items items;

    //HELPERS/UTILS

    public static TimeManager time;
    //TEST

    public Background bg;


    //HUD OBJECTS

    MainHUD hud;

    public GameScreen(MainGame game){

        this.game = game;
        cam = new OrthographicCamera(game.WIDTH, game.HEIGHT);

        vec = new Vector3();

        //cam.translate(0, 300, 0);



        pixies = new Pixies();
        //pos x, pos y, HP, ATK, SPD
        pixies.add(new Aqua(0,-250,30,2,30));
        pixies.add(new Agni(150,-250,30,2,30));
        pixies.add(new Tera(-150,-250,30,2,30));
        enemies = new Enemies();
        //pos x, pos y, move to, HP, ATK, TIME
        int test = MainGame.HEIGHT/2;
        int bat_hp = 14;
        enemies.add(new Bat(new Texture(Gdx.files.internal("enemies/bat.png")),-50,test,'L',bat_hp,4,1));
        enemies.add(new Bat(new Texture(Gdx.files.internal("enemies/bat.png")),0,test,'R',bat_hp,4,5));
        enemies.add(new Bat(new Texture(Gdx.files.internal("enemies/bat.png")),50,test,'R',bat_hp,4,10));
        enemies.add(new Bat(new Texture(Gdx.files.internal("enemies/bat.png")),0,test,'R',bat_hp,4,11));
        enemies.add(new Bat(new Texture(Gdx.files.internal("enemies/bat.png")),-50,test,'R',bat_hp,4,15));
        enemies.add(new Bat(new Texture(Gdx.files.internal("enemies/bat.png")),0,test,'L',bat_hp,4,15));
        enemies.add(new Bat(new Texture(Gdx.files.internal("enemies/bat.png")),50,test,'L',bat_hp,4,20));
        enemies.add(new Bat(new Texture(Gdx.files.internal("enemies/bat.png")),-50,test,'R',bat_hp,4,25));
        enemies.add(new Bat(new Texture(Gdx.files.internal("enemies/bat.png")),0,test,'L',bat_hp,4,30));
        enemies.add(new Bat(new Texture(Gdx.files.internal("enemies/bat.png")),50,test,'L',bat_hp,4,30));
        enemies.add(new Bat(new Texture(Gdx.files.internal("enemies/bat.png")),-50,test,'R',bat_hp,4,35));
        enemies.add(new Bat(new Texture(Gdx.files.internal("enemies/bat.png")),0,test,'R',bat_hp,4,40));
        enemies.add(new Bat(new Texture(Gdx.files.internal("enemies/bat.png")),-50,test,'R',bat_hp,4,40));
        enemies.add(new Bat(new Texture(Gdx.files.internal("enemies/bat.png")),0,test,'L',bat_hp,4,45));
        enemies.add(new Bat(new Texture(Gdx.files.internal("enemies/bat.png")),50,test,'L',bat_hp,4,50));
        enemies.add(new Bat(new Texture(Gdx.files.internal("enemies/bat.png")),-50,test,'R',bat_hp,4,55));
        enemies.add(new Bat(new Texture(Gdx.files.internal("enemies/bat.png")),0,test,'R',bat_hp,4,55));
        enemies.add(new Bat(new Texture(Gdx.files.internal("enemies/bat.png")),50,test,'L',bat_hp,4,60));
        enemies.add(new Bat(new Texture(Gdx.files.internal("enemies/bat.png")),-50,test,'R',bat_hp,4,60));
        enemies.add(new Bat(new Texture(Gdx.files.internal("enemies/bat.png")),0,test,'L',bat_hp,4,65));
        enemies.add(new Bigbat(new Texture(Gdx.files.internal("enemies/bat.png")),0,test,200,5,70));
        bullets = new Bullets();
        items = new Items();
        items.add(new Fruit(new Texture(Gdx.files.internal("items/orangefruit.png"))));
        items.add(new Fruit(new Texture(Gdx.files.internal("items/tuna.png"))));
        items.add(new Fruit(new Texture(Gdx.files.internal("items/grapes.png"))));


        bg = new Background(new Texture(Gdx.files.internal("backgrounds/bg4.png")));
        time = new TimeManager();




        hud = new MainHUD(pixies,items);
        time.start();
    }

    public void update(){



        bg.update();
        pixies.update();
        enemies.update();
        bullets.update();
        items.update();

    }
    public void input(){
        if(MyGestures.isLongPress()){
            vec.set(MyGestures.firstTouch);
            cam.unproject(vec);
            pixies.long_input(vec);
        }
        else if(MyGestures.isTouchDown()){
            vec.set(MyGestures.firstTouch);
            //ITEMS
            if(vec.y >= 5*MainGame.HEIGHT/6){
                items.input(vec);
                return;
            }
            cam.unproject(vec);
            pixies.input(vec);
        }
        if(MyGestures.isTouchDragged()){

        }
        else{
            MyGestures.resetDiff();
        }
        if(MyGestures.isTouchUp()){
            pixies.setUntouched();
            items.touchUp();
            items.setUntouched();
        }


    }

    public void draw(SpriteBatch batch){


        bg.draw(batch);
        enemies.draw(batch);
        pixies.draw(batch);
        bullets.draw(batch);
        //HUD that depends on this cam/batch positions
        pixies.drawHUD(batch);
    }
    public void drawHUD(SpriteBatch batch) {
        hud.draw(batch);

    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        //System.out.println(Gdx.graphics.getFramesPerSecond());
        // 1)Clear the screen
        Gdx.gl.glClearColor(255, 255, 255, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // 2)Input handling
        input();

        // 3)Update system

        // 3.1)---> Update Cam
        cam.update();
        game.batch.setProjectionMatrix(cam.combined);

        // 4)Draw
        game.batch.begin();
        draw(game.batch);
        game.batch.end();
        game.hudBatch.begin();
        drawHUD(game.hudBatch);
        game.hudBatch.end();


        // 3.2)---> Update Game

        update();
    }

    @Override
    public void resize(int width, int height) {
        //viewport.update(width,height);

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
