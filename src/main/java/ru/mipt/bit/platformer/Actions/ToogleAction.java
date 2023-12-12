package ru.mipt.bit.platformer.Actions;

import ru.mipt.bit.platformer.Action;
import ru.mipt.bit.platformer.Toogle;
import ru.mipt.bit.platformer.ModelObject;

public class ToogleAction implements Action {
    private Toogle toogle;

    public ToogleAction(Toogle toogle) {
        this.toogle = toogle;
    }

    @Override
    public void apply(ModelObject object) {
        toogle.changeToogleStatus();
    }
}
