package ru.mipt.bit.platformer.GameMechanic;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.Interfaces.Action;
import ru.mipt.bit.platformer.Interfaces.ModelObject;
import ru.mipt.bit.platformer.Interfaces.MovingObjects;

import java.util.List;

public enum Direction implements Action {
    UP(new GridPoint2(0, 1), 90),
    DOWN(new GridPoint2(0, -1), -90),
    LEFT(new GridPoint2(-1, 0), -180),
    RIGHT(new GridPoint2(1, 0), 0);

    private final GridPoint2 vector;
    private final float rotation;

    Direction(GridPoint2 vector, float rotation) {
        this.vector = vector;
        this.rotation = rotation;
    }

    @Override
    public void apply(ModelObject object, float deltaTime) {
        MovingObjects movingObjects = (MovingObjects) object;
        movingObjects.moveTo(addCoordinates(movingObjects.getCoordinates()));
    }

    @Override
    public boolean isPossibleDoAction(ModelObject object, List<ModelObject> listObjectsInGame) {
        boolean isApplyToObject = true;
        MovingObjects movingObject = (MovingObjects) object;
        for (ModelObject obj : listObjectsInGame) {
            isApplyToObject = CollidesController.isNotCollideTwoObjects(obj, movingObject, this);
            if (!isApplyToObject) return false;
        }
        return isApplyToObject;
    }

    public GridPoint2 addCoordinates(GridPoint2 point) {
        return point.cpy().add(vector);
    }

    public float getRotation() {
        return rotation;
    }
}

