package ru.mipt.bit.platformer.AdapterAI;

import org.awesome.ai.AI;
import org.awesome.ai.Action;
import org.awesome.ai.Recommendation;
import org.awesome.ai.state.GameState;
import org.awesome.ai.strategy.NotRecommendingAI;
import ru.mipt.bit.platformer.Actions.Direction;
import ru.mipt.bit.platformer.Actions.MoveAction;
import ru.mipt.bit.platformer.CollidesController;
import ru.mipt.bit.platformer.ObjectController;
import org.awesome.ai.state.movable.*;
import ru.mipt.bit.platformer.ModelObject;
import ru.mipt.bit.platformer.GameModels.MovingObjects;
import ru.mipt.bit.platformer.LevelCharacteristic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class AdapterAIController implements ObjectController<Action> {

    private AdapterGameState adapterGameState;
    private Map<Direction, Orientation> directionToOrientationMap;
    private Map<Action, ru.mipt.bit.platformer.Action> actionAIandGameMap;

    private final AI notRecommendingAI;

    public AdapterAIController(List<ModelObject> modelObjectList, MovingObjects playerObject, LevelCharacteristic levelCharacteristic, CollidesController collidesController) {
        createMappingDirectionToOrientation();
        initKeyMappingForController(collidesController);
        this.adapterGameState = new AdapterGameState(
                modelObjectList,
                playerObject,
                levelCharacteristic,
                directionToOrientationMap);
        this.notRecommendingAI = new NotRecommendingAI();
    }
    private void createMappingDirectionToOrientation(){
        directionToOrientationMap = new HashMap<>();
        directionToOrientationMap.put(Direction.UP, Orientation.N);
        directionToOrientationMap.put(Direction.DOWN, Orientation.S);
        directionToOrientationMap.put(Direction.LEFT, Orientation.W);
        directionToOrientationMap.put(Direction.RIGHT, Orientation.E);
    }

    @Override
    public void initKeyMappingForController(CollidesController collidesController) {
        actionAIandGameMap = new HashMap<>();
        addMapping(Action.MoveNorth, new MoveAction(Direction.UP, collidesController));
        addMapping(Action.MoveSouth, new MoveAction(Direction.DOWN, collidesController));
        addMapping(Action.MoveEast, new MoveAction(Direction.LEFT, collidesController));
        addMapping(Action.MoveWest, new MoveAction(Direction.RIGHT, collidesController));

    }

    @Override
    public void deleteObject(ModelObject object) {
        adapterGameState.deleteObject(object);
    }

    @Override
    public void addMapping(Action actionAI, ru.mipt.bit.platformer.Action actionGame) {
        actionAIandGameMap.put(actionAI, actionGame);
    }

    @Override
    public void execute() {
        GameState gameState = adapterGameState.buildGameState();
        Map<Actor, ModelObject> actorAndObjectMap = adapterGameState.getActors();
        for(Recommendation recommendation : notRecommendingAI.recommend(gameState)){
            if (recommendation.getActor() != gameState.getPlayer()) {
                ModelObject objects = actorAndObjectMap.get(recommendation.getActor());
                ru.mipt.bit.platformer.Action action = actionAIandGameMap.get(recommendation.getAction());
                action.apply(objects);
            }
        }
    }
}
