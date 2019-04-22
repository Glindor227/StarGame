package ru.geekbrains.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.base.BaseScreen;
import ru.geekbrains.math.Rect;
import ru.geekbrains.sprite.Background;
import ru.geekbrains.sprite.ButtonExit;
import ru.geekbrains.sprite.ButtonPlay;
import ru.geekbrains.sprite.Logo;
import ru.geekbrains.sprite.Star;

public class MenuScreen extends BaseScreen {

    private Texture bg,lg;
    private Background background;
    private Logo logo;
    private TextureAtlas atlas;
    private Star starList[];
    private ButtonExit buttonExit;
    private ButtonPlay buttonPlay;
    private Music music;
    private Game game;


    public MenuScreen(Game game) {
        this.game = game;
    }

    @Override
    public void show() {
        super.show();
        bg = new Texture("textures\\bg.png");
        lg = new Texture("badlogic.jpg");
        atlas = new TextureAtlas("textures/menuAtlas.tpack");
        background = new Background(new TextureRegion(bg));
        logo = new Logo(new TextureRegion(lg));
        starList = new Star[256];
        for (int i = 0; i < starList.length; i++) {
            starList[i] = new Star(atlas);
        }
        buttonExit = new ButtonExit(atlas);
        buttonPlay = new ButtonPlay(atlas, game);
        music = Gdx.audio.newMusic(Gdx.files.internal("sounds/music.mp3"));
        music.play();
        music.setLooping(true);
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        background.resize(worldBounds);
        logo.resize(worldBounds);
        for (Star star : starList) {
            star.resize(worldBounds);
        }
        buttonExit.resize(worldBounds);
        buttonPlay.resize(worldBounds);

    }

    @Override
    public void render(float delta) {
        super.render(delta);
        batch.begin();
        background.draw(batch);

        logo.update(delta);
        for (Star star : starList) {
            star.update(delta);
            star.draw(batch);
        }
        buttonExit.draw(batch);
        buttonPlay.draw(batch);

        logo.draw(batch);
        batch.end();
    }


    @Override
    public void dispose() {
        super.dispose();
        bg.dispose();
        lg.dispose();
        atlas.dispose();
        music.dispose();
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer) {
        buttonExit.touchDown(touch, pointer);
        buttonPlay.touchDown(touch, pointer);
        logo.touchDown(touch,pointer);
        return false;
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer) {
        buttonExit.touchUp(touch, pointer);
        buttonPlay.touchUp(touch, pointer);
        return false;
    }

}
