package ru.mipt.bit.platformer.Interfaces;

import com.badlogic.gdx.math.GridPoint2;

public interface ModelObject {
    public GridPoint2 getCoordinates();
    public void updateState(float deltaTime);
}
