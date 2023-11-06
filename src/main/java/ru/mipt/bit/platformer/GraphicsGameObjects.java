package ru.mipt.bit.platformer;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import ru.mipt.bit.platformer.util.TileMovement;

public interface GraphicsGameObjects {
    public void dispose();
    public void render(Batch batch);
    public void calculateInterpolatedScreenCoordinates(TileMovement tileMovement);
    public Rectangle getRectangle();
}
