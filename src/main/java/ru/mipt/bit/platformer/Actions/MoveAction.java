package ru.mipt.bit.platformer.Actions;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.Action;
import ru.mipt.bit.platformer.ObjectControllers.CollidesController;
import ru.mipt.bit.platformer.ModelObject;
import ru.mipt.bit.platformer.GameModels.MovingObjects;

public class MoveAction implements Action {
    private final Direction direction;
    private final CollidesController collidesController;

    public MoveAction(Direction direction, CollidesController collidesController) {
        this.direction = direction;
        this.collidesController = collidesController;
    }

    @Override
    public void apply(ModelObject object) {
        MovingObjects movingObjects = (MovingObjects) object;
        if (!movingObjects.isMoving()) {
            movingObjects.rotate(direction);
            if (isPossibleToApplyAction(direction, movingObjects)) {
                GridPoint2 newCoordinates = direction.addCoordinates(movingObjects.getCoordinates());
                movingObjects.moveTo(newCoordinates);
                collidesController.makeBusyCell(object);
            }
        }
    }

    private boolean isPossibleToApplyAction(Direction direction, ModelObject object) {
        boolean isApplyToObject;
        isApplyToObject = collidesController.objectDontCollideWithSomeElse(object, direction);
        return isApplyToObject;
    }
}