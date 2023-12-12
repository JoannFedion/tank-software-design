package ru.mipt.bit.platformer;

public interface LevelListener {
    public void onAddGameObject(ModelObject object);

    public void onDeleteGameObject(ModelObject object);
}
