package ru.geekbrains.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.base.BaseScreen;
import ru.geekbrains.math.Rect;
import ru.geekbrains.math.Rnd;
import ru.geekbrains.pool.BulletPool;
import ru.geekbrains.pool.EnemyPool;
import ru.geekbrains.sprite.Background;
import ru.geekbrains.sprite.Bullet;
import ru.geekbrains.sprite.EnemyShip;
import ru.geekbrains.sprite.PlayerShip;
import ru.geekbrains.sprite.Star;
import ru.geekbrains.utils.Regions;

public class GameScreen extends BaseScreen {
    private Texture bg;
    private Background background;
    private TextureAtlas atlasMenu,atlasMain;
    private Star starList[];
    private PlayerShip playerShip;
    private BulletPool bulletPool;
    private Sound sound;

    private EnemyPool enemyPool;
    private float enemyShipInterval=2f;
    private float enemyShipTimer=enemyShipInterval;


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
        bulletPool = new BulletPool();
        enemyPool = new EnemyPool();

        sound = Gdx.audio.newSound(Gdx.files.internal("sounds/bullet.wav"));
        playerShip = new PlayerShip(atlasMain,bulletPool,sound);

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
        bulletPool.updateActiveSprites(delta);
        bulletPool.freeAllDestroyedActiveSprites();
        bulletPool.drawActiveSprites(batch);

        enemyShipTimer+=delta;
        if (enemyShipTimer>=enemyShipInterval){
            EnemyShip eShip = enemyPool.obtain();
            eShip.set(Regions.split(
                    atlasMain.findRegion("enemy2"),1,2,2),
                    new Vector2(Rnd.nextFloat(worldBounds.getLeft(),worldBounds.getRight()),worldBounds.getTop()),
//                    new Vector2(0,worldBounds.getTop()),
                    new Vector2(0,-0.1f),
                    0.10f,
                    worldBounds);
            enemyShipTimer=0;
        }
        enemyPool.updateActiveSprites(delta);
        enemyPool.freeAllDestroyedActiveSprites();
        enemyPool.drawActiveSprites(batch);

        batch.end();
    }
    @Override

    public void dispose() {
        super.dispose();
        bg.dispose();
        atlasMain.dispose();
        atlasMenu.dispose();
        bulletPool.dispose();
        sound.dispose();
    }
    @Override
    public boolean touchDown(Vector2 touch, int pointer) {
        playerShip.touchDown(touch,pointer);
        return false;
    }

    @Override
    public boolean keyDown(int keycode) {
        playerShip.keyDown(keycode);
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        playerShip.keyUp(keycode);
        return false;
    }

}
