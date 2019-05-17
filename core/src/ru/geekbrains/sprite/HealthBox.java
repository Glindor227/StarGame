package ru.geekbrains.sprite;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.base.Sprite;
import ru.geekbrains.math.Rect;

public class HealthBox extends Sprite {

    private Rect worldBounds;
    private Vector2 v = new Vector2();
    private int health;

    public HealthBox(TextureRegion textureRegion) {
        super(textureRegion);
    }

    public void set(
            Vector2 pos0,
            Vector2 v0,
            float height,
            Rect worldBounds,
            int health
    ) {
        this.pos.set(pos0);
        this.v.set(v0);
        setHeightProportion(height);
        this.worldBounds = worldBounds;
        this.health = health;
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        setBottom(worldBounds.getHalfHeight());
    }

    @Override
    public void update(float delta) {
        pos.mulAdd(v, delta);
        if (isOutside(worldBounds)) {
            destroy();
        }
    }

    public int getHealth() {
        return health;
    }

}
