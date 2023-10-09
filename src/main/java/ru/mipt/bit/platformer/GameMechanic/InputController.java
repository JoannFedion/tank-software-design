package ru.mipt.bit.platformer.GameMechanic;
import com.badlogic.gdx.Gdx;
import ru.mipt.bit.platformer.Interfaces.Action;

import java.util.HashMap;
import java.util.Map;

public class InputController {
    private final Map<Integer, Action> keyToActionMap = new HashMap<>();

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
}
