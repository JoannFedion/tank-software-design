package ru.mipt.bit.platformer.GameModels.States.Motion;

import ru.mipt.bit.platformer.GameModels.Objects.Tank;
import ru.mipt.bit.platformer.GameModels.StateMotion;

import static ru.mipt.bit.platformer.util.GdxGameUtils.continueProgress;

public class MediumMotionState implements StateMotion {
    private final Tank tank;
    public MediumMotionState(Tank tank) {
        this.tank = tank;
    }
    @Override
    public boolean isSuit() {
        float ratioHealth = ((float) tank.getHealth()) / tank.getMaxHealth();
        return ratioHealth >= LOWER_BOUND_HEALTH && ratioHealth <= UPPER_BOUND_HEALTH;
    }

    @Override
    public float updateMovementProgress(float deltaTime) {
        return continueProgress(tank.getMovementProgress(), deltaTime, tank.getMOVEMENT_SPEED() * 2);
    }
}
