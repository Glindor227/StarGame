package ru.geekbrains.sprite;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import ru.geekbrains.base.ScaledTouchUpButton;
import ru.geekbrains.math.Rect;
import ru.geekbrains.screen.GameScreen;

public class GameOverButton extends ScaledTouchUpButton {
    public GameOverButton(TextureAtlas atlas, GameScreen gameScreen) {
        super(atlas.findRegion("message_game_over"));
        setHeightProportion(0.1f);
        setTop(0.3f);
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
//        setTop(worldBounds.getTop());
    }

    @Override
    public void update(float delta) {
/*        super.update(delta);
        if(getHeight()<0.1f){
            setTop(0.3f);
            setHeightProportion(getHeight() + delta/10);
        }
*/

    }

    @Override
    protected void action() {

    }
}