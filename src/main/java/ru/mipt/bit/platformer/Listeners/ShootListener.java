package ru.mipt.bit.platformer.Listeners;

import ru.mipt.bit.platformer.ObjectControllers.BulletController;
import ru.mipt.bit.platformer.ModelObject;
import ru.mipt.bit.platformer.GameModels.Objects.Bullet;
import ru.mipt.bit.platformer.LevelListener;

public class ShootListener implements LevelListener {

    private final BulletController bulletController;
    public ShootListener(BulletController bulletController) {
        this.bulletController = bulletController;
    }

    @Override
    public void onDeleteGameObject(ModelObject object) {
        bulletController.deleteBullet((Bullet) object);
    }
    @Override
    public void onAddGameObject(ModelObject object) {
        bulletController.addBullet((Bullet) object);
    }

}
