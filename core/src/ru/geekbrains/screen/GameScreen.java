package ru.geekbrains.screen;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.base.BaseScreen;
import ru.geekbrains.math.Rect;
import ru.geekbrains.sprite.Background;
import ru.geekbrains.sprite.PlayerShip;
import ru.geekbrains.sprite.Star;

public class GameScreen extends BaseScreen {
    private Texture bg;
    private Background background;
    private TextureAtlas atlasMenu,atlasMain;
    private Star starList[];
    private PlayerShip playerShip;

    public void show() {
        super.show();
        bg = new Texture("textures\\bg.png");
        atlasMenu = new TextureAtlas("textures/menuAtlas.tpack");
        atlasMain = new TextureAtlas("textures/mainAtlas.tpack");
        background = new Background(new TextureRegion(bg));
        starList = new Star[256];
        for (int i = 0; i < starList.length; i++) {
            starList[i] = new Star(atlasMenu);
        }
        playerShip = new PlayerShip(atlasMain);

    }
    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        background.resize(worldBounds);
        playerShip.resize(worldBounds);
        for (Star star : starList) {
            star.resize(worldBounds);
        }
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        batch.begin();
        background.draw(batch);
        for (Star star : starList) {
            star.update(delta);
            star.draw(batch);
        }
        playerShip.update(delta);
        playerShip.draw(batch);

        batch.end();
    }
    @Override

    public void dispose() {
        super.dispose();
        bg.dispose();
        atlasMain.dispose();
        atlasMenu.dispose();

    }
    @Override
    public boolean touchDown(Vector2 touch, int pointer) {
        playerShip.touchDown(touch,pointer);
        return false;
    }


}
