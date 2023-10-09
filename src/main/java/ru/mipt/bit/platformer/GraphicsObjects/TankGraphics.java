package ru.mipt.bit.platformer.GraphicsObjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Rectangle;
import ru.mipt.bit.platformer.Interfaces.Action;
import ru.mipt.bit.platformer.Interfaces.GraphicsGameObjects;
import ru.mipt.bit.platformer.ModelClasses.Tank;
import ru.mipt.bit.platformer.util.TileMovement;

import static ru.mipt.bit.platformer.util.GdxGameUtils.*;

public class TankGraphics implements GraphicsGameObjects {
    private Texture texture;
    private TextureRegion graphics;
    private Rectangle tankRectangle;
    private GridPoint2 coordinationsTank;
    private float rotationTank;
    private Tank modelTank;


    // player current position coordinates on level 10x8 grid (e.g. x=0, y=1)
    public TankGraphics(Tank obj, TiledMapTileLayer groundLayer, String tankGraphicPathToFile) {
        this.texture = new Texture(tankGraphicPathToFile);
        // TextureRegion represents Texture portion, there may be many TextureRegion instances of the same Texture
        this.graphics = new TextureRegion(texture);
        this.tankRectangle = createBoundingRectangle(graphics);
        this.modelTank = obj;
        this.rotationTank = modelTank.getDirection().getRotation();
        this.coordinationsTank = modelTank.getCoordinates();
        moveRectangleAtTileCenter(groundLayer, tankRectangle, coordinationsTank);
        // set player initial position

    }
    public void render(Batch batch) {
        drawTextureRegionUnscaled(batch, graphics, tankRectangle, modelTank.getDirection().getRotation());
    }


    public void dispose() {
        texture.dispose();
    }

    public void calculateInterpolatedScreenCoordinates(TileMovement tileMovement) {
        tileMovement.moveRectangleBetweenTileCenters(
                tankRectangle,
                modelTank.getCoordinates(),
                modelTank.getDestinationCoordinates(),
                modelTank.getMovementProgress()
        );
    }
}
