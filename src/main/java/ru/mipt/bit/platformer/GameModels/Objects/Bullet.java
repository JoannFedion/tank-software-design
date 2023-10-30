package ru.mipt.bit.platformer.GameModels.Objects;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.Actions.Direction;
import ru.mipt.bit.platformer.GameModels.DamageModel;
import ru.mipt.bit.platformer.GameModels.MovingObjects;

import static com.badlogic.gdx.math.MathUtils.isEqual;
import static ru.mipt.bit.platformer.util.GdxGameUtils.continueProgress;

public class Bullet implements MovingObjects, DamageModel {

    private Direction direction;
    private float movementProgress;
    private GridPoint2 destinationCoordinates;
    private GridPoint2 coordinates;
    private static final float MOVEMENT_SPEED = 0.4f;
    public static final float MOVEMENT_COMPLETED = 1f;
    public static final int MOVEMENT_STARTED = 0;
    private final int damage;
    private int health;

    public Bullet(Direction direction, GridPoint2 coordinates, int damage) {
        this.movementProgress = MOVEMENT_COMPLETED;
        this.direction = direction;
        this.destinationCoordinates = coordinates;
        this.coordinates = coordinates;
        this.health = 0;
        this.damage = damage;
    }

    @Override
    public GridPoint2 getDestinationCoordinates() {
        return destinationCoordinates;
    }

    @Override
    public GridPoint2 getCoordinates() {
        return coordinates;
    }


    @Override
    public void rotate(Direction direction) {
        //
    }

    @Override
    public Direction getDirection() {
        return direction;
    }

    @Override
    public boolean isMoving() {
        return !isEqual(movementProgress, MOVEMENT_COMPLETED);
    }
    @Override
    public void moveTo(GridPoint2 bulletTargetCoordinates) {
        if (!isMoving()) {
            destinationCoordinates = bulletTargetCoordinates;
            movementProgress = MOVEMENT_STARTED;
        }
    }

    @Override
    public void updateState(float deltaTime) {
        movementProgress = continueProgress(movementProgress, deltaTime, MOVEMENT_SPEED);
        if (isEqual(movementProgress, MOVEMENT_COMPLETED)) {
            // record that the player has reached his/her destination
            coordinates = destinationCoordinates;
        }
    }

    public float getMovementProgress() {
        return movementProgress;
    }

    @Override
    public void getDamage(DamageModel damageModel) {
        health -= damageModel.takeDamage();
    }

    @Override
    public int takeDamage() {
        return damage;
    }

    @Override
    public boolean isAlive() {
        return health > 0;
    }
}
