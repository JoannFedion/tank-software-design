package ru.mipt.bit.platformer.Graphics;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import ru.mipt.bit.platformer.*;
import ru.mipt.bit.platformer.GameModels.DamageModel;
import ru.mipt.bit.platformer.util.GdxGameUtils;

import java.util.Map;

public class DecoratorGameFieldGraphics implements FieldGraphics {

    private final FieldGraphics gameFieldGraphics;
    private final LevelCharacteristic levelCharacteristic;
    private Toogle toogle;

    public DecoratorGameFieldGraphics(FieldGraphics gameFieldGraphics, LevelCharacteristic levelCharacteristic, Toogle toogle) {
        this.gameFieldGraphics = gameFieldGraphics;
        this.levelCharacteristic = levelCharacteristic;
        this.toogle = toogle;
    }

    @Override
    public void renderAllObjects() {
        gameFieldGraphics.renderAllObjects();
        if (toogle.isToogle()) {
            drawHealthBar();
        }
    }

    private void drawHealthBar() {
        getBatch().begin();
        Map<ModelObject, GraphicsGameObjects> objectsMap = gameFieldGraphics.getObjectGraphicsGameObjectsMap();
        for (ModelObject object : objectsMap.keySet()){
            if (object instanceof DamageModel){
                drawHealthBar(getBatch(), objectsMap.get(object).getRectangle(), ((DamageModel) object).getHealth());
            }
        }
        getBatch().end();
    }

    @Override
    public Batch getBatch() {
        return gameFieldGraphics.getBatch();
    }

    @Override
    public void dispose() {
        gameFieldGraphics.dispose();
    }

    @Override
    public void createGraphicObject(ModelObject obj) {
        gameFieldGraphics.createGraphicObject(obj);
    }

    @Override
    public void deleteGraphicObject(ModelObject object) {
        gameFieldGraphics.deleteGraphicObject(object);
    }

    private void drawHealthBar(Batch batch, Rectangle tankRectangle, int health) {
        TextureRegion healthBgBar = createRedBar(levelCharacteristic.getInitialHealthTank(), Color.RED);
        TextureRegion healthLeftBar = createRedBar(health, Color.GREEN);
        Rectangle hpRectangle = new Rectangle(tankRectangle);
        hpRectangle.y += 90;
        GdxGameUtils.drawTextureRegionUnscaled(batch, healthBgBar, hpRectangle, 0);
        GdxGameUtils.drawTextureRegionUnscaled(batch, healthLeftBar, hpRectangle, 0);
    }

    private static TextureRegion createRedBar(int health, Color color) {
        if (health < 0) health = 0;
        Pixmap pixmap = new Pixmap(health * 10, 10, Pixmap.Format.RGBA8888);
        pixmap.setColor(color);
        pixmap.fillRectangle(0, 0, health * 10, 20);
        Texture texture = new Texture(pixmap);
        pixmap.dispose();
        TextureRegion healthBar = new TextureRegion(texture);
        return healthBar;
    }

    @Override
    public Map<ModelObject, GraphicsGameObjects> getObjectGraphicsGameObjectsMap() {
        return gameFieldGraphics.getObjectGraphicsGameObjectsMap();
    }
}
