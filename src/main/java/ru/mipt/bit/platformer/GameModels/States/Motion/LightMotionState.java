package ru.mipt.bit.platformer.GameModels.States.Motion;

import ru.mipt.bit.platformer.GameModels.Objects.Tank;
import ru.mipt.bit.platformer.GameModels.StateMotion;

import static ru.mipt.bit.platformer.util.GdxGameUtils.continueProgress;

public class LightMotionState implements StateMotion {
    private final Tank tank;
    @Override
    public boolean isSuit() {
        float ratioHealth = ((float) tank.getHealth()) / tank.getMaxHealth();
        return ratioHealth > UPPER_BOUND_HEALTH;
    }

    public LightMotionState(Tank tank) {
        this.tank = tank;
    }

    @Override
    public float updateMovementProgress(float deltaTime) {
        return continueProgress(tank.getMovementProgress(), deltaTime, tank.getMOVEMENT_SPEED());
    }


}
