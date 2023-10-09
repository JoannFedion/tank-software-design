package ru.mipt.bit.platformer.GenerationObjects;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.GameMechanic.Direction;
import ru.mipt.bit.platformer.Interfaces.ModelObject;
import ru.mipt.bit.platformer.ModelClasses.Tank;
import ru.mipt.bit.platformer.ModelClasses.Tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomGeneration implements GenerationObjects {
    private List<ModelObject> listObjects;
    private int NUMBERS_TREES;
    
    private Random random;

    public RandomGeneration(int numbersObjects) {
        this.listObjects = new ArrayList<>();
        this.random = new Random();
        this.NUMBERS_TREES = numbersObjects - 1;
    }

    @Override
    public List<ModelObject> generate() {
        listObjects.add(generateTank());
        for (int i = 0; i < NUMBERS_TREES; i++) {
            listObjects.add(generateTree());
        }
        return listObjects;
    }

    private boolean isAlreadyCreatedObjectSameCoordinates(GridPoint2 coordinates) {
        for (ModelObject obj : listObjects) {
            if (obj.getCoordinates().equals(coordinates)) {
                return true;
            }
        }
        return false;
    }

    private Tank generateTank() {
        GridPoint2 coordinates = generateCoordinates();
        Direction[] listValuesDirection = Direction.values();
        int numberDirection = random.nextInt(listValuesDirection.length);
        return new Tank(coordinates, listValuesDirection[numberDirection]);
    }

    private GridPoint2 generateCoordinates() {
        int x = random.nextInt(5);
        int y = random.nextInt(5);
        GridPoint2 coordinates = new GridPoint2(x, y);
        while (!isAlreadyCreatedObjectSameCoordinates(coordinates)) {
            x = random.nextInt(5);
            y = random.nextInt(5);
            coordinates = new GridPoint2(x, y);
        }
        return coordinates;
    }

    private Tree generateTree() {
        GridPoint2 coordinates = generateCoordinates();
        return new Tree(coordinates);
    }
}
