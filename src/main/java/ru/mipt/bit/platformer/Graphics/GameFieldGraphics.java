package ru.mipt.bit.platformer.Graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Interpolation;
import ru.mipt.bit.platformer.FieldGraphics;
import ru.mipt.bit.platformer.GameModels.Objects.Bullet;
import ru.mipt.bit.platformer.GameModels.Objects.Tank;
import ru.mipt.bit.platformer.GameModels.Objects.Tree;
import ru.mipt.bit.platformer.Graphics.GraphicsObjects.BulletGraphics;
import ru.mipt.bit.platformer.Graphics.GraphicsObjects.TankGraphics;
import ru.mipt.bit.platformer.Graphics.GraphicsObjects.TreeGraphics;
import ru.mipt.bit.platformer.GraphicsGameObjects;
import ru.mipt.bit.platformer.LevelGame;
import ru.mipt.bit.platformer.ModelObject;
import ru.mipt.bit.platformer.util.TileMovement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ru.mipt.bit.platformer.util.GdxGameUtils.createSingleLayerMapRenderer;
import static ru.mipt.bit.platformer.util.GdxGameUtils.getSingleLayer;

public class GameFieldGraphics implements FieldGraphics {
    private final Batch batch;
    private final TiledMap level;
    private final MapRenderer levelRenderer;
    private final TiledMapTileLayer groundLayer;
    private final TileMovement tileMovement;
    private final List<GraphicsGameObjects> listGraphicsObjects;
    private final List<ModelObject> listAllModelObjectsInGame;
    private final Map<ModelObject, GraphicsGameObjects> objectGraphicsGameObjectsMap;

    public GameFieldGraphics(String pathGameField, LevelGame levelGame) {
        this.batch = new SpriteBatch();
        this.level = new TmxMapLoader().load(pathGameField);
        this.levelRenderer = createSingleLayerMapRenderer(level, batch);
        this.groundLayer = getSingleLayer(level);
        this.tileMovement = new TileMovement(groundLayer, Interpolation.smooth);
        this.listGraphicsObjects = new ArrayList<>();
        this.listAllModelObjectsInGame = levelGame.getObjectsInGameList();
        this.objectGraphicsGameObjectsMap = new HashMap<>();

        createGraphicsObjects();
    }

    public void deleteGraphicObject(ModelObject object) {
        listAllModelObjectsInGame.remove(object);
        listGraphicsObjects.remove(objectGraphicsGameObjectsMap.remove(object));
    }

    @Override
    public void renderAllObjects() {
        // render each tile of the level
        levelRenderer.render();
        // start recording all drawing commands
        batch.begin();
        listGraphicsObjects.forEach((el) -> el.render(batch));
        batch.end();
        calculateInterpolatedScreenCoordinatesForAllObjects();
    }

    @Override
    public void dispose() {
        for (GraphicsGameObjects listGraphicsObject : listGraphicsObjects) listGraphicsObject.dispose();
        level.dispose();
        batch.dispose();
    }

    public void createGraphicObject(ModelObject obj) {
        GraphicsGameObjects graphObject = null;
        if (obj instanceof Tank) {
            graphObject = new TankGraphics((Tank) obj, groundLayer, "images/tank_blue.png");
        } else if (obj instanceof Tree) {
            graphObject = new TreeGraphics((Tree) obj, groundLayer, "images/greenTree.png");
        } else if (obj instanceof Bullet) {
            graphObject = new BulletGraphics((Bullet) obj, groundLayer, "images/bullet.png");
        }
        listGraphicsObjects.add(graphObject);
        objectGraphicsGameObjectsMap.put(obj, graphObject);
        renderAllObjects();
    }

    private void createGraphicsObjects() {
        for (ModelObject obj : listAllModelObjectsInGame) {
            createGraphicObject(obj);
        }
    }


    private void calculateInterpolatedScreenCoordinatesForAllObjects() {
        for (GraphicsGameObjects graphicObject : listGraphicsObjects) {
            graphicObject.calculateInterpolatedScreenCoordinates(tileMovement);
        }
    }

    public float getDeltaTime() {
        return Gdx.graphics.getDeltaTime();
    }
    @Override
    public Map<ModelObject, GraphicsGameObjects> getObjectGraphicsGameObjectsMap() {
        return objectGraphicsGameObjectsMap;
    }

    public Batch getBatch() {
        return batch;
    }
}
