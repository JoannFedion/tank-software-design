package ru.mipt.bit.platformer.GameModels.Objects;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.Actions.Direction;
import ru.mipt.bit.platformer.GameModels.DamageModel;
import ru.mipt.bit.platformer.GameModels.MovingObjects;
import ru.mipt.bit.platformer.GameModels.ShootingObject;
import ru.mipt.bit.platformer.LevelGame;
import ru.mipt.bit.platformer.CollidesController;

import static com.badlogic.gdx.math.MathUtils.isEqual;
import static ru.mipt.bit.platformer.util.GdxGameUtils.continueProgress;

public class Tank implements MovingObjects, ShootingObject, DamageModel {
    private static final float MOVEMENT_SPEED = 0.4f;
    private static final float MOVEMENT_COMPLETED = 1f;
    private static final int MOVEMENT_STARTED = 0;
    private static final int BULLET_DAMAGE = 1;


    private float movementProgress;
    private GridPoint2 coordinates;
    private GridPoint2 destinationCoordinates;
    private Direction direction;
    private int health;
    private int damage;
    private final LevelGame levelGame;


    public Tank(GridPoint2 coordinates, Direction direction, int tankHealth, LevelGame levelGame) {
        this.levelGame = levelGame;
        this.movementProgress = MOVEMENT_COMPLETED;
        this.coordinates = coordinates;
        this.destinationCoordinates = coordinates;
        this.direction = direction;
        this.health = tankHealth;
    }

    @Override
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
        if (!isAlive()){
            levelGame.delete(this);
            return;
        }
        movementProgress = continueProgress(movementProgress, deltaTime, MOVEMENT_SPEED);
        if (isEqual(movementProgress, MOVEMENT_COMPLETED)) {
            // record that the player has reached his/her destination
            coordinates = destinationCoordinates;
        }
    }
    @Override
    public Direction getDirection() {
        return direction;
    }

    public float getMovementProgress() {
        return movementProgress;
    }

    @Override
    public void getDamage(DamageModel damageModel) {
        this.health -= damageModel.takeDamage();
    }

    @Override
    public int takeDamage() {
        return 0;
    }

    @Override
    public boolean isAlive() {
        return health > 0;
    }

    @Override
    public void shoot(CollidesController collidesController) {
        if (isPossiblyToShoot()){
            Bullet bullet = new Bullet(direction, direction.addCoordinates(coordinates), BULLET_DAMAGE, collidesController, levelGame);
            levelGame.add(bullet);
        }
    }

    @Override
    public int getHealth() {
        return health;
    }

    private boolean isPossiblyToShoot(){
        return true;
    }
}
