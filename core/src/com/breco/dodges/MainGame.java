package com.breco.dodges;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import screens.WorldScreen;
import utils.MyGestures;


public class MainGame extends Game {
	public SpriteBatch batch;
	public SpriteBatch hudBatch;
	public static int WIDTH, HEIGHT;
	public static float ASPECT_RATIO;
	public static InputMultiplexer mux;
	public static Preferences prefs;
	@Override
	public void create () {
		batch = new SpriteBatch();
		hudBatch = new SpriteBatch();
		MyGestures ges = new MyGestures();
		mux = new InputMultiplexer();
		mux.addProcessor(ges);
		mux.addProcessor(ges.gd);
		Gdx.input.setInputProcessor(mux);
		WIDTH = Gdx.graphics.getWidth();
		HEIGHT = Gdx.graphics.getHeight();
		ASPECT_RATIO = (float)WIDTH/(float)HEIGHT;
        prefs = Gdx.app.getPreferences("My Preferences");
		Gdx.app.log("SIZE", "WIDTH:" + WIDTH + "HEIGHT: " + HEIGHT);
		//this.setScreen(new GameScreen(this));
		this.setScreen(new WorldScreen(this));
		//this.setScreen(new UITestScreen(this));

	}

	@Override
	public void render () {
		super.render();
		MyGestures.update();
	}
	public void dispose(){
		batch.dispose();
		hudBatch.dispose();
	}
	public static void setInputProcessor(){
		Gdx.input.setInputProcessor(mux);
	}
}
