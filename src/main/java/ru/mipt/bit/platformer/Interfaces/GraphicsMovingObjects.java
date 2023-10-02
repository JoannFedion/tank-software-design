package ru.mipt.bit.platformer.Interfaces;

import ru.mipt.bit.platformer.ModelClasses.Tank;
import ru.mipt.bit.platformer.util.TileMovement;

public interface GraphicsMovingObjects extends GraphicsGameObjects {
    public void calculateInterpolatedScreenCoordinates(TileMovement tileMovement);
    public Tank getModelTank();
    public Action getAction();
}