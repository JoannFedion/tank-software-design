package ru.mipt.bit.platformer.Controllers;

import ru.mipt.bit.platformer.Action;
import ru.mipt.bit.platformer.Actions.Direction;
import ru.mipt.bit.platformer.Actions.MoveAction;
import ru.mipt.bit.platformer.Actions.ShootAction;
import ru.mipt.bit.platformer.ObjectController;
import ru.mipt.bit.platformer.CollidesController;
import ru.mipt.bit.platformer.ModelObject;
import ru.mipt.bit.platformer.GameModels.Objects.Tank;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class AIController implements ObjectController<Integer> {

    private List<ObjectController<Integer>> AIControllers;
    private final List<Action> actionList;
    private final List<ModelObject> objectManagedByAI;
    private final ModelObject playerObject;
    private final CollidesController collidesController;

    public AIController(List<ModelObject> objectManagedByAI, ModelObject playerObject, CollidesController collidesController) {
        this.objectManagedByAI = objectManagedByAI;
        this.playerObject = playerObject;
        this.collidesController = collidesController;
        this.actionList = createActionListForAI();
        createAIControllers();
    }

    private List<Action> createActionListForAI() {
        return List.of(
                new MoveAction(Direction.UP, collidesController),
                new MoveAction(Direction.DOWN, collidesController),
                new MoveAction(Direction.LEFT, collidesController),
                new MoveAction(Direction.RIGHT, collidesController),
                new ShootAction(collidesController)
                );
    }

    private ObjectController<Integer> createOneAIController(ModelObject object){
        if (object != playerObject && object.getClass() == Tank.class){
            return new AIRandomController(object, actionList);
        }
        return null;
    }

    private void createAIControllers() {
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
    public void deleteObject(ModelObject object) {
        AIControllers.forEach((controller) -> controller.deleteObject(object));
    }
}
