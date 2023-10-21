package ru.mipt.bit.platformer;

public class LevelCharacteristic {
    private int HEIGHT;
    private int WIDTH;

    public LevelCharacteristic(int HEIGHT, int WIDTH) {
        this.HEIGHT = HEIGHT;
        this.WIDTH = WIDTH;
    }

    public int getHEIGHT() {
        return HEIGHT;
    }

    public int getWIDTH() {
        return WIDTH;
    }
}
