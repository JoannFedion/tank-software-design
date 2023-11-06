package ru.mipt.bit.platformer.Controllers;

import ru.mipt.bit.platformer.Action;
import ru.mipt.bit.platformer.Actions.Direction;
import ru.mipt.bit.platformer.Actions.MoveAction;
import ru.mipt.bit.platformer.Actions.ShootAction;
import ru.mipt.bit.platformer.ObjectController;
import ru.mipt.bit.platformer.CollidesController;
import ru.mipt.bit.platformer.ModelObject;
import ru.mipt.bit.platformer.GameModels.Objects.Tank;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.badlogic.gdx.Input.Keys.*;
import static com.badlogic.gdx.Input.Keys.RIGHT;

public class AIController implements ObjectController<Integer> {

    private List<ObjectController<Integer>> AIControllers;
    private Map<Integer, Action> keyToActionMap;
    private List<ModelObject> objectManagedByAI;
    private ModelObject playerObject;

    public AIController(List<ModelObject> objectManagedByAI, ModelObject playerObject) {
        this.objectManagedByAI = objectManagedByAI;
        this.playerObject = playerObject;
        this.keyToActionMap = new HashMap<>();
        createAIController();
    }

    private ObjectController<Integer> createOneAIController(ModelObject object){
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
    @Override
    public void addMapping(Integer key, Action action) {
        keyToActionMap.put(key, action);
    }

    @Override
    public void initKeyMappingForController(CollidesController collidesController) {
        addMapping(UP, new MoveAction(Direction.UP, collidesController));
        addMapping(DOWN, new MoveAction(Direction.DOWN, collidesController));
        addMapping(LEFT, new MoveAction(Direction.LEFT, collidesController));
        addMapping(RIGHT, new MoveAction(Direction.RIGHT, collidesController));
        addMapping(SPACE, new ShootAction(collidesController));
    }

    @Override
    public void deleteObject(ModelObject object) {
        AIControllers.forEach((controller) -> controller.deleteObject(object));
    }
}
