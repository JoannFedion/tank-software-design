package ru.mipt.bit.platformer.Controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g3d.Model;
import ru.mipt.bit.platformer.Actions.Action;
import ru.mipt.bit.platformer.GameModels.ModelObject;
import ru.mipt.bit.platformer.GameModels.MovingObjects;

import java.util.HashMap;
import java.util.Map;

public class InputController implements ObjectController {

    private ModelObject playerObject;
    private final Map<Integer, Action> keyToActionMap = new HashMap<>();


    public InputController(ModelObject playerObject) {
        this.playerObject = playerObject;
    }

    public void addMapping(int key, Action action) {
        keyToActionMap.put(key, action);
    }

    public Action getAction() {
        for (Integer key : keyToActionMap.keySet()) {
            if (Gdx.input.isKeyPressed(key)) {
                return keyToActionMap.get(key);
            }
        }
        return null;
    }

    @Override
    public void execute() {
        Action action = getAction();
        if (action != null) action.apply(playerObject);
    }


}
