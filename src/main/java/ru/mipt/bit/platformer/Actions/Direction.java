package ru.mipt.bit.platformer.Actions;

import com.badlogic.gdx.math.GridPoint2;

public enum Direction {
    UP(new GridPoint2(0, 1), 90),
    DOWN(new GridPoint2(0, -1), -90),
    LEFT(new GridPoint2(-1, 0), -180),
    RIGHT(new GridPoint2(1, 0), 0);

    private final GridPoint2 vector;
    private final float rotation;

    Direction(GridPoint2 vector, float rotation) {
        this.vector = vector;
        this.rotation = rotation;
    }

    public GridPoint2 getVector() {
        return vector;
    }

    public GridPoint2 addCoordinates(GridPoint2 coordinates){
        return coordinates.cpy().add(this.vector);
    }

    public float getRotation() {
        return rotation;
    }
}

