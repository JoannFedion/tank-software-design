package ru.mipt.bit.platformer.GameModels;

import ru.mipt.bit.platformer.CollidesController;
import ru.mipt.bit.platformer.GameModels.Objects.Bullet;
import ru.mipt.bit.platformer.LevelGame;

public interface StateShoot extends State {
    float BOUND_HEALTH = 0.15f;
    Bullet shoot(CollidesController collidesController, LevelGame levelGame);
}
