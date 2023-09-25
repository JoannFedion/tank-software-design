package ru.mipt.bit.platformer;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Interpolation;
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
    private List<ObjectGraphics> listGraphicsObjects;
    public GameFieldGraphics(String pathGameField) {
        this.batch = new SpriteBatch();
        this.level = new TmxMapLoader().load(pathGameField);
        this.levelRenderer = createSingleLayerMapRenderer(level, batch);
        this.groundLayer = getSingleLayer(level);
        this.tileMovement = new TileMovement(groundLayer, Interpolation.smooth);
        this.listGraphicsObjects = new ArrayList<>();
    }
    public void renderAllObjects(){
        // render each tile of the level
        levelRenderer.render();
        // start recording all drawing commands
        batch.begin();
        listGraphicsObjects.forEach((el) -> el.renderObjectGraphics(batch));
        batch.end();
    }

    public void dispose(){
        for (ObjectGraphics listGraphicsObject : listGraphicsObjects) listGraphicsObject.dispose();
        level.dispose();
        batch.dispose();
    }

    public ObjectGraphics createGraphicsObjects(String pathTexture, PropertyObject obj){
        ObjectGraphics newGraphicsObject = new ObjectGraphics(pathTexture, obj, groundLayer);
        listGraphicsObjects.add(newGraphicsObject);
        return newGraphicsObject;
    }
    public void calculateInterpolatedScreenCoordinates(ObjectGraphics objGraphics, Tank tank){
        tileMovement.moveRectangleBetweenTileCenters(
                objGraphics.getObjectRectangle(),
                tank.getCoordinates(),
                tank.getDestinationCoordinates(),
                tank.getMovementProgress()
        );
    }
}
