package ru.mipt.bit.platformer.GameModels;

import com.badlogic.gdx.math.GridPoint2;

public interface ModelObject {
    public GridPoint2 getCoordinates();
    public void updateState(float deltaTime);

}
