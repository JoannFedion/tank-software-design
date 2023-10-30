package ru.mipt.bit.platformer.Listeners;

import ru.mipt.bit.platformer.ObjectControllers.CollidesController;
import ru.mipt.bit.platformer.ModelObject;
import ru.mipt.bit.platformer.LevelListener;

public class CollidesControllerListener implements LevelListener {

    private final CollidesController collidesController;

    public CollidesControllerListener(CollidesController collidesController) {
        this.collidesController = collidesController;
    }

    @Override
    public void onDeleteGameObject(ModelObject object) {
        collidesController.update();
    }

    @Override
    public void onAddGameObject(ModelObject object) {
        collidesController.makeBusyCell(object);
    }
}
