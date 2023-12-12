package ru.mipt.bit.platformer.Listeners;

import ru.mipt.bit.platformer.FieldGraphics;
import ru.mipt.bit.platformer.ModelObject;
import ru.mipt.bit.platformer.LevelListener;

public class GraphicsListener implements LevelListener {

    private FieldGraphics gameFieldGraphics;

    public GraphicsListener(FieldGraphics gameFieldGraphics) {
        this.gameFieldGraphics = gameFieldGraphics;
    }

    @Override
    public void onDeleteGameObject(ModelObject object) {
        gameFieldGraphics.deleteGraphicObject(object);
    }

    @Override
    public void onAddGameObject(ModelObject object) {
        gameFieldGraphics.createGraphicObject(object);
    }
}
