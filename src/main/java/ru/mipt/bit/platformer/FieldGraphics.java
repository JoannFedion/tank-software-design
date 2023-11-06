package ru.mipt.bit.platformer;

import com.badlogic.gdx.graphics.g2d.Batch;

import java.util.List;
import java.util.Map;

public interface FieldGraphics {
    public void renderAllObjects();
    public void dispose();
    public float getDeltaTime();
    public void createGraphicObject(ModelObject obj);
    public void deleteGraphicObject(ModelObject object);

    public Map<ModelObject, GraphicsGameObjects> getObjectGraphicsGameObjectsMap();
    public Batch getBatch();

}
