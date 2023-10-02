package ru.mipt.bit.platformer.GraphicsObjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Rectangle;
import ru.mipt.bit.platformer.Interfaces.Action;
import ru.mipt.bit.platformer.GameMechanic.InputController;
import ru.mipt.bit.platformer.Interfaces.GraphicsMovingObjects;
import ru.mipt.bit.platformer.ModelClasses.Tank;
import ru.mipt.bit.platformer.util.TileMovement;

import static ru.mipt.bit.platformer.util.GdxGameUtils.*;

public class TankGraphics implements GraphicsMovingObjects {
    private Texture texture;
    private static final String GRAPHICSPATH = "images/tank_blue.png";
    private TextureRegion graphics;
    private Rectangle tankRectangle;
    private GridPoint2 coordinationsTank;
    private float rotationTank;
    private Tank ModelTank;
    private InputController inputController;


    // player current position coordinates on level 10x8 grid (e.g. x=0, y=1)
    public TankGraphics(Tank obj, TiledMapTileLayer groundLayer, InputController inputController) {
        this.texture = new Texture(GRAPHICSPATH);
        // TextureRegion represents Texture portion, there may be many TextureRegion instances of the same Texture
        this.graphics = new TextureRegion(texture);
        this.tankRectangle = createBoundingRectangle(graphics);
        this.ModelTank = obj;
        this.rotationTank = ModelTank.getDirection().getRotation();
        this.coordinationsTank = ModelTank.getCoordinates();
        this.inputController = inputController;
        moveRectangleAtTileCenter(groundLayer, tankRectangle, coordinationsTank);
        // set player initial position

    }

    @Override
    public void render(Batch batch) {
        drawTextureRegionUnscaled(batch, graphics, tankRectangle, getModelTank().getDirection().getRotation());
    }

    @Override
    public void dispose() {
        texture.dispose();
    }

    public void calculateInterpolatedScreenCoordinates(TileMovement tileMovement) {
        tileMovement.moveRectangleBetweenTileCenters(
                tankRectangle,
                ModelTank.getCoordinates(),
                ModelTank.getDestinationCoordinates(),
                ModelTank.getMovementProgress()
        );
    }

    public Tank getModelTank() {
        return ModelTank;
    }

    @Override
    public Action getAction() {
        return inputController.getAction();
    }
}
