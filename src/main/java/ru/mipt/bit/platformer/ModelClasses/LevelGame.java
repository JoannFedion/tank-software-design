package ru.mipt.bit.platformer.ModelClasses;

import ru.mipt.bit.platformer.GenerationObjects.GenerationObjects;
import ru.mipt.bit.platformer.GenerationObjects.RandomGeneration;
import ru.mipt.bit.platformer.Interfaces.Action;
import ru.mipt.bit.platformer.Interfaces.ModelObject;
import ru.mipt.bit.platformer.LevelListener;

import java.util.ArrayList;
import java.util.List;

public class LevelGame {
    private GenerationObjects strategy = new RandomGeneration(5);
    private List<ModelObject> objectsInGameList;
    private LevelListener levelListener;

    public LevelGame(LevelListener levelListener) {
        this.objectsInGameList = new ArrayList<>();
        this.levelListener = levelListener;
        generateObject();
    }

    public void generateObject() {
        objectsInGameList.addAll(strategy.generate());
        for (ModelObject obj : objectsInGameList){
            levelListener.onAddGameEntity(obj);
        }
    }

    public void update(float deltaTime) {
        for (ModelObject obj : objectsInGameList) {
            {
                obj.updateState(deltaTime);
            }
        }
    }

    public void apply(Action action, ModelObject object, float deltaTime) {
        if (action.isPossibleApplyAction(object, objectsInGameList))
            action.apply(object, deltaTime);
    }
}
