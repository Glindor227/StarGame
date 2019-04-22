package ru.geekbrains.sprite;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.base.Sprite;
import ru.geekbrains.math.Rect;
import ru.geekbrains.pool.BulletPool;

public class PlayerShip extends Sprite {

    private Vector2 v;
    private Rect worldBounds;

    private BulletPool bulletPool;

    private TextureRegion bulletRegion;
    private Vector2 bulletV = new Vector2(0f, 0.5f);


    private boolean pressedRight;
    private boolean pressedLeft;

    private float reloadInterval=0.25f;
    private float reloadTimer=0;



    public PlayerShip(TextureAtlas atlas, BulletPool bulletPool) {
        super(atlas.findRegion("main_ship"), 1, 2, 2);
        this.bulletPool = bulletPool;
        this.bulletRegion = atlas.findRegion("bulletMainShip");


        v = new Vector2();
    }

    @Override
    public void resize(Rect worldBounds) {
        setHeightProportion(worldBounds.getHeight()/10);
        this.worldBounds = worldBounds;
        setBottom(worldBounds.getBottom() + 0.02f);
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        reloadTimer+=delta;
        if (reloadTimer>=reloadInterval){
            shoot();
            reloadTimer=0;
        }
        if((worldBounds.getRight()-getRight()<0.01f)&&(v.x>0))
            v.setZero();
        if((getLeft()-worldBounds.getLeft()<0.01f)&&(v.x<0))
            v.setZero();
        pos.mulAdd(v,delta);
    }


    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.A:
            case Input.Keys.LEFT:
                pressedLeft = true;
                moveLeft();
                break;
            case Input.Keys.D:
            case Input.Keys.RIGHT:
                pressedRight = true;
                moveRight();
                break;
            case Input.Keys.UP:
                shoot();
                break;
        }
        return false;
    }

    public boolean keyUp(int keycode) {
        switch (keycode) {
            case Input.Keys.A:
            case Input.Keys.LEFT:
                pressedLeft = false;
                if (pressedRight) {
                    moveRight();
                } else {
                    stop();
                }
                break;
            case Input.Keys.D:
            case Input.Keys.RIGHT:
                pressedRight = false;
                if (pressedLeft) {
                    moveLeft();
                } else {
                    stop();
                }
                break;
        }
        return false;
    }

    //todo надо предусмотреть работу с мультитачем
    @Override
    public boolean touchDown(Vector2 touch, int pointer) {
        // чем дальше от корабля ткнули тем быстрее полетит в сторону тача
        v.set(touch.cpy().sub(pos).x,0);
        System.out.println("v - "+v);
        return false;

    }


    public void shoot() {
        Bullet bullet = bulletPool.obtain();
        bullet.set(this, bulletRegion, pos, bulletV, 0.025f, worldBounds, 1);
    }

    private void moveRight() {
        v.set(0.2f,0);
    }

    private void moveLeft() {
        v.set(-0.2f,0);
    }

    private void stop() {
        v.setZero();
    }

}
