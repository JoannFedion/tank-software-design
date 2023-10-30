package ru.mipt.bit.platformer;

import java.util.ArrayList;
import java.util.List;

public class LevelGame {
    private final List<LevelListener> levelListenerList;
    private final List<ModelObject> objectsInGameList;

    public LevelGame(List<ModelObject> listObjects) {
        this.objectsInGameList = listObjects;
        this.levelListenerList = new ArrayList<>();
        System.out.println(objectsInGameList.size());
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

    public void addListener(LevelListener levelListener){
        levelListenerList.add(levelListener);
    }

    public void add(ModelObject object) {
        objectsInGameList.add(object);
        levelListenerList.forEach((levelListener -> levelListener.onAddGameObject(object)));
    }
    public void delete(ModelObject object){
        objectsInGameList.remove(object);
        levelListenerList.forEach(levelListener -> levelListener.onDeleteGameObject(object));
    }
}
