package ru.mipt.bit.platformer.Controllers;

import ru.mipt.bit.platformer.GameModels.CollidesController;

import java.util.Map;

public interface ObjectController {
    public void execute();

    public void initKeyMappingForController(CollidesController collidesController);
}
