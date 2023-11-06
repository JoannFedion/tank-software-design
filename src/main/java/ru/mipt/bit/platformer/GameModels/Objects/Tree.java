package ru.mipt.bit.platformer.GameModels.Objects;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.GameModels.DamageModel;
import ru.mipt.bit.platformer.ModelObject;

public class Tree implements ModelObject {
    private GridPoint2 coordinates;

    public Tree(GridPoint2 coordinates) {
        this.coordinates = coordinates;
    }
    @Override
    public GridPoint2 getCoordinates() {
        return coordinates;
    }

    @Override
    public void updateState(float deltaTime) {
    }

    @Override
    public GridPoint2 getDestinationCoordinates() {
        return coordinates;
    }

}
