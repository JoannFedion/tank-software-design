package ru.mipt.bit.platformer;

import com.badlogic.gdx.graphics.g2d.Batch;

import java.util.List;
import java.util.Map;

public interface FieldGraphics {
    void renderAllObjects();
    void dispose();
    void createGraphicObject(ModelObject obj);
    void deleteGraphicObject(ModelObject object);
    Map<ModelObject, GraphicsGameObjects> getObjectGraphicsGameObjectsMap();
    Batch getBatch();

}
