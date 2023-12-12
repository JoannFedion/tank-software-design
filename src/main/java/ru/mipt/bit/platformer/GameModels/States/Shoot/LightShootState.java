package ru.mipt.bit.platformer.GameModels.States.Shoot;

import ru.mipt.bit.platformer.Actions.Direction;
import ru.mipt.bit.platformer.CollidesController;
import ru.mipt.bit.platformer.GameModels.Objects.Bullet;
import ru.mipt.bit.platformer.GameModels.Objects.Tank;
import ru.mipt.bit.platformer.GameModels.StateShoot;
import ru.mipt.bit.platformer.LevelGame;

public class LightShootState implements StateShoot {
    private final Tank tank;

    public LightShootState(Tank tank) {
        this.tank = tank;
    }

    @Override
    public boolean isSuit() {
        float ratioHealth = ((float) tank.getHealth()) / tank.getMaxHealth();
        return ratioHealth >= BOUND_HEALTH;
    }

    @Override
    public Bullet shoot(CollidesController collidesController, LevelGame levelGame) {
        Direction direction = tank.getDirection();
        int BULLET_DAMAGE = 1;
        return new Bullet(direction,
                direction.addCoordinates(tank.getCoordinates()),
                BULLET_DAMAGE,
                collidesController,
                levelGame);
    }
}
