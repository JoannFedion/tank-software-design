package ru.mipt.bit.platformer.GameModels;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.Actions.Direction;
import ru.mipt.bit.platformer.ModelObject;

public interface MovingObjects extends ModelObject {
    public boolean isMoving();
    public void rotate(Direction direction);
    public void moveTo(GridPoint2 targetCoordinates);


    public Direction getDirection();
}
