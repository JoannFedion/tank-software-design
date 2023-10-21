package ru.mipt.bit.platformer.Controllers;

import ru.mipt.bit.platformer.Actions.Action;
import ru.mipt.bit.platformer.GameModels.ModelObject;

import java.util.*;

public class AIRandomController implements ObjectController{

    private final Map<Integer, Action> keyToActionMap;
    private final ModelObject AIobject;

    public AIRandomController(ModelObject AIobject , Map<Integer, Action> keyToActionMap) {
        this.AIobject = AIobject;
        this.keyToActionMap = keyToActionMap;
    }

    public Action getAction(){
        Random random = new Random();
        List<Integer> keysAsArray = new ArrayList<>(keyToActionMap.keySet());
        return keyToActionMap.get(keysAsArray.get(random.nextInt(keysAsArray.size())));
    }



    @Override
    public void execute() {
        Action action = getAction();
        if (action != null) action.apply(AIobject);
    }
}
