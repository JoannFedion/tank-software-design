package ru.mipt.bit.platformer;

import org.junit.jupiter.params.provider.EnumSource;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LevelGame {
    private final List<LevelListener> levelListenerList;
    private final List<ModelObject> objectsInGameList;

    public LevelGame(List<ModelObject> listObjects) {
        this.objectsInGameList = listObjects;
        this.levelListenerList = new ArrayList<>();
    }

    public List<ModelObject> getObjectsInGameList() {
        return objectsInGameList;
    }

    public void update(float deltaTime) {
        List<ModelObject>  objectsInGameListCopy = new ArrayList<>();
        objectsInGameListCopy.addAll(objectsInGameList);
        for (ModelObject object : objectsInGameListCopy){
            object.updateState(deltaTime);
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
