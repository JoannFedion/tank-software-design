package ru.mipt.bit.platformer.GameModels;

public interface StateMotion extends State {
    float LOWER_BOUND_HEALTH = 0.15f;
    float UPPER_BOUND_HEALTH = 0.7f;
    float updateMovementProgress(float deltaTime);
}
