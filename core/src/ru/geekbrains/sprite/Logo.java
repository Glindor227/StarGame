package ru.geekbrains.sprite;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.base.Sprite;
import ru.geekbrains.math.Rect;

public class Logo extends Sprite {
    private Vector2 speed;
    private Vector2 distTouch,disTouchTEMP;
    private final float pointDelta = 0.002f;


    public Logo(TextureRegion region) {
        super(region);
        speed = new Vector2(0,0);
        distTouch = new Vector2(0,0);
        disTouchTEMP = new Vector2(0,0);


    }

    @Override
    public void update(float delta) {
        disTouchTEMP.set(distTouch);
        if(disTouchTEMP.sub(pos).len()>pointDelta)
            pos.add(speed);
        else {
            pos.set(distTouch);
            speed.setZero();
        }

    }


    @Override
    public boolean touchDown(Vector2 touch, int pointer) {
        //нормализуем к единичной скорости
        distTouch.set(touch);
        speed.set(touch.cpy().sub(pos)).setLength(pointDelta);
        System.out.println("Logo speed "+speed);
        return false;

    }

    @Override
    public void resize(Rect worldBounds) {
        System.out.println("Logo resize "+worldBounds);
        setHeightProportion(worldBounds.getHeight()/10);
        pos.set(worldBounds.pos);
    }

}
