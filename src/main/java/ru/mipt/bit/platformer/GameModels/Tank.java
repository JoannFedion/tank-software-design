package ru.mipt.bit.platformer.GameModels;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.Actions.Direction;

import static com.badlogic.gdx.math.MathUtils.isEqual;
import static ru.mipt.bit.platformer.util.GdxGameUtils.continueProgress;

public class Tank implements MovingObjects {
    private static final float MOVEMENT_SPEED = 0.4f;
    public static final float MOVEMENT_COMPLETED = 1f;
    public static final int MOVEMENT_STARTED = 0;

    private float movementProgress;
    private GridPoint2 coordinates;
    private GridPoint2 destinationCoordinates;
    private Direction direction;


    public Tank(GridPoint2 coordinates, Direction direction) {
        this.movementProgress = MOVEMENT_COMPLETED;
        this.coordinates = coordinates;
        this.destinationCoordinates = coordinates;
        this.direction = direction;
    }

    public GridPoint2 getCoordinates() {
        return coordinates;
    }
    @Override
    public GridPoint2 getDestinationCoordinates() {
        return destinationCoordinates;
    }
    @Override
    public boolean isMoving() {
        return !isEqual(movementProgress, MOVEMENT_COMPLETED);
    }
    @Override
    public void moveTo(GridPoint2 tankTargetCoordinates) {
        if (!isMoving()) {
            destinationCoordinates = tankTargetCoordinates;
            movementProgress = MOVEMENT_STARTED;
        }
    }
    @Override
    public void rotate(Direction direction) {
        this.direction = direction;
    }
    @Override
    public void updateState(float deltaTime) {
        movementProgress = continueProgress(movementProgress, deltaTime, MOVEMENT_SPEED);
        if (isEqual(movementProgress, MOVEMENT_COMPLETED)) {
            // record that the player has reached his/her destination
            coordinates = destinationCoordinates;
        }
    }
    public Direction getDirection() {
        return direction;
    }

    public float getMovementProgress() {
        return movementProgress;
    }

}
