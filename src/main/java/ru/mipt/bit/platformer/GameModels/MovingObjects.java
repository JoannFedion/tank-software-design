package ru.mipt.bit.platformer.GameModels;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.Actions.Direction;
import ru.mipt.bit.platformer.ModelObject;

public interface MovingObjects extends ModelObject {
    public void moveTo(Direction direction, Boolean isPossibleToMove);
    public Direction getDirection();
}
