package ru.mipt.bit.platformer;

public class Toogle {
    private boolean isToogle;

    public Toogle(boolean isToogle) {
        this.isToogle = isToogle;
    }

    public boolean isToogle() {
        return isToogle;
    }

    public void changeToogleStatus(){
        isToogle = !isToogle;
    }
}
