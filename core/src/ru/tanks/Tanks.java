package ru.tanks;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Tanks extends ApplicationAdapter {
	SpriteBatch batch;
	Tank tank;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		tank = new Tank();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(255, 255, 255, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		update();
		batch.begin();
		draw();
		batch.end();
	}

	private void draw(){
		tank.draw(batch);
	}

	private void update(){
		tank.update(Gdx.graphics.getDeltaTime());
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		tank.dispose();
	}
}
