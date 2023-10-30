package ru.mipt.bit.platformer.ObjectControllers;

import ru.mipt.bit.platformer.Actions.Direction;
import ru.mipt.bit.platformer.GameModels.DamageModel;
import ru.mipt.bit.platformer.ModelObject;
import ru.mipt.bit.platformer.LevelCharacteristic;
import ru.mipt.bit.platformer.LevelGame;
import ru.mipt.bit.platformer.LevelListener;

import java.util.*;

public class CollidesController {
    private final int BUSY = 1;
    private final int FREE = 0;
    private final int widthLevel;
    private final int heightLevel;
    private final LevelGame levelGame;
    private final Map<Pair<Integer, Integer>, ModelObject> modelObjectMap;

    private LevelListener listenerDying;

    public CollidesController(LevelGame levelGame, LevelCharacteristic levelCharacteristic) {
        this.levelGame = levelGame;
        this.widthLevel = levelCharacteristic.getWIDTH();
        this.heightLevel = levelCharacteristic.getHEIGHT();
        this.modelObjectMap = new HashMap<>();
        fillingBusyFreeFieldObjects();
    }

    public void addListener(LevelListener listenerDying) {
        this.listenerDying = listenerDying;
    }

    private void fillingBusyFreeFieldObjects() {
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


    public void makeBusyCell(ModelObject object) {
        int x = object.getCoordinates().x;
        int y = object.getCoordinates().y;
        modelObjectMap.put(new Pair<>(x, y), object);
    }

    public void update() {
        fillingBusyFreeFieldObjects();
    }

    public boolean objectDontCollideWithSomeElse(ModelObject obj, Direction direction) {
        boolean isNotCollide;
        int x_obj = direction.addCoordinates(obj.getCoordinates()).x;
        int y_obj = direction.addCoordinates(obj.getCoordinates()).y;

        if (exitOutBoundsField(x_obj, y_obj)) {
            calculateDamage(obj);
            return false;
        }
        isNotCollide = isCellFree(x_obj, y_obj);

        if (!isNotCollide) {
            ModelObject object1 = modelObjectMap.get(new Pair<>(x_obj, y_obj));
            calculateDamage(obj, object1);
        }
        return isNotCollide;
    }

    private void calculateDamage(ModelObject obj, ModelObject obj1) {
        if (obj instanceof DamageModel && obj1 instanceof DamageModel) {
            calculateDamage((DamageModel) obj, (DamageModel) obj1);
        }
    }

    private void calculateDamage(ModelObject object) {
        if (object instanceof DamageModel) {
            if (!((DamageModel) object).isAlive()) {
                listenerDying.onDeleteGameObject(object);
                this.update();
            }
        }
    }


    private void calculateDamage(DamageModel objFirst, DamageModel objSecond) {
        objFirst.getDamage(objSecond);
        objSecond.getDamage(objFirst);
        if (!objFirst.isAlive()) {
            listenerDying.onDeleteGameObject(objFirst);
        }
        if (!objSecond.isAlive()) {
            listenerDying.onDeleteGameObject(objSecond);
        }
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
        private T value1;
        private K value2;

        public Pair(T value1, K value2) {
            this.value1 = value1;
            this.value2 = value2;
        }

        public T getValue1() {
            return value1;
        }

        public K getValue2() {
            return value2;
        }

        public void setValue1(T value1) {
            this.value1 = value1;
        }

        public void setValue2(K value2) {
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
