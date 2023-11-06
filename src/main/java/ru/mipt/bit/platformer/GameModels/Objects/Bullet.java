package ru.mipt.bit.platformer.GameModels.Objects;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.Actions.Direction;
import ru.mipt.bit.platformer.GameModels.DamageModel;
import ru.mipt.bit.platformer.LevelGame;
import ru.mipt.bit.platformer.ModelObject;
import ru.mipt.bit.platformer.CollidesController;

import static com.badlogic.gdx.math.MathUtils.isEqual;
import static ru.mipt.bit.platformer.util.GdxGameUtils.continueProgress;

public class Bullet implements ModelObject, DamageModel {

    private final Direction direction;
    private float movementProgress;
    private GridPoint2 destinationCoordinates;
    private GridPoint2 coordinates;
    private static final float MOVEMENT_SPEED = 0.4f;
    private static final float MOVEMENT_COMPLETED = 1f;
    private static final int MOVEMENT_STARTED = 0;
    private final int damage;
    private int health;
    private final CollidesController collidesController;
    private final LevelGame levelGame;

    public Bullet(Direction direction, GridPoint2 coordinates, int damage, CollidesController collidesController, LevelGame levelGame) {
        this.collidesController = collidesController;
        this.levelGame = levelGame;
        this.movementProgress = MOVEMENT_COMPLETED;
        this.direction = direction;
        this.destinationCoordinates = coordinates;
        this.coordinates = coordinates;
        this.health = 1;
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

    public Direction getDirection() {
        return direction;
    }

    private boolean isMoving() {
        return !isEqual(movementProgress, MOVEMENT_COMPLETED);
    }

    private void moveTo(GridPoint2 bulletTargetCoordinates) {
        if (!isMoving()) {
            destinationCoordinates = bulletTargetCoordinates;
            movementProgress = MOVEMENT_STARTED;
        }
    }

    @Override
    public void updateState(float deltaTime) {

        if (!isMoving()) {
            if (collidesController.objectDontCollideWithSomeElse(this, direction)) {
                moveTo(direction.addCoordinates(coordinates));
            } else {
                ModelObject objectWithCollide = collidesController.getObjectWithCoordinate(direction.addCoordinates(coordinates));
                if (objectWithCollide instanceof DamageModel) {
                    ((DamageModel) objectWithCollide).getDamage(this);
                }
                levelGame.delete(this);
                return;
            }
        }

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

    @Override
    public int getHealth() {
        return health;
    }
}
