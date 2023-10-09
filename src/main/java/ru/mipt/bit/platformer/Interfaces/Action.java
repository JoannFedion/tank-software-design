package ru.mipt.bit.platformer.Interfaces;

import java.util.List;

public interface Action {
    public void apply(ModelObject object, float deltaTime);
    public boolean isPossibleApplyAction(ModelObject obj, List<ModelObject> lst);
}
