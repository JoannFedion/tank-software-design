package ru.mipt.bit.platformer;

public interface ObjectController<T> {
    public void execute();

    public void deleteObject(ModelObject object);

}
