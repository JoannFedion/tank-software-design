package ru.mipt.bit.platformer.Actions;

import ru.mipt.bit.platformer.Action;
import ru.mipt.bit.platformer.CollidesController;
import ru.mipt.bit.platformer.ModelObject;
import ru.mipt.bit.platformer.GameModels.ShootingObject;

public class ShootAction implements Action {

    private final CollidesController collidesController;

    public ShootAction(CollidesController collidesController) {
        this.collidesController = collidesController;
    }

    @Override
    public void apply(ModelObject object) {
        if (object instanceof ShootingObject) {
            ShootingObject shootingObject = (ShootingObject) object;
            if (isPossibleToApplyAction(shootingObject)) {
                shootingObject.shoot(collidesController);
            }
        }
    }

    private boolean isPossibleToApplyAction(ShootingObject object) {
        int bullet_coord_x = object.getDirection().addCoordinates(object.getCoordinates()).x;
        int bullet_coord_y = object.getDirection().addCoordinates(object.getCoordinates()).y;
        return collidesController.isCellFree(bullet_coord_x, bullet_coord_y);
    }
}
