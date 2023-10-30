package ru.mipt.bit.platformer;

public class LevelCharacteristic {
    private final int HEIGHT;
    private final int WIDTH;

    private final int initialHealthTank;

    public LevelCharacteristic(int HEIGHT, int WIDTH, int initialHealthTank) {
        this.HEIGHT = HEIGHT;
        this.WIDTH = WIDTH;
        this.initialHealthTank = initialHealthTank;
    }

    public int getHEIGHT() {
        return HEIGHT;
    }

    public int getWIDTH() {
        return WIDTH;
    }

    public int getInitialHealthTank() {
        return initialHealthTank;
    }
}
