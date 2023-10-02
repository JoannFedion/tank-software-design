package ru.mipt.bit.platformer.Interfaces;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.GameMechanic.Direction;

public interface MovingObjects extends ModelObject{
    public boolean isMoving();
    public void rotate(Direction direction);
    public void moveTo(GridPoint2 tankTargetCoordinates);
}
