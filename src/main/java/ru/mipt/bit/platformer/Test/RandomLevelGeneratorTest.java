package ru.mipt.bit.platformer.Test;

import com.badlogic.gdx.math.GridPoint2;
import org.junit.jupiter.api.Test;
import ru.mipt.bit.platformer.ModelObject;
import ru.mipt.bit.platformer.LevelGenerator;
import ru.mipt.bit.platformer.GeneratorsLevelInfo.LevelInfo;
import ru.mipt.bit.platformer.GeneratorsLevelInfo.RandomLevelGenerator;
import ru.mipt.bit.platformer.LevelCharacteristic;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class RandomLevelGeneratorTest {

    @Test
    void testRandomGeneratorLevelThatGenerateRightNumbersObjectsDontForgetAboutInnerGenerationPlayer(){
        LevelCharacteristic levelCharacteristic = createSomeLevelCharacteristic();
        int numbersEnemyTank = createSomeNumbersEnemyTank();
        int numbersTrees = createSomeNumbersTrees();
        LevelGenerator randomLevelGenerator = new RandomLevelGenerator(levelCharacteristic, numbersTrees, numbersEnemyTank);
        LevelInfo levelInfo = randomLevelGenerator.generateLevelInfo();


        assertTrue(levelInfo.getLevelGame().getObjectsInGameList().size() == (numbersTrees + numbersEnemyTank + 1));
    }

    @Test
    void testRandomGeneratorLevelThatGenerationObjectsHaveDifferentCoordinates(){
        LevelCharacteristic levelCharacteristic = createSomeLevelCharacteristic();
        int numbersEnemyTank = createSomeNumbersEnemyTank();
        int numbersTrees = createSomeNumbersTrees();
        LevelGenerator randomLevelGenerator = new RandomLevelGenerator(levelCharacteristic, numbersTrees, numbersEnemyTank);
        LevelInfo levelInfo = randomLevelGenerator.generateLevelInfo();


        Set<GridPoint2> uniqueCoordinates = new HashSet<>();
        for (ModelObject object : levelInfo.getLevelGame().getObjectsInGameList()){
            uniqueCoordinates.add(object.getCoordinates());
        }


        assertEquals(uniqueCoordinates.size(), levelInfo.getLevelGame().getObjectsInGameList().size());
    }


    private int createSomeNumbersTrees() {
        return 4;
    }
    private LevelCharacteristic createSomeLevelCharacteristic() {
        return new LevelCharacteristic(10, 10, 3);
    }
    private int createSomeNumbersEnemyTank() {
        return 3;
    }

}