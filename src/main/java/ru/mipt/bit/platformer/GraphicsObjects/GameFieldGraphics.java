package ru.mipt.bit.platformer.GraphicsObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Interpolation;
import ru.mipt.bit.platformer.Interfaces.Action;
import ru.mipt.bit.platformer.GameMechanic.InputController;
import ru.mipt.bit.platformer.Interfaces.ModelObject;
import ru.mipt.bit.platformer.Interfaces.GraphicsGameObjects;
import ru.mipt.bit.platformer.Interfaces.GraphicsMovingObjects;
import ru.mipt.bit.platformer.ModelClasses.LevelGame;
import ru.mipt.bit.platformer.ModelClasses.Tank;
import ru.mipt.bit.platformer.ModelClasses.Tree;
import ru.mipt.bit.platformer.util.TileMovement;

import java.util.ArrayList;
import java.util.List;

import static ru.mipt.bit.platformer.util.GdxGameUtils.createSingleLayerMapRenderer;
import static ru.mipt.bit.platformer.util.GdxGameUtils.getSingleLayer;

public class GameFieldGraphics {
    private Batch batch;
    private TiledMap level;
    private MapRenderer levelRenderer;
    private TiledMapTileLayer groundLayer;
    private TileMovement tileMovement;
    private List<GraphicsGameObjects> listGraphicsNotMovingObjects;
    private List<GraphicsMovingObjects> listGraphicsMovingObjects;
    private List<ModelObject>  listAllModelObjectsInGame;
    private float deltaTime;
    private LevelGame levelGame;

    public GameFieldGraphics(String pathGameField, LevelGame levelGame) {
        this.batch = new SpriteBatch();
        this.level = new TmxMapLoader().load(pathGameField);
        this.levelRenderer = createSingleLayerMapRenderer(level, batch);
        this.groundLayer = getSingleLayer(level);
        this.tileMovement = new TileMovement(groundLayer, Interpolation.smooth);
        this.listGraphicsNotMovingObjects = new ArrayList<>();
        this.listGraphicsMovingObjects = new ArrayList<>();
        this.listAllModelObjectsInGame = new ArrayList<>();

        this.levelGame = levelGame;
    }

    public void renderAllObjects() {
        // render each tile of the level
        levelRenderer.render();
        // start recording all drawing commands
        batch.begin();
        listGraphicsNotMovingObjects.forEach((el) -> el.render(batch));
        listGraphicsMovingObjects.forEach((el) -> el.render(batch));
        batch.end();

        calculateInterpolatedScreenCoordinatesForAllObjects();
    }

    public void dispose() {
        for (GraphicsGameObjects listGraphicsObject : listGraphicsNotMovingObjects) listGraphicsObject.dispose();
        for (GraphicsMovingObjects listGraphicsObject : listGraphicsMovingObjects) listGraphicsObject.dispose();
        level.dispose();
        batch.dispose();
    }

    public void createGraphicsObject(Tree obj) {
        TreeGraphics newGraphicsObject = new TreeGraphics(obj, groundLayer);
        listGraphicsNotMovingObjects.add(newGraphicsObject);
        listAllModelObjectsInGame.add(obj);
    }

    public void createGraphicsObject(Tank obj, InputController inputController) {
        TankGraphics newGraphicsObject = new TankGraphics(obj, groundLayer, inputController);
        listGraphicsMovingObjects.add(newGraphicsObject);
        listAllModelObjectsInGame.add(obj);

    }


    private void calculateInterpolatedScreenCoordinatesForAllObjects() {
        listGraphicsMovingObjects.forEach((el) -> el.calculateInterpolatedScreenCoordinates(tileMovement));
    }

//    public void getObjectsDirections() {
//        deltatime = Gdx.graphics.getDeltaTime();
//        for (MovingObjects graphicsMovingObject : listGraphicsMovingObjects) {
//            Direction directionObject = graphicsMovingObject.getDirection();
//            for (GameObjectInterface obj2 : listAllModelObjectsInGame) {
//                graphicsMovingObject.applyToModel(deltatime, obj2, directionObject);
//            }
//        }
//    }

    public float getDeltaTime() {
        return deltaTime;
    }

    public void getObjectsAction() {
        deltaTime = Gdx.graphics.getDeltaTime();
        for (GraphicsMovingObjects obj : listGraphicsMovingObjects){
            Action action = obj.getAction();
            if (action != null) levelGame.apply(action, obj.getModelTank(), deltaTime);
        }
    }
}
