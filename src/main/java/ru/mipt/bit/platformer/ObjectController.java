package ru.mipt.bit.platformer;

import ru.mipt.bit.platformer.ObjectControllers.CollidesController;

public interface ObjectController<T> {
    public void execute();

    public void initKeyMappingForController(CollidesController collidesController);

    public void addMapping(T key, Action action);

    public void deleteObject(ModelObject object);

}
