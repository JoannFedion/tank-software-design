package ru.mipt.bit.platformer;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Rectangle;

import static ru.mipt.bit.platformer.util.GdxGameUtils.createBoundingRectangle;

public class GameObject {

    private Texture objectTexture;
    private TextureRegion objectGraphics;
    private Rectangle objectRectangle;
    private GridPoint2 objectCoordinates;
    private float objectMovementProgress = 1f;
    private float objectRotation;

    public GameObject(String picturePath, int xCoordinate, int yCoordinate, float objectRotation) {
        this.objectTexture = new Texture(picturePath);
        this.objectGraphics = new TextureRegion(objectTexture);
        this.objectRectangle = createBoundingRectangle(objectGraphics);
        this.objectCoordinates = new GridPoint2(xCoordinate, yCoordinate);
        this.objectRotation = objectRotation;
    }


    public float getObjectRotation() {
        return objectRotation;
    }

    public void setObjectRotation(float objectRotation) {
        this.objectRotation = objectRotation;
    }

    public void dispose() {
        objectTexture.dispose();
    }

    public TextureRegion getObjectGraphics() {
        return objectGraphics;
    }

    public void setObjectMovementProgress(float objectMovementProgress) {
        this.objectMovementProgress = objectMovementProgress;
    }

    public float getObjectMovementProgress() {
        return objectMovementProgress;
    }

    public Rectangle getObjectRectangle() {
        return objectRectangle;
    }

    public GridPoint2 getObjectCoordinates() {
        return objectCoordinates;
    }
}
