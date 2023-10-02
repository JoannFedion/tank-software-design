package ru.mipt.bit.platformer.GraphicsObjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Rectangle;
import ru.mipt.bit.platformer.Interfaces.ModelObject;
import ru.mipt.bit.platformer.Interfaces.GraphicsGameObjects;

import static ru.mipt.bit.platformer.util.GdxGameUtils.*;

public class TreeGraphics implements GraphicsGameObjects {
    private Texture texture;
    private TextureRegion graphics;
    private Rectangle treeRectangle;
    private GridPoint2 coordinationsTree;
    private static final String TREEGRAPHICSPATH = "images/greenTree.png";
    private float rotationTree;
    private ModelObject mathTree;

    // player current position coordinates on level 10x8 grid (e.g. x=0, y=1)
    public TreeGraphics(ModelObject obj, TiledMapTileLayer groundLayer) {
        this.texture = new Texture(TREEGRAPHICSPATH);
        // TextureRegion represents Texture portion, there may be many TextureRegion instances of the same Texture
        this.graphics = new TextureRegion(texture);
        this.treeRectangle = createBoundingRectangle(graphics);
        this.mathTree = obj;
        this.coordinationsTree = mathTree.getCoordinates();
        moveRectangleAtTileCenter(groundLayer, treeRectangle, coordinationsTree);
        // set player initial position
    }

    @Override
    public void render(Batch batch) {
        drawTextureRegionUnscaled(batch, graphics, treeRectangle, 0f);
    }

    public void dispose(){
        texture.dispose();
    }

}
