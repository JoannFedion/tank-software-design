package ru.mipt.bit.platformer.GameModels;

import ru.mipt.bit.platformer.Actions.Direction;
import ru.mipt.bit.platformer.LevelCharacteristic;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public class CollidesController {
    private final int BUSY = 1;
    private final int FREE = 0;
    private final int widthLevel;
    private final int heightLevel;
    private final Deque<int[]> queueForBusyPosition;
    private final List<ModelObject> modelObjectList;
    private int[][] busyByObjectsField;

    public CollidesController(List<ModelObject> modelObjectList, LevelCharacteristic levelCharacteristic) {
        this.modelObjectList = modelObjectList;
        widthLevel = levelCharacteristic.getWIDTH();
        heightLevel = levelCharacteristic.getHEIGHT();
        fillingBusyFieldByObjectsInLevel();
        queueForBusyPosition = new ArrayDeque<>();
    }


    private void fillingBusyFieldByObjectsInLevel() {

        busyByObjectsField = new int[widthLevel][heightLevel];
        for (int i = 0; i < widthLevel; i++) {
            for (int j = 0; j < heightLevel; j++) {
                busyByObjectsField[i][j] = FREE;
            }
        }

        for (ModelObject object :modelObjectList) {
            int x = object.getCoordinates().x;
            int y = object.getCoordinates().y;
            busyByObjectsField[x][y] = BUSY;
        }
    }

    public void update(){
        int[] postitionWillFree;
        while (!queueForBusyPosition.isEmpty()){
            postitionWillFree = queueForBusyPosition.pop();
            busyByObjectsField[postitionWillFree[0]][postitionWillFree[1]] = FREE;
        }
    }
    public boolean objectDontCollideWithSomeElse(ModelObject obj, Direction direction) {
        int x_obj = direction.addCoordinates(obj.getCoordinates()).x;
        int y_obj = direction.addCoordinates(obj.getCoordinates()).y;
        if (exitOutBoundsField(x_obj, y_obj)) return false;

        boolean isNotCollide = busyByObjectsField[x_obj][y_obj] == FREE;
        if (isNotCollide) {
            updateStateCollisionField(obj, direction);
        }
        return isNotCollide;
    }

    private void updateStateCollisionField(ModelObject obj, Direction direction) {
        int x_obj_new = direction.addCoordinates(obj.getCoordinates()).x;
        int y_obj_new = direction.addCoordinates(obj.getCoordinates()).y;
        int x_obj_old = (obj.getCoordinates().x);
        int y_obj_old = (obj.getCoordinates().y);

        busyByObjectsField[x_obj_new][y_obj_new] = BUSY;
        queueForBusyPosition.add(new int[]{x_obj_old, y_obj_old});
    }
    private boolean exitOutBoundsField(int x, int y) {
        boolean isExit = false;
        if (exitOutBoundField(x, widthLevel) || exitOutBoundField(y, heightLevel)){
            isExit = true;
        }
        return isExit;
    }

    private boolean exitOutBoundField(int coordinate, int bound) {
        return coordinate >= bound || coordinate < 0;
    }
    public void show(){
        System.out.println("---------");
        for (int i = heightLevel - 1; i >= 0; i--) {
            for (int j = 0; j < widthLevel; j++) {
                System.out.print(busyByObjectsField[j][i]);
            }
            System.out.println();
        }
        System.out.println("-----------");
    }
}
