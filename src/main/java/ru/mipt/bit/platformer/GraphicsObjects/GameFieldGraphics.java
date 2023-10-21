package ru.mipt.bit.platformer.GraphicsObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Interpolation;
import ru.mipt.bit.platformer.GameModels.ModelObject;
import ru.mipt.bit.platformer.GameModels.LevelGame;
import ru.mipt.bit.platformer.GameModels.Tank;
import ru.mipt.bit.platformer.GameModels.Tree;
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
    private List<GraphicsGameObjects> listGraphicsObjects;
    private List<ModelObject> listAllModelObjectsInGame;

    public GameFieldGraphics(String pathGameField, LevelGame levelGame) {
        this.batch = new SpriteBatch();
        this.level = new TmxMapLoader().load(pathGameField);
        this.levelRenderer = createSingleLayerMapRenderer(level, batch);
        this.groundLayer = getSingleLayer(level);
        this.tileMovement = new TileMovement(groundLayer, Interpolation.smooth);
        this.listGraphicsObjects = new ArrayList<>();
        this.listAllModelObjectsInGame = levelGame.getObjectsInGameList();
        createGraphicsObjects();

    }

    public void renderAllObjects() {
        // render each tile of the level
        levelRenderer.render();
        // start recording all drawing commands
        batch.begin();
        listGraphicsObjects.forEach((el) -> el.render(batch));
        batch.end();

        calculateInterpolatedScreenCoordinatesForAllObjects();
    }

    public void dispose() {
        for (GraphicsGameObjects listGraphicsObject : listGraphicsObjects) listGraphicsObject.dispose();
        level.dispose();
        batch.dispose();
    }

    private void createGraphicsObjects() {
        for (ModelObject obj : listAllModelObjectsInGame) {
            if (obj instanceof Tank) {
                listGraphicsObjects.add(new TankGraphics((Tank) obj, groundLayer, "images/tank_blue.png"));
            } else if (obj instanceof Tree) {
                listGraphicsObjects.add(new TreeGraphics((Tree) obj, groundLayer, "images/greenTree.png"));
            }
        }
    }


    private void calculateInterpolatedScreenCoordinatesForAllObjects() {
        for (GraphicsGameObjects graphicObject : listGraphicsObjects) {
            if (graphicObject instanceof TankGraphics) {
                graphicObject.calculateInterpolatedScreenCoordinates(tileMovement);
            }
        }
    }

    public float getDeltaTime() {
        return Gdx.graphics.getDeltaTime();
    }

}
