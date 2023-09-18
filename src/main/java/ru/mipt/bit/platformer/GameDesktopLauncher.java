package ru.mipt.bit.platformer;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import static com.badlogic.gdx.graphics.GL20.GL_COLOR_BUFFER_BIT;

public class GameDesktopLauncher implements ApplicationListener {

    private GameField gameField;
    private Tank tank;

    private Tree treeObstacle;

    @Override
    public void create() {
        gameField = new GameField("level.tmx");

        tank = new Tank("images/tank_blue.png", 1, 1, 0f);
        treeObstacle = new Tree("images/greenTree.png", 1, 3);
        Direction dir = new Direction();
        gameField.addObjectsDuringGame(tank);
        gameField.addObjectsDuringGame(treeObstacle);
        gameField.moveRectangle(treeObstacle.getObjectRectangle(), treeObstacle.getObjectCoordinates());
    }

    @Override
    public void render() {
        // clear the screen
        Gdx.gl.glClearColor(0f, 0f, 0.2f, 1f);
        Gdx.gl.glClear(GL_COLOR_BUFFER_BIT);

        gameField.move(tank, treeObstacle);
        gameField.levelRenderer();
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
        gameField.dispose();
    }

    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        // level width: 10 tiles x 128px, height: 8 tiles x 128px
        config.setWindowedMode(1280, 1024);
        new Lwjgl3Application(new GameDesktopLauncher(), config);
    }
}
