package ru.mipt.bit.platformer.GraphicsObjects;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Interpolation;
import ru.mipt.bit.platformer.Interfaces.ModelObject;
import ru.mipt.bit.platformer.Interfaces.GraphicsGameObjects;
import ru.mipt.bit.platformer.LevelListener;
import ru.mipt.bit.platformer.ModelClasses.Tank;
import ru.mipt.bit.platformer.ModelClasses.Tree;
import ru.mipt.bit.platformer.util.TileMovement;

import java.util.ArrayList;
import java.util.List;

import static ru.mipt.bit.platformer.util.GdxGameUtils.createSingleLayerMapRenderer;
import static ru.mipt.bit.platformer.util.GdxGameUtils.getSingleLayer;

public class GameFieldGraphics implements LevelListener {
    private Batch batch;
    private TiledMap level;
    private MapRenderer levelRenderer;
    private TiledMapTileLayer groundLayer;
    private TileMovement tileMovement;
    private List<GraphicsGameObjects> listGraphicsObjects;
    private List<ModelObject> listAllModelObjectsInGame;
    private float deltaTime;

    public GameFieldGraphics(String pathGameField) {
        this.batch = new SpriteBatch();
        this.level = new TmxMapLoader().load(pathGameField);
        this.levelRenderer = createSingleLayerMapRenderer(level, batch);
        this.groundLayer = getSingleLayer(level);
        this.tileMovement = new TileMovement(groundLayer, Interpolation.smooth);
        this.listGraphicsObjects = new ArrayList<>();
//        this.listGraphicsMovingObjects = new ArrayList<>();
        this.listAllModelObjectsInGame = new ArrayList<>();
    }

    public void renderAllObjects() {
        // render each tile of the level
        levelRenderer.render();
        // start recording all drawing commands
        batch.begin();
        listGraphicsObjects.forEach((el) -> el.render(batch));
//        listGraphicsMovingObjects.forEach((el) -> el.render(batch));
        batch.end();

        calculateInterpolatedScreenCoordinatesForAllObjects();
    }

    public void dispose() {
        for (GraphicsGameObjects listGraphicsObject : listGraphicsObjects) listGraphicsObject.dispose();
//        for (GraphicsMovingObjects listGraphicsObject : listGraphicsMovingObjects) listGraphicsObject.dispose();
        level.dispose();
        batch.dispose();
    }

    private void createGraphicsObject(Tree obj) {
        TreeGraphics newGraphicsObject = new TreeGraphics(obj, groundLayer, "images/greenTree.png");
        listGraphicsObjects.add(newGraphicsObject);
        listAllModelObjectsInGame.add(obj);
    }

    private void createGraphicsObject(Tank obj) {
        TankGraphics newGraphicsObject = new TankGraphics(obj, groundLayer, "images/tank_blue.png");
        listGraphicsObjects.add(newGraphicsObject);
        listAllModelObjectsInGame.add(obj);
    }

    private void calculateInterpolatedScreenCoordinatesForAllObjects() {
        listGraphicsObjects.forEach((el) -> el.calculateInterpolatedScreenCoordinates(tileMovement));
    }

    public float getDeltaTime() {
        return deltaTime;
    }

    @Override
    public void onAddGameEntity(ModelObject object) {
        switch (object.getClass().getName()) {
            case "TreeGraphics":
                createGraphicsObject((Tree) object);
                break;
            case "TankGraphics":
                createGraphicsObject((Tank) object);
        }
    }
}
