package ru.mipt.bit.platformer.GameModels.States.Motion;

import ru.mipt.bit.platformer.CollidesController;
import ru.mipt.bit.platformer.GameModels.Objects.Bullet;
import ru.mipt.bit.platformer.GameModels.Objects.Tank;
import ru.mipt.bit.platformer.GameModels.StateMotion;
import ru.mipt.bit.platformer.LevelGame;

import static ru.mipt.bit.platformer.util.GdxGameUtils.continueProgress;

public class HardMotionState implements StateMotion {
    @Override
    public boolean isSuit() {
        float ratioHealth = ((float) tank.getHealth()) / tank.getMaxHealth();
        return ratioHealth < 0.15;
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
