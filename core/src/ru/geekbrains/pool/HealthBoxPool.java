package ru.geekbrains.pool;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import ru.geekbrains.base.SpritesPool;
import ru.geekbrains.sprite.Bullet;
import ru.geekbrains.sprite.HealthBox;


public class HealthBoxPool extends SpritesPool<HealthBox> {
    TextureRegion textureRegion;
    public HealthBoxPool(TextureRegion textureRegion) {
        this.textureRegion = textureRegion;
    }

    @Override
    protected HealthBox newObject() {
        return new HealthBox(textureRegion);
    }
}