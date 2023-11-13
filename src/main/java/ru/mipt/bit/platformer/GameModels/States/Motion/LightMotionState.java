package ru.mipt.bit.platformer.GameModels.States.Motion;

import ru.mipt.bit.platformer.Actions.Direction;
import ru.mipt.bit.platformer.CollidesController;
import ru.mipt.bit.platformer.GameModels.Objects.Bullet;
import ru.mipt.bit.platformer.GameModels.Objects.Tank;
import ru.mipt.bit.platformer.GameModels.StateMotion;
import ru.mipt.bit.platformer.LevelGame;

import static ru.mipt.bit.platformer.util.GdxGameUtils.continueProgress;

public class LightMotionState implements StateMotion {
    private final Tank tank;
    @Override
    public boolean isSuit() {
        float ratioHealth = ((float) tank.getHealth()) / tank.getMaxHealth();
        return ratioHealth > 0.7;
    }

    public LightMotionState(Tank tank) {
        this.tank = tank;
    }

    @Override
    public float updateMovementProgress(float deltaTime) {
        return continueProgress(tank.getMovementProgress(), deltaTime, tank.getMOVEMENT_SPEED());
    }


}
