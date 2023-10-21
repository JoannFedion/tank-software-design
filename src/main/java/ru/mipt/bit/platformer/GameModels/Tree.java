package ru.mipt.bit.platformer.GameModels;

import com.badlogic.gdx.math.GridPoint2;

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
