//package ru.mipt.bit.platformer.Test;
//
//import com.badlogic.gdx.math.GridPoint2;
//import org.junit.jupiter.api.Test;
//import ru.mipt.bit.platformer.Action;
//import ru.mipt.bit.platformer.Actions.Direction;
//import ru.mipt.bit.platformer.Actions.MoveAction;
//import ru.mipt.bit.platformer.ObjectControllers.CollidesController;
//import ru.mipt.bit.platformer.GameModels.Objects.Tank;
//import ru.mipt.bit.platformer.GameModels.Objects.Tree;
//import ru.mipt.bit.platformer.LevelCharacteristic;
//
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class CollidesControllerTest {
//
//    @Test
//    void testObjectDontCollideWithSomeElseWhenTankMoveToTreeThenGetFalse() {
//        LevelCharacteristic someLevelCharacteristic = createSomeLevelCharacteristic();
//        Tank someTank = createSomeTank();
//        Direction someDirectionAlongWhichWillBeTree = createSomeDirection();
//        Tree someTreeThatNearSomeTankAlongSomeDirection = createSomeTreeThatNearSomeTankAlongSomeDirection();
//        CollidesController collidesController = new CollidesController(List.of(someTank, someTreeThatNearSomeTankAlongSomeDirection), someLevelCharacteristic);
//
//
//        assertFalse(collidesController.objectDontCollideWithSomeElse(someTank, someDirectionAlongWhichWillBeTree));
//    }
//
//    @Test
//    void testObjectDontCollideWithSomeElseWhenTankMoveToFreeCellThenGetTrue() {
//        LevelCharacteristic someLevelCharacteristic = createSomeLevelCharacteristic();
//        Tank someTank = createSomeTank();
//        Direction someDirectionAlongWhichWillNotBeTree = createSomeDirection();
//        Tree someTreeThatNotNearSomeTankAlongSomeDirection = createSomeTreeThatNotNearSomeTank();
//        CollidesController collidesController = new CollidesController(List.of(someTank, someTreeThatNotNearSomeTankAlongSomeDirection), someLevelCharacteristic);
//
//
//        assertTrue(collidesController.objectDontCollideWithSomeElse(someTank, someDirectionAlongWhichWillNotBeTree));
//    }
//
//    @Test
//    void testObjectDontCollideWithSomeElseWhenTankMoveOutBoundLevel() {
//        LevelCharacteristic someLevelCharacteristic = createSomeLevelCharacteristic();
//        Tank tankNearBound = createTankNearBound();
//        Direction directionOnBound = createDirectionOnBound();
//        CollidesController collidesController = new CollidesController(List.of(tankNearBound), someLevelCharacteristic);
//
//
//        assertFalse(collidesController.objectDontCollideWithSomeElse(tankNearBound, directionOnBound));
//    }
//
//    @Test
//    void testObjectDontCollideWithSomeElseWhenOneTankWantToMoveToFreeCellAndAnotherTankWantToMoveToStartFirstTankCellThenCooridinatesAndDestinationCoordinatesDontChange(){
//        LevelCharacteristic someLevelCharacteristic = createSomeLevelCharacteristic();
//        Tank someTank = createSomeTank();
//        Tank tankWhichBelowSomeTank = createTankWhichBelowSomeTank(someTank);
//        CollidesController collidesController = new CollidesController(List.of(someTank, tankWhichBelowSomeTank), someLevelCharacteristic);
//        MoveAction someMoveAction = createSomeMoveAction(collidesController);
//        MoveAction moveActionForBelowTankToUp = new MoveAction(Direction.UP, collidesController);
//
//
//        someMoveAction.apply(someTank);
//        moveActionForBelowTankToUp.apply(tankWhichBelowSomeTank);
//
//
//        assertTrue(tankWhichBelowSomeTank.getDestinationCoordinates() == tankWhichBelowSomeTank.getCoordinates());
//    }
//
//
//    private MoveAction createSomeMoveAction(CollidesController collidesController){
//        return new MoveAction(createSomeDirection(), collidesController);
//    }
//
//    private Tank createTankWhichBelowSomeTank(Tank someTank){
//        return new Tank(someTank.getCoordinates().add(Direction.DOWN.getVector()), createSomeDirection());
//    }
//
//    private Tank createTankNearBound() {
//        return new Tank(new GridPoint2(0, 0), createSomeDirection());
//    }
//
//    private Direction createDirectionOnBound() {
//        return Direction.LEFT;
//    }
//
//    private Tree createSomeTreeThatNearSomeTankAlongSomeDirection() {
//        return new Tree(createSomeTank().getCoordinates().add(createSomeDirection().getVector()));
//    }
//
//    private LevelCharacteristic createSomeLevelCharacteristic() {
//        return new LevelCharacteristic(10, 10);
//    }
//
//    private Direction createSomeDirection() {
//        return Direction.UP;
//    }
//
//    private Tank createSomeTank() {
//        return new Tank(new GridPoint2(3, 5), createSomeDirection());
//    }
//
//    private Tree createSomeTreeThatNotNearSomeTank() {
//        return new Tree(createSomeTank().getCoordinates().add(createSomeDirection().getVector()).add(createSomeDirection().getVector()));
//    }
//}