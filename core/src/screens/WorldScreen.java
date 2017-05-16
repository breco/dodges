package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.breco.dodges.MainGame;

import planets.Planet;
import planets.Planets;
import utils.MyGestures;

/**
 * Created by victor on 5/9/17.
 */
public class WorldScreen implements Screen {
    OrthographicCamera cam;
    public Vector3 vec;
    MainGame game;
    public enum State{
        PAUSE,
        RUN,
        RESUME,

    }
    private State state = State.RUN;

    Sprite bg;

    public Planets planets;
    public WorldScreen(MainGame game){
        this.game = game;
        cam = new OrthographicCamera();
        cam = new OrthographicCamera(game.WIDTH/2, game.HEIGHT/2);

        bg = new Sprite(new Texture(Gdx.files.internal("backgrounds/space.png")));
        bg.setPosition(-game.WIDTH/2,-game.HEIGHT/2);
        bg.setSize(game.WIDTH, game.HEIGHT);

        vec = new Vector3();

        planets = new Planets();
        planets.add(new Planet(new Texture(Gdx.files.internal("planets/fire_planet.png")),100,100,"1-1"));
        planets.add(new Planet(new Texture(Gdx.files.internal("planets/water_planet.png")),0,-100,"2-1"));
        planets.add(new Planet(new Texture(Gdx.files.internal("planets/grass_planet.png")),-150,5,"3-1"));

    }
    public void input(){
        if(MyGestures.isTouchDown()) {
            vec.set(MyGestures.firstTouch);
            cam.unproject(vec);
            planets.input(vec);
        }
    }

    public void update(){
        planets.update();
        if(planets.planetTouched()){
            game.prefs.putString("loadLevel",planets.getTouched().level);
            game.prefs.flush();
            game.setScreen(new GameScreen(game));
            dispose();
        }
    }

    public void draw(SpriteBatch batch){

        bg.draw(batch);
        planets.draw(batch);
    }

    public void drawHUD(SpriteBatch batch){

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
