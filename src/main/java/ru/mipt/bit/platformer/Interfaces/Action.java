package ru.mipt.bit.platformer.Interfaces;

import ru.mipt.bit.platformer.Interfaces.ModelObject;

import java.util.List;

public interface Action {
    public void apply(ModelObject object, float deltaTime);
    public boolean isPossibleDoAction(ModelObject obj, List<ModelObject> lst);
}
