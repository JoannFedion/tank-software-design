package ru.mipt.bit.platformer.ModelClasses;

import ru.mipt.bit.platformer.Interfaces.Action;
import ru.mipt.bit.platformer.Interfaces.ModelObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LevelGame {

    private List<ModelObject> objectsInGameList;

    public LevelGame() {
        this.objectsInGameList = new ArrayList<>();
    }

    public void add(ModelObject obj) {
        objectsInGameList.add(obj);
    }

    public void add(ModelObject... objects) {
        objectsInGameList.addAll(Arrays.asList(objects));
    }

    public void update(float deltaTime) {
        for (ModelObject obj : objectsInGameList) {
            {
                obj.updateState(deltaTime);
            }
        }
    }

    public void apply(Action action, ModelObject object, float deltaTime) {
        if (action.isPossibleDoAction(object, objectsInGameList))
            action.apply(object, deltaTime);
    }
}
