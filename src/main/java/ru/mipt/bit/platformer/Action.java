package ru.mipt.bit.platformer;

import ru.mipt.bit.platformer.ModelObject;

public interface Action {
    public void apply(ModelObject object);
}
