package ru.mipt.bit.platformer;

import com.badlogic.gdx.math.GridPoint2;

public interface ModelObject {
    public GridPoint2 getCoordinates();
    public void updateState(float deltaTime);

    public GridPoint2 getDestinationCoordinates();

}
