package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.breco.dodges.MainGame;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import backgrounds.Background;
import bullets.Bullets;
import enemies.Enemies;
import enemies.Enemy;
import huds.LoseHUD;
import huds.MainHUD;
import huds.PauseHUD;
import huds.WinHUD;
import items.Fruit;
import items.Items;
import items.MagicMirror;
import items.SpeedStar;
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
        STOPPED,
        WIN,
        LOSE
    }
    private State state = State.RUN;

    //INPUT AND CAMERA
    public MainGame game;
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
    WinHUD winhud;
    LoseHUD losehud;

    //MUSIC AND SOUND EFFECTS

    Music ost;

    public GameScreen(MainGame game){

        this.game = game;
        cam = new OrthographicCamera(game.WIDTH, game.HEIGHT);

        vec = new Vector3();

        //cam.translate(0, 300, 0);



        pixies = new Pixies();
        //pos x, pos y, HP, ATK, SPD
        pixies.add(new Aqua(0,-250,30,2,30));
        pixies.add(new Tera(150,-250,30,2,30));
        pixies.add(new Aqua(-150,-250,30,2,30));

        enemies = new Enemies();

        //LOAD LEVEL
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

        bullets = new Bullets();
        items = new Items();
        items.add(new MagicMirror(new Texture(Gdx.files.internal("items/mirror.png"))));
        items.add(new SpeedStar(new Texture(Gdx.files.internal("items/star.png"))));
        items.add(new Fruit(new Texture(Gdx.files.internal("items/grapes.png"))));

        time = new TimeManager();

        hud = new MainHUD(this);
        pausehud = new PauseHUD(this);
        winhud = new WinHUD(game);
        losehud = new LoseHUD(game);
        time.start();

        ost = Gdx.audio.newMusic(Gdx.files.internal("music/02.wav"));
        ost.setLooping(true);
        ost.play();
    }

    public static class JsonEnemy {
        public String clase;
        public int posx,posy,time;
    }

    public void loadLevel() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        JsonReader reader = new JsonReader();
        Json json = new Json();
        JsonValue base = reader.parse(Gdx.files.internal("levels/"+game.prefs.getString("loadLevel")+".json"));

        JsonValue enemigos = base.get("enemies");
        JsonEnemy t;
        int i = 0;
        while(enemigos.get(i) != null){
            t = json.fromJson(JsonEnemy.class, enemigos.get(i).toString());
            Class<?> clazz = Class.forName(t.clase);
            Constructor<?> ctor = clazz.getConstructor(int.class,int.class,int.class);
            enemies.add((Enemy) ctor.newInstance(t.posx,MainGame.HEIGHT/t.posy,t.time));
            i++;
        }



        bg = new Background(new Texture(Gdx.files.internal("backgrounds/"+base.getString("background"))));
    }

    public void gameConditions(){
        if(enemies.enemies.size == enemies.deadEnemies.size + enemies.escapedEnemies.size){
            //Gdx.app.log("CONDITIONS","WIN");
            ost.stop();
            winhud.sound();
            state = State.WIN;
        }
        if(pixies.getDeadPixies().size == 3){
            ost.stop();
            losehud.sound();
            state = State.LOSE;
        }
    }

    public void update(){
        gameConditions();
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
        if(MyGestures.isTouchDown()) {
            vec.set(MyGestures.firstTouch);
            pausehud.input(vec);
        }
    }
    public void winInput(){
        if(MyGestures.isTouchDown()) {
            vec.set(MyGestures.firstTouch);
            winhud.input(vec);
        }
    }
    public void loseInput(){
        if(MyGestures.isTouchDown()) {
            vec.set(MyGestures.firstTouch);
            losehud.input(vec);
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
        if(state == State.PAUSE)
            pausehud.draw(batch);
        if(state == State.WIN){
            winhud.draw(batch);
        }
        if(state == State.LOSE){
            losehud.draw(batch);
        }

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
                pixies.pause();
                break;
            case RESUME:
                pixies.unpause();
                break;
            case WIN:
                winInput();
                pixies.pause();
                break;
            case LOSE:
                loseInput();
                pixies.pause();
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
        pixies.unpause();

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        ost.stop();
        ost.dispose();
        pixies.dispose();
    }
}
