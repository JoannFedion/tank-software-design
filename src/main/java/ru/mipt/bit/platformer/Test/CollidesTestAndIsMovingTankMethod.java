package ru.mipt.bit.platformer.Test;

import com.badlogic.gdx.math.GridPoint2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.mipt.bit.platformer.GameMechanic.CollidesController;
import ru.mipt.bit.platformer.GameMechanic.Direction;
import ru.mipt.bit.platformer.ModelClasses.LevelGame;
import ru.mipt.bit.platformer.ModelClasses.Tank;
import ru.mipt.bit.platformer.ModelClasses.Tree;

import static org.junit.jupiter.api.Assertions.*;

class CollidesTestAndIsMovingTankMethod {
    Tree tree1;
    Tree tree2;
    Tree tree3;
    Tank tank;
    LevelGame levelGame;
    float deltaTime;

    @BeforeEach
    void setUp() {
//        tree1 = new Tree(new GridPoint2(2, 1));
//        tree2 = new Tree(new GridPoint2(1, 2));
//        tree3 = new Tree(new GridPoint2(2, 3));
//        tank = new Tank(new GridPoint2(2, 2), Direction.UP);
//        levelGame = new LevelGame();
//        levelGame.add(tree1, tree2, tree3, tank);
//        deltaTime = 1f;
    }

    @Test
    void isCollideTwoObjectsMoveLeftTankThenCollide() {
        assertFalse(CollidesController.isNotCollideTwoObjects(tree2, tank, Direction.LEFT));
    }
    @Test
    void isCollideTwoObjectsMoveDownTankThenCollide() {
        assertFalse(CollidesController.isNotCollideTwoObjects(tree1, tank, Direction.DOWN));
    }

    @Test
    void isCollideTwoObjectsMoveUpTankThenCollide() {
        assertFalse(CollidesController.isNotCollideTwoObjects(tree3, tank, Direction.UP));
    }

    @Test
    void levelGameApplyMoveUpTankThenNothing(){
        levelGame.apply(Direction.UP, tank, deltaTime);
        assertTrue(new GridPoint2(2, 2).equals(tank.getCoordinates()));
    }
    @Test
    void levelGameApplyMoveDownTankThenNothing(){
        levelGame.apply(Direction.DOWN, tank, deltaTime);
        assertTrue(new GridPoint2(2, 2).equals(tank.getCoordinates()));
    }
    @Test
    void levelGameApplyMoveLeftTankThenNothing(){
        levelGame.apply(Direction.LEFT, tank, deltaTime);
        assertTrue(new GridPoint2(2, 2).equals(tank.getCoordinates()));
    }
    @Test
    void levelGameApplyMoveRightTankThenApply(){
        levelGame.apply(Direction.RIGHT, tank, deltaTime);
        levelGame.update(deltaTime);
        assertTrue(new GridPoint2(3, 2).equals(tank.getCoordinates()));
    }
    @Test
    void isMovingTankLeftInTreeThenFalse(){
        levelGame.apply(Direction.LEFT, tank, deltaTime);
        assertFalse(tank.isMoving());
    }
    @Test
    void isMovingTankUpInTreeThenFalse(){
        levelGame.apply(Direction.UP, tank, deltaTime);
        assertFalse(tank.isMoving());
    }
    @Test
    void isMovingTankDownInTreeThenFalse(){
        levelGame.apply(Direction.DOWN, tank, deltaTime);
        assertFalse(tank.isMoving());
    }
    @Test
    void isMovingTankRightInTreeThenTrue(){
        levelGame.apply(Direction.RIGHT, tank, deltaTime);
        assertTrue(tank.isMoving());
    }
}
