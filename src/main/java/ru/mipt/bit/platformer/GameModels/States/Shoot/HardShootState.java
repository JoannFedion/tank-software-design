package ru.mipt.bit.platformer.GameModels.States.Shoot;

import ru.mipt.bit.platformer.CollidesController;
import ru.mipt.bit.platformer.GameModels.Objects.Bullet;
import ru.mipt.bit.platformer.GameModels.Objects.Tank;
import ru.mipt.bit.platformer.GameModels.StateShoot;
import ru.mipt.bit.platformer.LevelGame;

public class HardShootState implements StateShoot {
    private final Tank tank;

    public HardShootState(Tank tank) {
        this.tank = tank;
    }

    @Override
    public boolean isSuit() {
        float ratioHealth = ((float) tank.getHealth()) / tank.getMaxHealth();
        return ratioHealth < BOUND_HEALTH;
    }

    @Override
    public Bullet shoot(CollidesController collidesController, LevelGame levelGame) {
        return null;
    }
}
