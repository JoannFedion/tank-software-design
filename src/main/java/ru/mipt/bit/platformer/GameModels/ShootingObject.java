package ru.mipt.bit.platformer.GameModels;

import ru.mipt.bit.platformer.Actions.Direction;
import ru.mipt.bit.platformer.ModelObject;

public interface ShootingObject extends ModelObject {

    public Direction getDirection();

}
