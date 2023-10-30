package ru.mipt.bit.platformer;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import ru.mipt.bit.platformer.AdapterAI.AdapterAIController;
import ru.mipt.bit.platformer.Controllers.AIController;
import ru.mipt.bit.platformer.Controllers.InputController;
import ru.mipt.bit.platformer.Actions.*;
import ru.mipt.bit.platformer.GameModels.MovingObjects;
import ru.mipt.bit.platformer.GeneratorsLevelInfo.LevelInfo;
import ru.mipt.bit.platformer.GeneratorsLevelInfo.RandomLevelGenerator;
import ru.mipt.bit.platformer.Listeners.*;
import ru.mipt.bit.platformer.ObjectControllers.BulletController;
import ru.mipt.bit.platformer.ObjectControllers.CollidesController;

import static com.badlogic.gdx.Input.Keys.*;
import static com.badlogic.gdx.graphics.GL20.GL_COLOR_BUFFER_BIT;

public class GameDesktopLauncher implements ApplicationListener {

    private GameFieldGraphics gameFieldGraphics;
    private LevelGenerator levelGenerator;
    private ObjectController<Integer> inputController;
    private LevelGame levelGame;
    private CollidesController collidesController;
    private BulletController bulletController;
    private ModelObject playerObject;
    private ObjectController<Integer> AIController;
    private ObjectController<org.awesome.ai.Action> AIInternetController;

    @Override
    public void create() {
        LevelCharacteristic levelCharacteristic = new LevelCharacteristic(6, 10, 2);

        levelGenerator = new RandomLevelGenerator(levelCharacteristic, 2, 4);

        LevelInfo levelInfo = levelGenerator.generateLevelInfo();

        playerObject = levelInfo.getPlayerObject();

        levelGame = levelInfo.getLevelGame();

        collidesController = new CollidesController(levelGame, levelCharacteristic);

        gameFieldGraphics = new GameFieldGraphics("level.tmx", levelGame);

        CommonListener commonListener = new CommonListener(collidesController);

        bulletController = new BulletController(collidesController, levelGame);

        LevelListener graphicsListener = new GraphicsListener(gameFieldGraphics);
        LevelListener collisionControllerListener = new CollidesControllerListener(collidesController);
        ShootListener shootListener = new ShootListener(bulletController);
        ControllersListener controllersListener = new ControllersListener();

        Action shoot = new ShootAction(collidesController, shootListener);

        inputController = new InputController(playerObject);
        inputController.initKeyMappingForController(collidesController);
        inputController.addMapping(SPACE, shoot);
        controllersListener.addControllers(inputController);

        AIController = new AIController(levelGame.getObjectsInGameList(), playerObject);
        AIController.initKeyMappingForController(collidesController);
        AIController.addMapping(SPACE, shoot);
        controllersListener.addControllers(AIController);

//        AIInternetController = new AdapterAIController(levelGame.getObjectsInGameList(), (MovingObjects) playerObject, levelCharacteristic, collidesController);
//        AIInternetController.addMapping(org.awesome.ai.Action.Shoot, shoot);
//        controllersListener.addControllers(AIInternetController);

        commonListener.addListener(graphicsListener);
        commonListener.addListener(collisionControllerListener);
        commonListener.addListener(controllersListener);

        collidesController.addListener(commonListener);
        levelGame.addListener(commonListener);
    }

    @Override
    public void render() {
        clearScreen();
        inputController.execute();
        AIController.execute();
//        AIInternetController.execute();
        bulletController.execute();
        levelGame.update(gameFieldGraphics.getDeltaTime());
        collidesController.update();
        gameFieldGraphics.renderAllObjects();
    }

    private static void clearScreen() {
        Gdx.gl.glClearColor(0f, 0f, 0.2f, 1f);
        Gdx.gl.glClear(GL_COLOR_BUFFER_BIT);
    }

    @Override
    public void resize(int width, int height) {
        // do not react to window resizing
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
