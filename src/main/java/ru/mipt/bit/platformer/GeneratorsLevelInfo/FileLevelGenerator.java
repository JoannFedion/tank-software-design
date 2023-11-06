package ru.mipt.bit.platformer.GeneratorsLevelInfo;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.Actions.Direction;
import ru.mipt.bit.platformer.ModelObject;
import ru.mipt.bit.platformer.LevelGame;
import ru.mipt.bit.platformer.GameModels.Objects.Tank;
import ru.mipt.bit.platformer.GameModels.Objects.Tree;
import ru.mipt.bit.platformer.LevelCharacteristic;
import ru.mipt.bit.platformer.LevelGenerator;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileLevelGenerator implements LevelGenerator {
    private final int NEW_LINE = 10;
    private final int EMPTY_CELL = 95;
    private final int TREE_CELL = 84;
    private final int TANK_CELL = 88;


    private final int WIDTH;
    private final int HEIGHT;
    private final int INITIAL_HEALTH_TANK;
    private String pathToFile;
    private List<ModelObject> listObjects;
    private ModelObject playerObject;
    private LevelGame levelGame;

    @Override
    public LevelInfo generateLevelInfo() {
        return new LevelInfo(levelGame, playerObject);
    }

    public FileLevelGenerator(LevelCharacteristic levelCharacteristic, String pathToFile) {
        this.pathToFile = pathToFile;
        this.listObjects = new ArrayList<>();
        this.HEIGHT = levelCharacteristic.getHEIGHT();
        this.WIDTH = levelCharacteristic.getWIDTH();
        this.INITIAL_HEALTH_TANK = levelCharacteristic.getInitialHealthTank();
        parse(pathToFile);
    }

    private void parse(String pathToFile) {
        levelGame = new LevelGame(listObjects);
        int x = HEIGHT;
        int y = WIDTH;
        try (FileReader reader = new FileReader(pathToFile)) {
            int c;
            while ((c = reader.read()) != -1) {
                switch (c) {
                    case NEW_LINE:
                        x = 0;
                        y--;
                    case EMPTY_CELL:
                        break;
                    case TREE_CELL:
                        listObjects.add(new Tree(new GridPoint2(x, y)));
                        break;
                    case TANK_CELL:
                        playerObject = new Tank(new GridPoint2(x, y), Direction.UP, INITIAL_HEALTH_TANK, levelGame);
                        listObjects.add(playerObject);
                        break;
                    default:
                        x++;
                }
                System.out.print((char) c);
            }
        } catch (RuntimeException | IOException ex) {
            throw new RuntimeException("Error while parsing line " + y + " in file " + pathToFile, ex);
        }
    }
}
