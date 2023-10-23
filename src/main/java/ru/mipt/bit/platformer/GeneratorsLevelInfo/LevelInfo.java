package ru.mipt.bit.platformer.GeneratorsLevelInfo;

import ru.mipt.bit.platformer.GameModels.ModelObject;
import ru.mipt.bit.platformer.GameModels.LevelGame;

public class LevelInfo {
    private final LevelGame levelGame;
    private final ModelObject playerObject;

    public LevelInfo(LevelGame levelGame, ModelObject playerObject) {
        this.levelGame = levelGame;
        this.playerObject = playerObject;
    }

    public LevelGame getLevelGame() {
        return levelGame;
    }

    public ModelObject getPlayerObject() {
        return playerObject;
    }
}
