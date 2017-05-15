package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Json;
import com.breco.dodges.MainGame;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import backgrounds.Background;
import bullets.Bullets;
import enemies.Enemies;
import enemies.Enemy;
import huds.MainHUD;
import huds.PauseHUD;
import items.Fruit;
import items.Items;
import items.MagicMirror;
import items.SpeedStar;
import pixies.Agni;
import pixies.Aqua;
import pixies.Pixies;
import pixies.Tera;
import utils.MyGestures;
import utils.TimeManager;


public class GameScreen implements Screen {
    //GAME STATES
    public enum State{
        PAUSE,
        RUN,
        RESUME,
        STOPPED
    }
    private State state = State.RUN;

    //INPUT AND CAMERA
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
    PauseHUD pausehud;





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
        try {
            loadLevel();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        //pos x, pos y, move to, HP, ATK, TIME
        /*int test = MainGame.HEIGHT/2;
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
        enemies.add(new Bigbat(new Texture(Gdx.files.internal("enemies/bat.png")),0,test,200,5,70));*/
        bullets = new Bullets();
        items = new Items();
        items.add(new MagicMirror(new Texture(Gdx.files.internal("items/mirror.png"))));
        items.add(new SpeedStar(new Texture(Gdx.files.internal("items/star.png"))));
        items.add(new Fruit(new Texture(Gdx.files.internal("items/grapes.png"))));


        bg = new Background(new Texture(Gdx.files.internal("backgrounds/bg4.png")));
        time = new TimeManager();




        hud = new MainHUD(this);
        pausehud = new PauseHUD(this);
        time.start();
    }
    public static class My_Test{
        public String clase;
        public int posx,posy,time;
    }


    public void loadLevel() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Json json = new Json();
        ArrayList<My_Test> test = json.fromJson(ArrayList.class, My_Test.class, Gdx.files.internal("levels/1-1.json"));

        for(My_Test t : test){
            Class<?> clazz = Class.forName(t.clase);
            Constructor<?> ctor = clazz.getConstructor(int.class,int.class,int.class);
            enemies.add((Enemy) ctor.newInstance(t.posx,MainGame.HEIGHT/t.posy,t.time));
        }


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
        else{
            if(MyGestures.isTouchDown()){
                vec.set(MyGestures.firstTouch);
                if(vec.y >= 5*MainGame.HEIGHT/6){
                    items.input(vec,0);
                    return;
                }

                cam.unproject(vec);
                pixies.input(vec, 0);
                //HUD INPUT
                vec.set(MyGestures.firstTouch);

                hud.input(vec);

            }
            if(MyGestures.isTouchDown2()){
                vec.set(MyGestures.firstTouch2);
                if(vec.y >= 5*MainGame.HEIGHT/6){
                    items.input(vec,1);
                    return;
                }
                cam.unproject(vec);
                pixies.input(vec,1);
                //HUD INPUT
                vec.set(MyGestures.firstTouch);
            }
        }

        if(!MyGestures.isTouchDragged()){
            MyGestures.resetDiff(0);
        }

        if(!MyGestures.isTouchDragged2()){
            MyGestures.resetDiff(1);
        }

        if(MyGestures.isTouchUp()){

            pixies.setUntouched(0);
            items.touchUp(0);
            items.setUntouched(0);
        }
        if(MyGestures.isTouchUp2()){
            pixies.setUntouched(1);
            items.touchUp(1);
            items.setUntouched(1);
        }


    }
    public void pauseInput(){
        vec.set(MyGestures.firstTouch);
        pausehud.input(vec);
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
        if(state == State.PAUSE)
            pausehud.draw(batch);

    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        //Gdx.app.log("FPS",""+Gdx.graphics.getFramesPerSecond());

        // 1)Clear the screen
        Gdx.gl.glClearColor(255, 255, 255, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        switch(state){
            case RUN:
                // 2)Input handling
                input();
                // 3)Update system
                // 3.1)---> Update Cam
                cam.update();
                game.batch.setProjectionMatrix(cam.combined);
                // 3.2)---> Update Game
                update();
                break;
            case PAUSE:
                pauseInput();
                break;
            case RESUME:
                break;

        }
        // 4)Draw
        game.batch.begin();
        draw(game.batch);
        game.batch.end();
        game.hudBatch.begin();
        drawHUD(game.hudBatch);
        game.hudBatch.end();

    }

    @Override
    public void resize(int width, int height) {


    }

    @Override
    public void pause() {
        state = State.PAUSE;
        time.pause();
    }

    @Override
    public void resume() {
        state = State.RUN;
        time.unpause();
    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
