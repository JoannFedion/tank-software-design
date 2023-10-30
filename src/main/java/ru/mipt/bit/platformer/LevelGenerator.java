package ru.mipt.bit.platformer;

import ru.mipt.bit.platformer.GeneratorsLevelInfo.LevelInfo;

public interface LevelGenerator {
    public LevelInfo generateLevelInfo();
}
