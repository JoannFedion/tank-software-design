package ru.mipt.bit.platformer.Listeners;

import ru.mipt.bit.platformer.LevelListener;
import ru.mipt.bit.platformer.ModelObject;
import ru.mipt.bit.platformer.ObjectController;

import java.util.ArrayList;
import java.util.List;

public class ControllersListener implements LevelListener {

    private final List<ObjectController<?>> objectControllerList;

    public ControllersListener() {
        this.objectControllerList = new ArrayList<>();
    }

    public void addControllers(ObjectController<?> objectController){
        objectControllerList.add(objectController);
    }

    @Override
    public void onAddGameObject(ModelObject object) {
        //
    }

    @Override
    public void onDeleteGameObject(ModelObject object) {
        objectControllerList.forEach((objectController -> objectController.deleteObject(object)));
    }
}
