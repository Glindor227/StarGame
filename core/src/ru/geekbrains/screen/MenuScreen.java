package ru.geekbrains.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
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

class MenuScreen_OLD extends BaseScreen {

    private Vector2 touch,tauchTemp;
    private Vector2 pos;
    private Vector2 v;
    private Texture img;
    private Texture bg_smoll,bg_big;
    private boolean isTauchActive = false;

    // отрисовка фона - ПЗ к первому уроку.
    // 1) Укладывем фон мозайкой по всей поверхности
    // 2) фон передаем из-вне, чтобы риовать разный(движется спрайт или стоит)
    private void DrowBackgraud(Texture localBG) {
        int pointX=0;
        while (pointX<Gdx.graphics.getWidth()){
            int pointY=0;
            while (pointY<Gdx.graphics.getHeight()){
                batch.draw(localBG, pointX, pointY);
                pointY = pointY + localBG.getHeight();
            }
            pointX = pointX + localBG.getWidth();
        }
    }

    // туда двигаться нельзя?
    private boolean stopMove()
    {
        // достигли достигли точки назначания
        if(isTauchActive){
            tauchTemp.set(touch);
            if(tauchTemp.sub(pos).len()<1.0f)
                return true;
        }

        // упремся в правый или верхний край.
        if ((pos.y + img.getHeight()> Gdx.graphics.getHeight())||(pos.x + img.getWidth() > Gdx.graphics.getWidth())) {
            return true;
        }
        // упрёмся в левый или нижний край.
        if ((pos.y <0)||(pos.x<0)){
            return true;
        }

        return false;
    }

    @Override
    public void show() {
        super.show();
        bg_smoll = new Texture("bg2.png");
        bg_big = new Texture("bg.png");
        touch = new Vector2();
        tauchTemp = new Vector2();
        pos = new Vector2();
        v = new Vector2(0,0);
        img = new Texture("badlogic.jpg");
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        pos.add(v);
        batch.begin();
        if (stopMove()) {
            pos.sub(v);
            v.setZero();
        }

        DrowBackgraud(v.len()==0 ? bg_big:bg_smoll);

        batch.draw(img, pos.x, pos.y);
        batch.end();
    }

    @Override
    public void dispose() {
        super.dispose();
        img.dispose();
        bg_smoll.dispose();
        bg_big.dispose();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        isTauchActive=true;
        touch.set(screenX, Gdx.graphics.getHeight() - screenY);
        //нормализуем к единичной скорости
        v.set(touch.cpy().sub(pos)).nor();
//        System.out.println("touchDown touch.x = " + touch.x + " touch.y = " + touch.y);
        System.out.println("pos=" + pos +" v=" + v +" touch=" + touch );
        return false;
    }

    @Override
    public boolean keyDown(int keycode) {
        isTauchActive = false;
        switch (keycode){
            case 19:v.set(0,1);break;
            case 20:v.set(0,-1);break;
            case 21:v.set(-1,0);break;
            case 22:v.set(1,0);break;
            default:v.setZero();
        }
        return super.keyDown(keycode);
    }
}
