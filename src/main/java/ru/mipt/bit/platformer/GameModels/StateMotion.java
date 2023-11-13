package ru.mipt.bit.platformer.GameModels;

public interface StateMotion extends State {
    float updateMovementProgress(float deltaTime);
}
