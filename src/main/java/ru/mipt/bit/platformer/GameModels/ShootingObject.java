package ru.mipt.bit.platformer.GameModels;

import ru.mipt.bit.platformer.Actions.Direction;
import ru.mipt.bit.platformer.ModelObject;
import ru.mipt.bit.platformer.CollidesController;

public interface ShootingObject extends ModelObject {

    public Direction getDirection();

    public void shoot(CollidesController collidesController);
}
