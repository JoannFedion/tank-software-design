package ru.mipt.bit.platformer.GameMechanic;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.Interfaces.ModelObject;
import ru.mipt.bit.platformer.Interfaces.MovingObjects;

public class CollidesController {
    public static boolean isNotCollideTwoObjects(ModelObject obj1, MovingObjects obj2, Direction direction) {
        if (direction != null & !obj2.isMoving()) {
            GridPoint2 obj2TargetCoordinates = direction.addCoordinates(obj2.getCoordinates());
            obj2.rotate(direction);
            if (!collides(obj1.getCoordinates(), obj2TargetCoordinates)) {
                return true;
            }
            return false;
        }
        return false;
    }
    private static boolean collides(GridPoint2 object1, GridPoint2 object2) {
        return object1.equals(object2);
    }
}
