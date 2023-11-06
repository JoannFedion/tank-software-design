package ru.mipt.bit.platformer;

public interface ObjectController<T> {
    public void execute();

    public void initKeyMappingForController(CollidesController collidesController);

    public void addMapping(T key, Action action);

    public void deleteObject(ModelObject object);

}
