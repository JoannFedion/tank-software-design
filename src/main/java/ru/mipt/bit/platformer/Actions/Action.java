package ru.mipt.bit.platformer.Actions;

import ru.mipt.bit.platformer.GameModels.ModelObject;

public interface Action {
    public void apply(ModelObject object);
}
