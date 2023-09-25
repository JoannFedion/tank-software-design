package ru.mipt.bit.platformer;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.math.GridPoint2;

import static com.badlogic.gdx.Input.Keys.*;
import static com.badlogic.gdx.graphics.GL20.GL_COLOR_BUFFER_BIT;

public class GameDesktopLauncher implements ApplicationListener {

    private GameFieldGraphics gameFieldGraphics;

    private ObjectGraphics tankGraphics;
    private ObjectGraphics treeGraphics;

    private Tank tank;
    private Tree tree;
    private InputController inputController;

    @Override
    public void create() {
        gameFieldGraphics = new GameFieldGraphics("level.tmx");

        tank = new Tank(new GridPoint2(1,1), Direction.RIGHT);
        tree = new Tree(new GridPoint2(1, 3));
        // Texture decodes an image file and loads it into GPU memory, it represents a native resource
        tankGraphics = gameFieldGraphics.createGraphicsObjects("images/tank_blue.png", tank);
        treeGraphics = gameFieldGraphics.createGraphicsObjects("images/greenTree.png", tree);

        initKeyMappings();
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
    public void render() {
        clearScreen();

        float deltaTime = Gdx.graphics.getDeltaTime();

        Direction direction = inputController.getDirection();
        updateGameState(deltaTime, direction);
        renderGame();
    }

    private void updateGameState(float deltaTime, Direction direction) {
        if (direction != null & !tank.isMoving()) {
            GridPoint2 tankTargetCoordinates = direction.apply(tank.getCoordinates());
            if (!collides(tree.getCoordinates(), tankTargetCoordinates)) {
                tank.moveTo(tankTargetCoordinates);
            }
            tank.rotate(direction);
        }
        // calculate interpolated player screen coordinates
        gameFieldGraphics.calculateInterpolatedScreenCoordinates(tankGraphics, tank);

        tank.updateState(deltaTime);
    }

    private void renderGame() {
        gameFieldGraphics.renderAllObjects();
    }

    private boolean collides(GridPoint2 object1, GridPoint2 object2) {
        return object1.equals(object2);
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
