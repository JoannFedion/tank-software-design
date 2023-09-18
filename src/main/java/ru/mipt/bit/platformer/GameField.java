package ru.mipt.bit.platformer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Rectangle;
import ru.mipt.bit.platformer.util.TileMovement;

import java.util.ArrayList;
import java.util.List;

import static com.badlogic.gdx.Input.Keys.*;
import static com.badlogic.gdx.Input.Keys.D;
import static com.badlogic.gdx.math.MathUtils.isEqual;
import static ru.mipt.bit.platformer.util.GdxGameUtils.*;

public class GameField {
    private static final float MOVEMENT_SPEED = 0.4f;

    private Batch batch;
    private TiledMap level;
    private MapRenderer levelRenderer;
    private TileMovement tileMovement;
    private ArrayList<GameObject> arrayListObjects;


    public GameField(String gameFieldPath) {
        this.batch = new SpriteBatch();
        this.level = new TmxMapLoader().load(gameFieldPath);
        this.levelRenderer = createSingleLayerMapRenderer(level, batch);
        this.tileMovement = new TileMovement(getGroundLayer(), Interpolation.smooth);
        this.arrayListObjects = new ArrayList<>();
    }

    private TiledMapTileLayer getGroundLayer() {
        return getSingleLayer(level);
    }

    public void addObjectsDuringGame(GameObject objects) {
        arrayListObjects.add(objects);
    }

    public void levelRenderer() {
        levelRenderer.render();
        batch.begin();
        for (GameObject obj : arrayListObjects) {
            drawTextureRegionUnscaled(batch, obj.getObjectGraphics(), obj.getObjectRectangle(), obj.getObjectRotation());
        }
        batch.end();
    }

    public void dispose() {
        for (GameObject obj : arrayListObjects) {
            obj.dispose();
        }
        level.dispose();
        batch.dispose();
    }

    public int getParametersMovieDirection() {

        if (Gdx.input.isKeyPressed(UP) || Gdx.input.isKeyPressed(W)) {
            return Input.Keys.UP;
        }
        if (Gdx.input.isKeyPressed(LEFT) || Gdx.input.isKeyPressed(A)) {
            return Input.Keys.LEFT;
        }
        if (Gdx.input.isKeyPressed(DOWN) || Gdx.input.isKeyPressed(S)) {
            return Input.Keys.DOWN;
        }
        if (Gdx.input.isKeyPressed(RIGHT) || Gdx.input.isKeyPressed(D)) {
            return Input.Keys.RIGHT;

        }
        return 0;
    }

    public void move(Tank obj, GameObject objObstacle) {
        int direction = getParametersMovieDirection();
        if (direction != 0) {
            objMove(obj, objObstacle, direction);
            moveTankBetweenTileCenters(obj);
            float deltaTime = Gdx.graphics.getDeltaTime();
            obj.setObjectMovementProgress(continueProgress(obj.getObjectMovementProgress(), deltaTime, MOVEMENT_SPEED));
            if (isEqual(obj.getObjectMovementProgress(), 1f)) {
                obj.getObjectCoordinates().set(obj.getObjectDestinationCoordinates());
            }
        }
    }

    private boolean equalsTwoObjectCoordinatesAfterStep(GameObject obj1, GameObject obj2, int direction) {
        return setDirectionChangesToCoordinates(obj1.getObjectCoordinates(), direction).equals(obj2.getObjectCoordinates());
    }

    private List<Integer> getDirectionParameters(int direction) {
        Direction dir_direction = new Direction();
        return dir_direction.getParametersMovieDirection().get(direction);
    }

    private GridPoint2 setDirectionChangesToCoordinates(GridPoint2 objCoordinates, int direction) {

        List<Integer> parameters_direction = getDirectionParameters(direction);
        int changesX = parameters_direction.get(0);
        int changesY = parameters_direction.get(1);
        System.out.println(changesX);
        System.out.println(changesY);
        objCoordinates.add(changesX, changesY);
        return objCoordinates;
    }

    private int getRotation(int direction) {
        List<Integer> parameters_direction = getDirectionParameters(direction);
        return parameters_direction.get(2);
    }

    private void objMove(Tank obj, GameObject objObstacle, int direction) {
        System.out.println(obj.getObjectMovementProgress());
        if (isMovementProcess(obj)) {

            if (!equalsTwoObjectCoordinatesAfterStep(obj, objObstacle, direction)) {
                setDirectionChangesToCoordinates(obj.getObjectDestinationCoordinates(), direction);
                obj.setObjectMovementProgress(0);
            }

            obj.setObjectRotation(getRotation(direction));
        }
    }

    private boolean isMovementProcess(GameObject obj) {
        return isEqual(obj.getObjectMovementProgress(), 1f);
    }

    public Rectangle moveRectangle(Rectangle object, GridPoint2 objectCoordinates) {
        return moveRectangleAtTileCenter(getGroundLayer(), object, objectCoordinates);
    }

    public void moveTankBetweenTileCenters(Tank obj) {
        tileMovement.moveRectangleBetweenTileCenters(obj.getObjectRectangle(),
                obj.getObjectCoordinates(),
                obj.getObjectDestinationCoordinates(),
                obj.getObjectMovementProgress());
    }
}

