package ru.mipt.bit.platformer.GameModels.States.Motion;

import ru.mipt.bit.platformer.GameModels.Objects.Tank;
import ru.mipt.bit.platformer.GameModels.StateMotion;

import static ru.mipt.bit.platformer.util.GdxGameUtils.continueProgress;

public class HardMotionState implements StateMotion {
    @Override
    public boolean isSuit() {
        float ratioHealth = ((float) tank.getHealth()) / tank.getMaxHealth();
        return ratioHealth < LOWER_BOUND_HEALTH;
    }

    private final Tank tank;
    public HardMotionState(Tank tank) {
        this.tank = tank;
    }

    @Override
    public float updateMovementProgress(float deltaTime) {
        return continueProgress(tank.getMovementProgress(), deltaTime, tank.getMOVEMENT_SPEED() * 3);
    }
}
