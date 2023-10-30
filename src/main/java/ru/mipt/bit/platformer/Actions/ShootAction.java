package ru.mipt.bit.platformer.Actions;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.Action;
import ru.mipt.bit.platformer.ObjectControllers.CollidesController;
import ru.mipt.bit.platformer.GameModels.Objects.Bullet;
import ru.mipt.bit.platformer.ModelObject;
import ru.mipt.bit.platformer.GameModels.ShootingObject;
import ru.mipt.bit.platformer.LevelListener;

public class ShootAction implements Action {

    private final LevelListener listener;
    private final CollidesController collidesController;

    private final int BULLET_DAMAGE = 1;

    public ShootAction(CollidesController collidesController,
                       LevelListener shootListener){
        this.collidesController = collidesController;
        this.listener = shootListener;
    }

    @Override
    public void apply(ModelObject object) {
        ShootingObject shootingObject = (ShootingObject) object;
        if (isPossibleToApplyAction(shootingObject)) {
            Bullet bullet = createBullet(shootingObject);
            listener.onAddGameObject(bullet);
        }
    }

    private Bullet createBullet(ShootingObject object) {
        Direction direction = object.getDirection();
        GridPoint2 coordinates = direction.addCoordinates(object.getCoordinates());
        return new Bullet(direction, coordinates, BULLET_DAMAGE);
    }

    private boolean isPossibleToApplyAction(ShootingObject object) {
        int bullet_coord_x = object.getDirection().addCoordinates(object.getCoordinates()).x;
        int bullet_coord_y = object.getDirection().addCoordinates(object.getCoordinates()).y;
        return collidesController.isCellFree(bullet_coord_x, bullet_coord_y);
    }
}
