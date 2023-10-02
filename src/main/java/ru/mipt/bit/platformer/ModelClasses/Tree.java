package ru.mipt.bit.platformer.ModelClasses;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.Interfaces.ModelObject;

public class Tree implements ModelObject {
    private GridPoint2 coordinates;

    public Tree(GridPoint2 coordinates) {
        this.coordinates = coordinates;
    }

    public GridPoint2 getCoordinates() {
        return coordinates;
    }

    @Override
    public void updateState(float deltaTime) {
    }
}
