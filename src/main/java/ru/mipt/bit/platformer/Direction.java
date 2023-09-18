package ru.mipt.bit.platformer;

import com.badlogic.gdx.Input;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Direction {
    private HashMap<Integer, List<Integer>> parametersMovieDirection;

    public Direction() {
        fillingParametersMovieDirection();
    }

    public HashMap<Integer, List<Integer>> getParametersMovieDirection() {
        return parametersMovieDirection;
    }

    private void fillingParametersMovieDirection() {
        parametersMovieDirection = new HashMap<>();
        addOneMovieDirection(Input.Keys.UP, 0, 1, 90);
        addOneMovieDirection(Input.Keys.DOWN, 0, -1, -90);
        addOneMovieDirection(Input.Keys.LEFT, -1, 0, -180);
        addOneMovieDirection(Input.Keys.RIGHT, 1, 0, 0);

    }

    private void addOneMovieDirection(int button1, int changesX, int changesY, int changesRotate) {
        List<Integer> direction_changes = Arrays.asList(new Integer[]{changesX, changesY, changesRotate});
        parametersMovieDirection.put(button1, direction_changes);

    }
}