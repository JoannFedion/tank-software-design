package ru.mipt.bit.platformer.GeneratorsLevelInfo;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.Actions.Direction;
import ru.mipt.bit.platformer.ModelObject;
import ru.mipt.bit.platformer.LevelGame;
import ru.mipt.bit.platformer.GameModels.Objects.Tank;
import ru.mipt.bit.platformer.GameModels.Objects.Tree;
import ru.mipt.bit.platformer.LevelCharacteristic;
import ru.mipt.bit.platformer.LevelGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomLevelGenerator implements LevelGenerator {
    private final List<ModelObject> listObjects;
    private final int numbers_tree;
    private final int numbers_enemy_tank;
    private Tank playerObject;
    private final Random random;
    private final int HEIGHT;
    private final int WIDTH;
    private final int INITIAL_HEALTH_TANK;
    private final LevelGame levelGame;

    @Override
    public LevelInfo generateLevelInfo() {
        generateObjects();
        return new LevelInfo(levelGame, playerObject);
    }

    public RandomLevelGenerator(LevelCharacteristic levelCharacteristic, int treeNumbers, int enemyTankNumbers) {
        this.listObjects = new ArrayList<>();
        this.levelGame = new LevelGame(listObjects);
        this.random = new Random();
        this.numbers_tree = treeNumbers;
        this.numbers_enemy_tank = enemyTankNumbers;
        this.WIDTH = levelCharacteristic.getWIDTH();
        this.HEIGHT = levelCharacteristic.getHEIGHT();
        this.INITIAL_HEALTH_TANK = levelCharacteristic.getInitialHealthTank();
    }
    private void generatePlayerObject(){
        playerObject = new Tank(generateCoordinates(), Direction.UP, INITIAL_HEALTH_TANK, levelGame);
        listObjects.add(playerObject);
    }

    private void generateObjects() {
        generateEnemyTanks();
        generateTrees();
        generatePlayerObject();
    }

    private void generateEnemyTanks() {
        for (int i = 0; i < numbers_enemy_tank; i++) {
            listObjects.add(generateTank());
        }
    }

    private Tank generateTank() {
        GridPoint2 coordinates = generateCoordinates();
        Direction[] listValuesDirection = Direction.values();
        int numberDirection = random.nextInt(listValuesDirection.length);
        return new Tank(coordinates, listValuesDirection[numberDirection], INITIAL_HEALTH_TANK, levelGame);
    }

    private void generateTrees() {
        for (int i = 0; i < numbers_tree; i++) {
            listObjects.add(generateTree());
        }
    }

    private Tree generateTree() {
        GridPoint2 coordinates = generateCoordinates();
        return new Tree(coordinates);
    }

    private GridPoint2 generateCoordinates() {
        int x = random.nextInt(WIDTH);
        int y = random.nextInt(HEIGHT);
        GridPoint2 coordinates = new GridPoint2(x, y);
        while (isEmptyCell(coordinates)) {
            x = random.nextInt(WIDTH);
            y = random.nextInt(HEIGHT);
            coordinates = new GridPoint2(x, y);
        }
        return coordinates;
    }

    private boolean isEmptyCell(GridPoint2 coordinates) {
        for (ModelObject obj : listObjects) {
            if (obj.getCoordinates().equals(coordinates)) {
                return true;
            }
        }
        return false;
    }
}
