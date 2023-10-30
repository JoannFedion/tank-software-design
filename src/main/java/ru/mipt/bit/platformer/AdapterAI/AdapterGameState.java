package ru.mipt.bit.platformer.AdapterAI;

import org.awesome.ai.state.GameState;
import org.awesome.ai.state.immovable.Obstacle;
import org.awesome.ai.state.movable.Actor;
import org.awesome.ai.state.movable.Bot;
import org.awesome.ai.state.movable.Orientation;
import org.awesome.ai.state.movable.Player;
import ru.mipt.bit.platformer.Action;
import ru.mipt.bit.platformer.Actions.Direction;
import ru.mipt.bit.platformer.ModelObject;
import ru.mipt.bit.platformer.GameModels.MovingObjects;
import ru.mipt.bit.platformer.GameModels.Objects.Tree;
import ru.mipt.bit.platformer.LevelCharacteristic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AdapterGameState {
    private final List<Bot> bots;
    private final List<Obstacle> obstacles;
    private final Player player;
    private final int levelWidth;
    private final int levelHeight;
    private final List<ModelObject> objectsFromGame;

    private final Map<Direction, Orientation> directionToOrientationMap;

    private final Map<Actor, ModelObject> bondBetweenActorAndModelObject;

    public AdapterGameState(List<ModelObject> modelObjectList,
                            MovingObjects playerObject,
                            LevelCharacteristic levelCharacteristic,
                            Map<Direction, Orientation> directionToOrientationMap) {
        this.bondBetweenActorAndModelObject = new HashMap<>();
        this.directionToOrientationMap = directionToOrientationMap;

        this.objectsFromGame = modelObjectList;
        this.bots = createBots(playerObject);
        this.obstacles = createObstacles();
        this.player = createPlayer(playerObject);
        this.levelWidth = levelCharacteristic.getWIDTH();
        this.levelHeight = levelCharacteristic.getHEIGHT();
    }

    public GameState buildGameState() {
        return GameState.builder()
                .obstacles(obstacles)
                .bots(bots)
                .player(player)
                .levelHeight(levelHeight)
                .levelWidth(levelWidth)
                .build();
    }

    public Map<Actor, ModelObject> getActors(){
        return bondBetweenActorAndModelObject;
    }

    private List<Bot> createBots(MovingObjects playerObject) {
        return objectsFromGame.stream()
                .filter((el) -> el instanceof MovingObjects && el != playerObject)
                .map((el) -> (MovingObjects) el)
                .map(this::createBot)
                .collect(Collectors.toList());
    }

    private Player createPlayer(MovingObjects playerObject) {
        Player player = Player.builder()
                .source(playerObject)
                .x(playerObject.getCoordinates().x)
                .y(playerObject.getCoordinates().y)
                .destX(playerObject.getDestinationCoordinates().x)
                .destY(playerObject.getDestinationCoordinates().y)
                .orientation(directionToOrientationMap.get(playerObject.getDirection()))
                .build();
        bondBetweenActorAndModelObject.put(player, playerObject);
        return player;
    }

    private List<Obstacle> createObstacles() {
        return objectsFromGame.stream()
                .filter((el) -> el instanceof Tree)
                .map((el) -> (Tree) el)
                .map(this::createObstacle)
                .collect(Collectors.toList());
    }

    private Obstacle createObstacle(Tree tree) {
        return new Obstacle(tree.getCoordinates().x, tree.getCoordinates().y);
    }

    private Bot createBot(MovingObjects object) {
        Bot bot = Bot.builder()
                .source(object)
                .x(object.getCoordinates().x)
                .y(object.getCoordinates().y)
                .destX(object.getDestinationCoordinates().x)
                .destY(object.getDestinationCoordinates().y)
                .orientation(directionToOrientationMap.get(object.getDirection()))
                .build();
        bondBetweenActorAndModelObject.put(bot, object);
        return bot;
    }

    public void deleteObject(ModelObject object) {
        for (Actor actor : bondBetweenActorAndModelObject.keySet()){
            if (bondBetweenActorAndModelObject.get(actor) == object){
                objectsFromGame.remove(object);
            }
        }
    }
}
