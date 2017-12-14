package AdventOfCode;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Day31 {

    enum Direction {
        RIGHT, UP, LEFT, DOWN;
    }

    public static void main(String[] args) {

        int max = 277678;
        int input = 277678;

        int lastX = 0;
        int lastY = 0;

        int step = 2;
        int stepsize = 1;

        boolean lastStep = false;

        String currentDirection = Direction.RIGHT.name();

        TreeMap<Integer, HashMap<Integer, Integer>> keyValue = new TreeMap<>();

        for (int i = 1; i < max; i++) {

            if (currentDirection.equals(Direction.RIGHT.name())) {

                HashMap<Integer, Integer> coordMap = new HashMap<>();

                int newX = lastX + 1;
                lastX = newX;

                coordMap.put(newX, lastY);

                keyValue.put(step, coordMap);

            } else if (currentDirection.equals(Direction.UP.name())) {

                int newY = lastY + 1;
                lastY = newY;

                HashMap<Integer, Integer> coordMap = new HashMap<>();
                coordMap.put(lastX, newY);

                keyValue.put(step, coordMap);

            } else if (currentDirection.equals(Direction.LEFT.name())) {
                int newX = lastX - 1;
                lastX = newX;

                HashMap<Integer, Integer> coordMap = new HashMap<>();
                coordMap.put(newX, lastY);

                keyValue.put(step, coordMap);

            } else if (currentDirection.equals(Direction.DOWN.name())) {
                int newY = lastY - 1;
                lastY = newY;

                HashMap<Integer, Integer> coordMap = new HashMap<>();
                coordMap.put(lastX, newY);

                keyValue.put(step, coordMap);

            }

            step += 1;

            if (i % stepsize == 0 && !lastStep) {
                lastStep = true;
                System.out.println("first occurrence of valid modulo");
                currentDirection = changeDirection(currentDirection);
            } else if (i % stepsize == 0) {
                stepsize = stepsize + 1;
                lastStep = false;
                System.out.println("second occurrence of valid modulo");
                currentDirection = changeDirection(currentDirection);
            }

        }


        for (Map.Entry entry : keyValue.entrySet()) {
            System.out.println("number is: " + entry.getKey() + " coordinates are: " + entry.getValue());
        }
    }

    private static String changeDirection(String direction) {
        if (direction.equals(Direction.RIGHT.name())) {
            direction = Direction.UP.name();
        } else if (direction.equals(Direction.UP.name())) {
            direction = Direction.LEFT.name();
        } else if (direction.equals(Direction.LEFT.name())) {
            direction = Direction.DOWN.name();
        } else if (direction.equals(Direction.DOWN.name())) {
            direction = Direction.RIGHT.name();
        }

        return direction;

    }
}
