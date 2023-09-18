package ru.mipt.bit.platformer;

import com.badlogic.gdx.math.GridPoint2;


public class Tank extends GameObject {

    private GridPoint2 objectDestinationCoordinates;

    public Tank(String picturePath, int xCoordinate, int yCoordinate, float objectRotation) {
        super(picturePath, xCoordinate, yCoordinate, objectRotation);
        this.objectDestinationCoordinates = new GridPoint2(xCoordinate, yCoordinate);
    }

    public void setObjectDestinationCoordinates(GridPoint2 objectDestinationCoordinates) {
        this.objectDestinationCoordinates = objectDestinationCoordinates;
    }

    public GridPoint2 getObjectDestinationCoordinates() {
        return objectDestinationCoordinates;
    }
}