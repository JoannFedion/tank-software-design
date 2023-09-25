package ru.mipt.bit.platformer;

import com.badlogic.gdx.math.GridPoint2;

public class Tree implements PropertyObject {
    private GridPoint2 coordinates;

    public Tree(GridPoint2 coordinates) {
        this.coordinates = coordinates;
    }

    public GridPoint2 getCoordinates() {
        return coordinates;
    }

    public float getRotate(){
        return 0f;
    }
}
