package ru.geekbrains.sprite;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import ru.geekbrains.base.ScaledTouchUpButton;
import ru.geekbrains.math.Rect;
import ru.geekbrains.screen.GameScreen;


public class NewGameButton extends ScaledTouchUpButton {
    private GameScreen gameScreen;

    public NewGameButton(TextureAtlas atlas, GameScreen gameScreen) {
        super(atlas.findRegion("button_new_game"));
        setHeightProportion(0.0f);
        this.gameScreen = gameScreen;
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
//        setBottom(worldBounds.getBottom());

    }

    @Override
    public void update(float delta) {
        super.update(delta);
        if(getHeight()<0.08f){
            setTop(-0.2f);
            setHeightProportion(getHeight() + delta/20);
        }
    }

    @Override
    protected void action() {
        System.out.println("NewGameButton initNewGame");
        gameScreen.initNewGame();
        setHeightProportion(0.0f);

    }
}
