package ru.mipt.bit.platformer.GraphicsObjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Rectangle;
import ru.mipt.bit.platformer.GameModels.ModelObject;
import ru.mipt.bit.platformer.GameModels.Tree;
import ru.mipt.bit.platformer.util.TileMovement;

import static ru.mipt.bit.platformer.util.GdxGameUtils.*;

public class TreeGraphics implements GraphicsGameObjects {
    private Texture texture;
    private TextureRegion graphics;
    private Rectangle treeRectangle;
    private GridPoint2 coordinationsTree;
    private float rotationTree;
    private ModelObject modelTree;

    // player current position coordinates on level 10x8 grid (e.g. x=0, y=1)
    public TreeGraphics(Tree obj, TiledMapTileLayer groundLayer, String graphicPath) {
        this.texture = new Texture(graphicPath);
        // TextureRegion represents Texture portion, there may be many TextureRegion instances of the same Texture
        this.graphics = new TextureRegion(texture);
        this.treeRectangle = createBoundingRectangle(graphics);
        this.modelTree = obj;
        this.coordinationsTree = modelTree.getCoordinates();
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

    @Override
    public void calculateInterpolatedScreenCoordinates(TileMovement tileMovement) {
        //
    }
}
