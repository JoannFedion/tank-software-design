package ru.mipt.bit.platformer.GraphicsObjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Rectangle;
import ru.mipt.bit.platformer.GameModels.Objects.Bullet;
import ru.mipt.bit.platformer.GraphicsGameObjects;
import ru.mipt.bit.platformer.util.TileMovement;

import static ru.mipt.bit.platformer.util.GdxGameUtils.*;

public class BulletGraphics implements GraphicsGameObjects {

    private Texture texture;
    private TextureRegion graphics;
    private Rectangle bulletRectangle;
    private GridPoint2 coordinationsBullet;
    private Bullet modelBullet;


    // player current position coordinates on level 10x8 grid (e.g. x=0, y=1)
    public BulletGraphics(Bullet obj, TiledMapTileLayer groundLayer, String graphicPath) {
        this.texture = new Texture(graphicPath);
        // TextureRegion represents Texture portion, there may be many TextureRegion instances of the same Texture
        this.graphics = new TextureRegion(texture);
        this.bulletRectangle = createBoundingRectangle(graphics);
        this.modelBullet = obj;
        this.coordinationsBullet = modelBullet.getCoordinates();
        moveRectangleAtTileCenter(groundLayer, bulletRectangle, coordinationsBullet);
        // set player initial position

    }

    @Override
    public void render(Batch batch) {
        drawTextureRegionUnscaled(batch, graphics, bulletRectangle, modelBullet.getDirection().getRotation());
    }

    @Override
    public void dispose() {
        texture.dispose();
    }
    @Override
    public void calculateInterpolatedScreenCoordinates(TileMovement tileMovement) {
        tileMovement.moveRectangleBetweenTileCenters(
                bulletRectangle,
                modelBullet.getCoordinates(),
                modelBullet.getDestinationCoordinates(),
                modelBullet.getMovementProgress()
        );
    }

}
