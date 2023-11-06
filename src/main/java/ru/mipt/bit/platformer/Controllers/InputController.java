package ru.mipt.bit.platformer.Controllers;

import com.badlogic.gdx.Gdx;
import ru.mipt.bit.platformer.Action;
import ru.mipt.bit.platformer.Actions.Direction;
import ru.mipt.bit.platformer.Actions.MoveAction;
import ru.mipt.bit.platformer.Actions.ShootAction;
import ru.mipt.bit.platformer.ObjectController;
import ru.mipt.bit.platformer.CollidesController;
import ru.mipt.bit.platformer.ModelObject;

import java.util.HashMap;
import java.util.Map;

import static com.badlogic.gdx.Input.Keys.*;
import static com.badlogic.gdx.Input.Keys.D;

public class InputController implements ObjectController<Integer> {

    private ModelObject playerObject;
    private final Map<Integer, Action> keyToActionMap = new HashMap<>();


    public InputController(ModelObject playerObject) {
        this.playerObject = playerObject;
    }

    @Override
    public void addMapping(Integer key, Action action) {
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
        if (playerObject != null) {
            Action action = getAction();
            if (action != null) action.apply(playerObject);
        }
//
    }

    public void initKeyMappingForController(CollidesController collidesController) {
        addMapping(UP, new MoveAction(Direction.UP, collidesController));
        addMapping(W, new MoveAction(Direction.UP, collidesController));
        addMapping(LEFT, new MoveAction(Direction.LEFT, collidesController));
        addMapping(A, new MoveAction(Direction.LEFT, collidesController));
        addMapping(DOWN, new MoveAction(Direction.DOWN, collidesController));
        addMapping(S, new MoveAction(Direction.DOWN, collidesController));
        addMapping(RIGHT, new MoveAction(Direction.RIGHT, collidesController));
        addMapping(D, new MoveAction(Direction.RIGHT, collidesController));
        addMapping(SPACE, new ShootAction(collidesController));
    }

    @Override
    public void deleteObject(ModelObject object) {
        if (playerObject == object){
            playerObject = null;
        }
    }
}
