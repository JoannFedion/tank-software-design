package ru.mipt.bit.platformer.GenerationObjects;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.GameMechanic.Direction;
import ru.mipt.bit.platformer.Interfaces.ModelObject;
import ru.mipt.bit.platformer.ModelClasses.Tank;
import ru.mipt.bit.platformer.ModelClasses.Tree;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParserGeneration implements GenerationObjects {
    private String pathToFile;
    private List<ModelObject> listObjects;

    public ParserGeneration(String pathToFile) {
        this.pathToFile = pathToFile;
        this.listObjects = new ArrayList<>();
    }

    @Override
    public List<ModelObject> generate() {
        parse(pathToFile);
        return listObjects;
    }

    private void parse(String pathToFile) {
        int x = 0;
        int y = 5;
        List<Map<String, int[]>> lst = new ArrayList<>();
        try (FileReader reader = new FileReader(pathToFile)) {
            int c;
            while ((c = reader.read()) != -1) {
                switch (c) {
                    case 10: //перевод строки
                        x = 0;
                        y--;
                    case 95: // пустое место _
                        break;
                    case 84: // дерево Т
                        listObjects.add(new Tree(new GridPoint2(x, y)));
                        break;
                    case 88: // танк Х
                        listObjects.add(new Tank(new GridPoint2(x, y), Direction.UP));
                        break;
                    default:
                        x++;
                }
                System.out.print((char) c);
            }
        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }
    }

}
