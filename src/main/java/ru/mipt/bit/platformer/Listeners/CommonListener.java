package ru.mipt.bit.platformer.Listeners;

import ru.mipt.bit.platformer.ObjectControllers.CollidesController;
import ru.mipt.bit.platformer.ModelObject;
import ru.mipt.bit.platformer.LevelListener;

import java.util.ArrayList;
import java.util.List;

public class CommonListener implements LevelListener {
    private final List<LevelListener> levelListenerList;

    private final CollidesController collidesController;

    public CommonListener(CollidesController collidesController) {
        this.collidesController = collidesController;
        levelListenerList = new ArrayList<>();
    }

    public void addListener(LevelListener levelListener){
        levelListenerList.add(levelListener);
    }
    @Override
    public void onAddGameObject(ModelObject object) {
        if (isPossibleAddObject(object)){
            levelListenerList.forEach((levelListener -> levelListener.onAddGameObject(object)));
        }
    }

    @Override
    public void onDeleteGameObject(ModelObject object) {
        levelListenerList.forEach((levelListener -> levelListener.onDeleteGameObject(object)));
    }

    private boolean isPossibleAddObject(ModelObject object) {
        return collidesController.isCellFree(object.getCoordinates().x, object.getCoordinates().y);
    }

}
