package ru.mipt.bit.platformer;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.GameMechanic.Direction;
import ru.mipt.bit.platformer.GameMechanic.InputController;
import ru.mipt.bit.platformer.GraphicsObjects.GameFieldGraphics;
import ru.mipt.bit.platformer.ModelClasses.LevelGame;
import ru.mipt.bit.platformer.ModelClasses.Tank;
import ru.mipt.bit.platformer.ModelClasses.Tree;

import static com.badlogic.gdx.Input.Keys.*;
import static com.badlogic.gdx.Input.Keys.D;
import static com.badlogic.gdx.graphics.GL20.GL_COLOR_BUFFER_BIT;

public class GameDesktopLauncher implements ApplicationListener {

    private GameFieldGraphics gameFieldGraphics;
    private LevelGame levelGame;
    private InputController inputController;


    @Override
    public void create() {
        initKeyMappings();
        gameFieldGraphics = new GameFieldGraphics("level.tmx");
        levelGame = new LevelGame(gameFieldGraphics);

    }

    @Override
    public void render() {
        clearScreen();
//        gameFieldGraphics.getObjectsAction();
        //levelGame.update(gameFieldGraphics.getDeltaTime());
        gameFieldGraphics.renderAllObjects();
    }

    private static void clearScreen() {
        Gdx.gl.glClearColor(0f, 0f, 0.2f, 1f);
        Gdx.gl.glClear(GL_COLOR_BUFFER_BIT);
    }
    private void initKeyMappings() {
        inputController = new InputController();
        inputController.addMapping(UP, Direction.UP);
        inputController.addMapping(W, Direction.UP);
        inputController.addMapping(LEFT, Direction.LEFT);
        inputController.addMapping(A, Direction.LEFT);
        inputController.addMapping(DOWN, Direction.DOWN);
        inputController.addMapping(S, Direction.DOWN);
        inputController.addMapping(RIGHT, Direction.RIGHT);
        inputController.addMapping(D, Direction.RIGHT);
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
