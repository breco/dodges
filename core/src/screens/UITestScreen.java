package screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.breco.dodges.MainGame;

/**
 * Created by victor on 7/16/17.
 */

public class UITestScreen implements Screen {

    private Stage stage;
    private Table table;
    public UITestScreen(MainGame game2){
        stage = new Stage();

        Gdx.input.setInputProcessor(stage);
        table = new Table();
        table.setFillParent(true);

        TextureRegion upRegion = new TextureRegion(new Texture(Gdx.files.internal("pixies/agni.png")));
        TextureRegion downRegion = new TextureRegion(new Texture(Gdx.files.internal("pixies/aqua.png")));
        BitmapFont buttonFont = new BitmapFont();

        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
        style.up = new TextureRegionDrawable(upRegion);
        style.down = new TextureRegionDrawable(downRegion);
        style.font = buttonFont;

        final MainGame game = game2;

        TextButton button1 = new TextButton("BOTON 1 AYUDA", style);
        button1.addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("ASD","SADVASFA");
                game.setScreen( new WorldScreen(game));
            };
        }
        );
        table.add(button1);

        TextButton button2 = new TextButton("OTRO BOTON AYUDA", style);
        table.add(button2);

        stage.addActor(table);

        table.setDebug(true);

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
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
        stage.dispose();
    }
}
