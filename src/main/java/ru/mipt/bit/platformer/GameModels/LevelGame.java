package ru.mipt.bit.platformer.GameModels;

import java.util.List;

public class LevelGame {

    private List<ModelObject> objectsInGameList;

    public LevelGame(List<ModelObject> listObjects) {
        this.objectsInGameList = listObjects;
    }

    public List<ModelObject> getObjectsInGameList() {
        return objectsInGameList;
    }

    public void update(float deltaTime) {
        for (ModelObject obj : objectsInGameList) {
            {
                obj.updateState(deltaTime);
            }
        }
    }

}
