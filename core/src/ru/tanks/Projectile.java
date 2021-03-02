package ru.tanks;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;

public class Projectile {
    private Texture texture;
    private float x;
    private float y;
    private float vx;
    private float vy;
    private float speed;
    private final int WIDTH = 16;
    private final int HEIGHT = 16;
    private float scale;
    private boolean active;

    public Projectile(){
        scale = 1f;
        texture = new Texture("projectile.png");
        speed = 600f;
    }

    public boolean isActive(){
        return active;
    }

    public void deactivate(){
        active = false;
    }

    public void shoot(float x, float y, float angle) {
        this.x = x;
        this.y = y;
        this.vx = speed * MathUtils.cosDeg(angle);
        this.vy = speed * MathUtils.sinDeg(angle);
        this.active = true;
    }

    public void update(float delta) {
        x += vx * delta;
        y += vy * delta;
        if (x < 0 || x > Gdx.graphics.getWidth() || y < 0 || y > Gdx.graphics.getHeight()) {
            deactivate();
        }
    }

    public void draw(SpriteBatch batch) {
        batch.draw(texture, x - WIDTH / 2f, y - HEIGHT / 2f, 8, 8, WIDTH, HEIGHT,
                scale, scale, 0, 0, 0, WIDTH, HEIGHT, false, false);
    }

    public void dispose(){
        texture.dispose();
    }
}
