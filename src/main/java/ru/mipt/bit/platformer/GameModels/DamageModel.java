package ru.mipt.bit.platformer.GameModels;

import ru.mipt.bit.platformer.ModelObject;

public interface DamageModel extends ModelObject {
    public void getDamage(DamageModel damageModel);

    public int takeDamage();

    public boolean isAlive();
}
