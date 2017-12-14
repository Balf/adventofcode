package AdventOfCode;

import java.util.*;

public class Day32 {

    private static LinkedHashMap<String, Integer> keyValue = new LinkedHashMap<>();

    enum Direction {
        RIGHT, UP, LEFT, DOWN;
    }

    public static void main(String[] args) {

        int max = 277678;

        int lastX = 0;
        int lastY = 0;

        int step = 2;
        int stepsize = 1;

        boolean lastStep = false;

        String currentDirection = Direction.RIGHT.name();

        keyValue.put("0|0",1);



        for (int i = 1; i < max; i++) {

            int total = 0;

            if (currentDirection.equals(Direction.RIGHT.name())) {

                int newX = lastX + 1;
                lastX = newX;

                total = getSurroundingTotal(newX, lastY);

                keyValue.put(newX + "|" +lastY, total);


            } else if (currentDirection.equals(Direction.UP.name())) {

                int newY = lastY + 1;
                lastY = newY;

                total = getSurroundingTotal(lastX, newY);

                keyValue.put(lastX + "|" + newY, total);

            } else if (currentDirection.equals(Direction.LEFT.name())) {
                int newX = lastX - 1;
                lastX = newX;

                total = getSurroundingTotal(newX, lastY);

                keyValue.put(newX + "|" + lastY, total);

            } else if (currentDirection.equals(Direction.DOWN.name())) {
                int newY = lastY - 1;
                lastY = newY;

                total = getSurroundingTotal(lastX, newY);

                keyValue.put(lastX + "|" + newY, total);

            }

            if (total > max) {
                System.out.println("First value after max is: " + total);
                break;
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

    private static int getSurroundingTotal(int x, int y) {
        int total = 0;

        // +1 +0

        total = addValue((x + 1) + "|" + (y), total);

        // +1 +1

        total = addValue((x + 1) + "|" + (y + 1), total);

        // +0 +1

        total = addValue(x + "|" + (y +1), total);

        // -1 +1

        total = addValue((x - 1) + "|" + (y +1), total);

        // -1 +0

        total = addValue((x - 1) + "|" + (y), total);

        // -1 -1

        total = addValue((x - 1) + "|" + (y - 1), total);

        // +0 -1

        total = addValue(x + "|" + (y - 1), total);

        // +1 -1

        total = addValue((x + 1) + "|" + (y - 1), total);


        return total;


    }

    private static int addValue(String key, int total) {
        Integer mapValue = keyValue.get(key);

        if (mapValue != null) {
            total += mapValue;
        }

        return total;
    }
}
