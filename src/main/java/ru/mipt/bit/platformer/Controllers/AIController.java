package ru.mipt.bit.platformer.Controllers;

import ru.mipt.bit.platformer.Actions.Action;
import ru.mipt.bit.platformer.GameModels.ModelObject;
import ru.mipt.bit.platformer.GameModels.Tank;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class AIController implements ObjectController {

    private List<ObjectController> AIControllers;
    private Map<Integer, Action> keyToActionMap;
    private List<ModelObject> objectManagedByAI;
    private ModelObject playerObject;

    public AIController(List<ModelObject> objectManagedByAI, ModelObject playerObject) {
        this.objectManagedByAI = objectManagedByAI;
        this.playerObject = playerObject;
        this.keyToActionMap = new HashMap<>();
        createAIController();
    }

    private ObjectController createOneAIController(ModelObject object){
        if (object != playerObject && object.getClass() == Tank.class){
            return new AIRandomController(object, keyToActionMap);
        }
        return null;
    }

    private void createAIController() {
        this.AIControllers = objectManagedByAI.stream()
                .map(this::createOneAIController)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    @Override
    public void execute() {
        AIControllers.forEach(ObjectController::execute);
    }

    public void addMapping(int key, Action action) {
        keyToActionMap.put(key, action);
    }

}
