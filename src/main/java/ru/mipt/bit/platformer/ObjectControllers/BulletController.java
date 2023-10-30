package ru.mipt.bit.platformer.ObjectControllers;

import ru.mipt.bit.platformer.Action;
import ru.mipt.bit.platformer.Actions.MoveAction;
import ru.mipt.bit.platformer.GameModels.Objects.Bullet;
import ru.mipt.bit.platformer.LevelGame;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BulletController {
    private final Map<Bullet, Action> bulletActionMap;

    private final LevelGame levelGame;

    private final CollidesController collidesController;

    private List<Bullet> bulletToDelete;

    public BulletController(CollidesController collidesController, LevelGame levelGame) {
        this.collidesController = collidesController;
        bulletActionMap = new HashMap<>();
        this.levelGame = levelGame;
    }

    public void addBullet(Bullet bullet) {
        bulletActionMap.put(bullet, new MoveAction(bullet.getDirection(), collidesController));
        levelGame.add(bullet);
    }

    public void execute() {
        bulletToDelete = new ArrayList<>();
        for (Bullet bullet : bulletActionMap.keySet()) {
            if (collidesController.objectExist(bullet)) {
                bulletActionMap.get(bullet).apply(bullet);
            } else {
                bulletToDelete.add(bullet);
            }
        }
        bulletToDelete.forEach((bulletActionMap::remove));
    }

    public void deleteBullet(Bullet bullet) {
        bulletActionMap.remove(bullet);
        levelGame.delete(bullet);
    }
}
