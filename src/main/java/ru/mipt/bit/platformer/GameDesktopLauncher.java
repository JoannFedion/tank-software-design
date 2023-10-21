package ru.mipt.bit.platformer;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import ru.mipt.bit.platformer.Controllers.AIController;
import ru.mipt.bit.platformer.Controllers.InputController;
import ru.mipt.bit.platformer.Actions.*;
import ru.mipt.bit.platformer.GameModels.CollidesController;
import ru.mipt.bit.platformer.GameModels.ModelObject;
import ru.mipt.bit.platformer.GraphicsObjects.GameFieldGraphics;
import ru.mipt.bit.platformer.GameModels.MovingObjects;
import ru.mipt.bit.platformer.GameModels.LevelGame;
import ru.mipt.bit.platformer.GeneratorsLevelInfo.LevelGenerator;
import ru.mipt.bit.platformer.GeneratorsLevelInfo.LevelInfo;
import ru.mipt.bit.platformer.GeneratorsLevelInfo.RandomLevelGenerator;

import static com.badlogic.gdx.Input.Keys.*;
import static com.badlogic.gdx.Input.Keys.D;
import static com.badlogic.gdx.graphics.GL20.GL_COLOR_BUFFER_BIT;

public class GameDesktopLauncher implements ApplicationListener {

    private GameFieldGraphics gameFieldGraphics;
    private LevelGenerator levelGenerator;
    private InputController inputController;
    private LevelGame levelGame;
    private CollidesController collidesController;
    private ModelObject playerObject;
    private ru.mipt.bit.platformer.Controllers.AIController AIController;


    @Override
    public void create() {
        LevelCharacteristic levelCharacteristic = new LevelCharacteristic(6, 10);
        levelGenerator = new RandomLevelGenerator(levelCharacteristic, 10, 20);
        LevelInfo levelInfo = levelGenerator.generateLevelInfo();
        playerObject = levelInfo.getPlayerObject();
        levelGame = levelInfo.getLevelGame();
        collidesController = new CollidesController(levelGame.getObjectsInGameList(), levelCharacteristic);
        gameFieldGraphics = new GameFieldGraphics("level.tmx", levelGame);
        inputController = new InputController(playerObject);
        AIController = new AIController(levelGame.getObjectsInGameList(), playerObject);
        initKeyMappingsForPlayerInputController();
        initKeyMappingsForAIController();
    }

    @Override
    public void render() {
        clearScreen();
        inputController.execute();
        AIController.execute();
        levelGame.update(gameFieldGraphics.getDeltaTime());
        gameFieldGraphics.renderAllObjects();
        collidesController.update();
    }

    private static void clearScreen() {
        Gdx.gl.glClearColor(0f, 0f, 0.2f, 1f);
        Gdx.gl.glClear(GL_COLOR_BUFFER_BIT);
    }

    @Override
    public void resize(int width, int height) {
        // do not react to window resizing
    }
    private void initKeyMappingsForAIController() {
        AIController.addMapping(UP, new MoveAction(Direction.UP, collidesController));
        AIController.addMapping(DOWN, new MoveAction(Direction.DOWN, collidesController));
        AIController.addMapping(LEFT, new MoveAction(Direction.LEFT, collidesController));
        AIController.addMapping(RIGHT, new MoveAction(Direction.RIGHT, collidesController));
    }

    private void initKeyMappingsForPlayerInputController() {
        inputController.addMapping(UP, new MoveAction(Direction.UP, collidesController));
        inputController.addMapping(W, new MoveAction(Direction.UP, collidesController));
        inputController.addMapping(LEFT, new MoveAction(Direction.LEFT, collidesController));
        inputController.addMapping(A, new MoveAction(Direction.LEFT, collidesController));
        inputController.addMapping(DOWN, new MoveAction(Direction.DOWN, collidesController));
        inputController.addMapping(S, new MoveAction(Direction.DOWN, collidesController));
        inputController.addMapping(RIGHT, new MoveAction(Direction.RIGHT, collidesController));
        inputController.addMapping(D, new MoveAction(Direction.RIGHT, collidesController));
    }
    @Override
    public void pause() {
        // game doesn't get paused
    }

    @Override
    public void resume() {
        // game doesn't get paused
    }

    @Override
    public void dispose() {
        // dispose of all the native resources (classes which implement com.badlogic.gdx.utils.Disposable)
        gameFieldGraphics.dispose();
    }

    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        // level width: 10 tiles x 128px, height: 8 tiles x 128px
        config.setWindowedMode(1280, 1024);
        new Lwjgl3Application(new GameDesktopLauncher(), config);
    }
}
