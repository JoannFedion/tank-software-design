package ru.mipt.bit.platformer.Controllers;

import ru.mipt.bit.platformer.Action;
import ru.mipt.bit.platformer.ObjectController;
import ru.mipt.bit.platformer.CollidesController;
import ru.mipt.bit.platformer.ModelObject;

import java.util.*;

public class AIRandomController implements ObjectController<Integer> {

    private final Map<Integer, Action> keyToActionMap;
    private ModelObject AIobject;

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
    public void initKeyMappingForController(CollidesController collidesController) {
        //
    }

    @Override
    public void addMapping(Integer key, Action action) {
        keyToActionMap.put(key, action);
    }

    @Override
    public void execute() {
        Action action = getAction();
        if (action != null && AIobject != null) action.apply(AIobject);
    }

    @Override
    public void deleteObject(ModelObject object) {
        if (AIobject == object){
            AIobject = null;
        }
    }
}
