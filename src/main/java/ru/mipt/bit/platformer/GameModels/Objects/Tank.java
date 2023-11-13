package ru.mipt.bit.platformer.GameModels.Objects;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.Actions.Direction;
import ru.mipt.bit.platformer.GameModels.*;
import ru.mipt.bit.platformer.GameModels.States.Motion.HardMotionState;
import ru.mipt.bit.platformer.GameModels.States.Motion.LightMotionState;
import ru.mipt.bit.platformer.GameModels.States.Motion.MediumMotionState;
import ru.mipt.bit.platformer.GameModels.States.Shoot.HardShootState;
import ru.mipt.bit.platformer.GameModels.States.Shoot.LightShootState;
import ru.mipt.bit.platformer.LevelGame;
import ru.mipt.bit.platformer.CollidesController;

import java.util.List;
import java.util.Optional;

import static com.badlogic.gdx.math.MathUtils.isEqual;
import static ru.mipt.bit.platformer.util.GdxGameUtils.continueProgress;

public class Tank implements MovingObjects, ShootingObject, DamageModel {
    private static final float MOVEMENT_SPEED = 0.4f;
    private static final float MOVEMENT_COMPLETED = 1f;
    private static final int MOVEMENT_STARTED = 0;

    private final List<StateMotion> stateMotionList;
    private final List<StateShoot> stateShootList;
    private StateMotion stateMotion;
    private StateShoot stateShoot;
    private float movementProgress;
    private GridPoint2 coordinates;
    private GridPoint2 destinationCoordinates;
    private Direction direction;
    private final int maxHealth;
    private int health;
    private int damage;
    private final LevelGame levelGame;

    public Tank(GridPoint2 coordinates, Direction direction, int tankHealth, LevelGame levelGame) {
        this.levelGame = levelGame;
        this.movementProgress = MOVEMENT_COMPLETED;
        this.coordinates = coordinates;
        this.destinationCoordinates = coordinates;
        this.direction = direction;
        this.maxHealth = tankHealth;
        this.health = tankHealth;

        this.stateMotionList = List.of(
                new LightMotionState(this),
                new MediumMotionState(this),
                new HardMotionState(this)
        );
        this.stateShootList = List.of(
                new LightShootState(this),
                new HardShootState(this)
        );

        setStates();
    }

    @Override
    public GridPoint2 getCoordinates() {
        return coordinates;
    }

    @Override
    public GridPoint2 getDestinationCoordinates() {
        return destinationCoordinates;
    }

    private boolean isMoving() {
        return !isEqual(movementProgress, MOVEMENT_COMPLETED);
    }
    @Override
    public void moveTo(Direction direction, Boolean isPossibleToMove) {
        if (!isMoving()) {
            this.direction = direction;
            if (isPossibleToMove) {
                destinationCoordinates = direction.addCoordinates(coordinates);
                movementProgress = MOVEMENT_STARTED;
            }
        }
    }

    @Override
    public void updateState(float deltaTime) {
        if (!isAlive()) {
            levelGame.delete(this);
            return;
        }
        movementProgress = stateMotion.updateMovementProgress(deltaTime);
        if (isEqual(movementProgress, MOVEMENT_COMPLETED)) {
            // record that the player has reached his/her destination
            coordinates = destinationCoordinates;
        }

        setStates();
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
            Bullet bullet = stateShoot.shoot(collidesController, levelGame);
            if (!(bullet == null)) {
                levelGame.add(bullet);
            }
    }

    @Override
    public int getHealth() {
        return health;
    }
    public float getMOVEMENT_SPEED() {
        return MOVEMENT_SPEED;
    }
    private void setStates() {
        stateShoot = setStateTank(stateShootList);
        stateMotion = setStateTank(stateMotionList);
    }
    private<T extends State> T setStateTank(List<T> stateList){
        Optional<T> optionalStateTank = stateList.stream()
                .filter(State::isSuit)
                .findFirst();
        return optionalStateTank.orElse(null);
    }

    public float getMaxHealth() {
        return maxHealth;
    }
}
