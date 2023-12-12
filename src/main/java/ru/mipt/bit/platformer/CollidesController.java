package ru.mipt.bit.platformer;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.Actions.Direction;

import java.util.*;

public class CollidesController {
    private final int widthLevel;
    private final int heightLevel;
    private final LevelGame levelGame;
    private final Map<Pair<Integer, Integer>, ModelObject> modelObjectMap;

    public CollidesController(LevelGame levelGame, LevelCharacteristic levelCharacteristic) {
        this.levelGame = levelGame;
        this.widthLevel = levelCharacteristic.getWIDTH();
        this.heightLevel = levelCharacteristic.getHEIGHT();
        this.modelObjectMap = new HashMap<>();
        updateField();
    }
    private void updateField() {
        for (int i = 0; i < widthLevel; i++) {
            for (int j = 0; j < heightLevel; j++) {
                modelObjectMap.remove(new Pair<>(i, j));
            }
        }
        for (ModelObject object : levelGame.getObjectsInGameList()) {
            int x = object.getCoordinates().x;
            int y = object.getCoordinates().y;
            modelObjectMap.put(new Pair<>(x, y), object);

            int dest_x = object.getDestinationCoordinates().x;
            int dest_y = object.getDestinationCoordinates().y;
            modelObjectMap.put(new Pair<>(dest_x, dest_y), object);
        }
    }
    public ModelObject getObjectWithCoordinate(GridPoint2 coordinate){
        return modelObjectMap.get(new Pair<>(coordinate.x, coordinate.y));
    }

    public void makeBusyCell(ModelObject object) {
        int x = object.getCoordinates().x;
        int y = object.getCoordinates().y;
        modelObjectMap.put(new Pair<>(x, y), object);
    }

    public void makeFreeCell(ModelObject object) {
        int x = object.getCoordinates().x;
        int y = object.getCoordinates().y;
        modelObjectMap.remove(new Pair<>(x, y));
    }

    public void update() {
        updateField();
    }

    public boolean objectDontCollideWithSomeElse(ModelObject obj, Direction direction) {
        int x_obj = direction.addCoordinates(obj.getCoordinates()).x;
        int y_obj = direction.addCoordinates(obj.getCoordinates()).y;
        return isCellFree(x_obj, y_obj);
    }

    public boolean isCellFree(int x, int y) {
        if (exitOutBoundsField(x, y)) return false;
        return !modelObjectMap.containsKey(new Pair<>(x, y));
    }

    public boolean objectExist(ModelObject object) {
        return levelGame.getObjectsInGameList().contains(object);
    }

    public boolean exitOutBoundsField(int x, int y) {
        boolean isExit = false;
        if (exitOutBoundField(x, widthLevel) || exitOutBoundField(y, heightLevel)) {
            isExit = true;
        }
        return isExit;
    }

    private boolean exitOutBoundField(int coordinate, int bound) {
        return coordinate >= bound || coordinate < 0;
    }

    private static class Pair<T, K> {
        private final T value1;
        private final K value2;

        public Pair(T value1, K value2) {
            this.value1 = value1;
            this.value2 = value2;
        }
        @Override
        public boolean equals(Object object) {
            if (this == object) return true;
            if (object == null || getClass() != object.getClass()) return false;
            Pair<?, ?> pair = (Pair<?, ?>) object;
            return Objects.equals(value1, pair.value1) && Objects.equals(value2, pair.value2);
        }

        @Override
        public int hashCode() {
            return Objects.hash(value1, value2);
        }
    }
}
