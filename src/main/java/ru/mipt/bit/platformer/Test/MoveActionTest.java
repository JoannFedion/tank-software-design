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
//class MoveActionTest {
//
//    @Test
//    void testMoveActionApplyingToTankWithoutNearSomeObstacles() {
//        LevelCharacteristic someLevelCharacteristic = createSomeLevelCharacteristic();
//        Tank someTank = createSomeTank();
//        Tree someTreeThatNotNearSomeTank = createSomeTreeThatNotNearSomeTank();
//        CollidesController collidesController = new CollidesController(List.of(someTank, someTreeThatNotNearSomeTank), someLevelCharacteristic );
//        Action MoveToSomeDirection = createMoveToSomeDirection(collidesController);
//
//        MoveToSomeDirection.apply(someTank);
//
//        assertFalse(someTank.getDestinationCoordinates().equals(someTank.getCoordinates()));
//
//    }
//    @Test
//    void testMoveActionApplyingToTankWithNearSomeObstacles() {
//        LevelCharacteristic someLevelCharacteristic = createSomeLevelCharacteristic();
//        Tank someTank = createSomeTank();
//        Tree someTreeThatNearSomeTankAlongSomeDirection = createSomeTreeThatNearSomeTankAlongSomeDirection();
//        CollidesController collidesController = new CollidesController(List.of(someTank, someTreeThatNearSomeTankAlongSomeDirection), someLevelCharacteristic );
//        Action MoveToSomeDirection = createMoveToSomeDirection(collidesController);
//
//        MoveToSomeDirection.apply(someTank);
//
//        assertTrue(someTank.getDestinationCoordinates().equals(someTank.getCoordinates()));
//
//    }
//
//    private Tree createSomeTreeThatNearSomeTankAlongSomeDirection() {
//        return new Tree(createSomeTank().getCoordinates().add(createSomeDirection().getVector()));
//    }
//
//    private LevelCharacteristic createSomeLevelCharacteristic(){
//        return new LevelCharacteristic(10, 10);
//    }
//    private Action createMoveToSomeDirection(CollidesController collidesController){
//        return new MoveAction(createSomeDirection(), collidesController);
//    }
//
//    private Direction createSomeDirection(){
//        return Direction.UP;
//    }
//
//    private Tank createSomeTank(){
//        return new Tank(new GridPoint2(0, 0), createSomeDirection());
//    }
//    private Tree createSomeTreeThatNotNearSomeTank(){
//        return new Tree(createSomeTank().getCoordinates().add(createSomeDirection().getVector()).add(createSomeDirection().getVector()));
//    }
//}