package ru.geekbrains.sprite;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.base.Sprite;
import ru.geekbrains.math.Rect;

public class PlayerShip extends Sprite {

    private Vector2 v;
    private Rect worldBounds;

    public PlayerShip(TextureAtlas atlas) {
        super(atlas.findRegion("main_ship"));
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
        if((worldBounds.getRight()-getRight()<0.01f)&&(v.x>0))
            v.setZero();
        if((getLeft()-worldBounds.getLeft()<0.01f)&&(v.x<0))
            v.setZero();
        pos.mulAdd(v,delta);
    }
    @Override
    public boolean touchDown(Vector2 touch, int pointer) {
        v.set(touch.cpy().sub(pos).x,0);
        System.out.println("v - "+v);
        return false;

    }

}
