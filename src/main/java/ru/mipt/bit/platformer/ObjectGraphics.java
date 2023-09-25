package ru.mipt.bit.platformer;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Rectangle;

import static ru.mipt.bit.platformer.util.GdxGameUtils.*;

public class ObjectGraphics {
    private Texture texture;
    private TextureRegion graphics;
    private Rectangle objectRectangle;
    private GridPoint2 coordinationsObject;
    private float rotationObject;
    private PropertyObject mathObject;

    // player current position coordinates on level 10x8 grid (e.g. x=0, y=1)
    public ObjectGraphics(String pathTexture, PropertyObject obj, TiledMapTileLayer groundLayer) {
        this.texture = new Texture(pathTexture);
        // TextureRegion represents Texture portion, there may be many TextureRegion instances of the same Texture
        this.graphics = new TextureRegion(texture);
        this.objectRectangle = createBoundingRectangle(graphics);
        this.mathObject = obj;
        this.coordinationsObject = mathObject.getCoordinates();
        this.rotationObject = mathObject.getRotate();
        moveRectangleAtTileCenter(groundLayer, objectRectangle, coordinationsObject);
        // set player initial position
    }
    public void renderObjectGraphics(Batch batch) {
        rotationObject = mathObject.getRotate();
        drawTextureRegionUnscaled(batch, graphics, objectRectangle, rotationObject);
    }

    public Rectangle getObjectRectangle() {
        return objectRectangle;
    }

    public void dispose(){
        texture.dispose();
    }

}
