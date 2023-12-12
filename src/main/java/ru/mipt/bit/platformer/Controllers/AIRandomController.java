package ru.mipt.bit.platformer.Controllers;

import ru.mipt.bit.platformer.Action;
import ru.mipt.bit.platformer.ObjectController;
import ru.mipt.bit.platformer.CollidesController;
import ru.mipt.bit.platformer.ModelObject;

import java.util.*;

public class AIRandomController implements ObjectController<Integer> {

    private final List<Action> actionList;
    private ModelObject AIobject;

    public AIRandomController(ModelObject AIobject , List<Action> actionList) {
        this.AIobject = AIobject;
        this.actionList = actionList;
    }

    public Action getAction(){
        Random random = new Random();
        return actionList.get(random.nextInt(actionList.size()));
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
