package snakeAndLadder.services;

import java.util.Random;

public class DiceService {
    public static final int MAX_DIE = 6;

    public static int roll(){
        return new Random().nextInt(MAX_DIE)+1;
    }
}
