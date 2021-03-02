package ru.tanks;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;

public class Tank {

    private Texture texture;
    Projectile projectile;

    private float x;
    private float y;
    private float speed;
    private float angle;
    private float scale;
    private final int WIDTH = 40;
    private final int HEIGHT = 40;

    private Weapon weapon;

    public Tank(){
        texture = new Texture("tank.png");
        weapon = new Weapon();
        x = 100f;
        y = 100f;
        speed = 240f;
        scale = 1f;
    }

    private void moveX(float delta, boolean isForward){
        if (x > WIDTH * scale && x < Gdx.graphics.getWidth() - WIDTH * scale){
            if (isForward) {
                x += speed * MathUtils.cosDeg(angle) * delta;
            } else {
                x -= speed * MathUtils.cosDeg(angle) * delta * 0.2f;
            }
        } else if (x < WIDTH * scale){
            x = WIDTH * scale + 1;
        } else if (x > Gdx.graphics.getWidth() - WIDTH * scale){
            x = Gdx.graphics.getWidth() - WIDTH * scale - 1;
        }
        if (y > HEIGHT * scale && y < Gdx.graphics.getHeight() - HEIGHT * scale){
            if (isForward) {
                y += speed * MathUtils.sinDeg(angle) * delta;
            } else {
                y -= speed * MathUtils.sinDeg(angle) * delta * 0.2f;
            }
        } else if (y < HEIGHT * scale) {
            y = WIDTH * scale + 1;
        } else if (y > Gdx.graphics.getHeight() - HEIGHT * scale){
            y = Gdx.graphics.getHeight() - HEIGHT * scale - 1;
        }
    }

    public void update(float delta){
        weapon.update(delta);
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            moveX(delta, true);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            moveX(delta, false);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            angle -= 90.0f * delta;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            angle += 90.0f * delta;
        }
    }

    private class Weapon{

        private Texture texture;
        private float angle;
        private float x;
        private float y;

        public Weapon(){
            texture = new Texture("weapon.png");
        }

        public void dispose(){
            texture.dispose();
        }

        public void update(float delta){
            x = Tank.this.x;
            y = Tank.this.y;
            if (projectile != null && projectile.isActive()){
                projectile.update(delta);
            }
            if (Gdx.input.isKeyPressed(Input.Keys.E)){
                angle -= 90.0f * delta;
            }
            if (Gdx.input.isKeyPressed(Input.Keys.Q)){
                angle += 90.0f * delta;
            }
            if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
                if (projectile == null || !projectile.isActive()) {
                    shoot();
                }
            }
        }

        public void draw(SpriteBatch batch){
            batch.draw(texture, x - WIDTH / 2f, y - HEIGHT / 2f, 20, 20, WIDTH, HEIGHT,
                    scale, scale, Tank.this.getAngle() + angle, 0, 0, WIDTH,
                    HEIGHT, false, false);
        }

        public float getAngle(){
            return angle;
        }
    }

    public void shoot(){
        projectile = new Projectile();
        projectile.shoot(x, y, angle + weapon.getAngle());
    }

    public float getAngle(){
        return angle;
    }

    public void draw(SpriteBatch batch){
        batch.draw(texture, x - WIDTH / 2f, y - HEIGHT / 2f, 20, 20, WIDTH, HEIGHT,
                scale, scale, angle, 0, 0, WIDTH, HEIGHT, false, false);
        if (projectile != null && projectile.isActive()){
            projectile.draw(batch);
        }
        weapon.draw(batch);
    }

    public void dispose(){
        projectile.dispose();
        weapon.dispose();
        texture.dispose();
    }

}
