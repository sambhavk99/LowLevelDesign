package game2048;

import game2048.services.BoardService;
import game2048.utils.Direction;

import java.util.Random;
import java.util.Scanner;

public class Game2048 {
    public static void main(String[] args){
        BoardService boardService = new BoardService();
        Scanner scanner = new Scanner(System.in);
        String directionStr = "";
        boolean canMove = true;
        while (canMove) {
            //int direction = scanner.nextInt();
            int direction = new Random().nextInt(4);
            switch (direction) {
                case 0 -> directionStr = "LEFT";
                case 1 -> directionStr = "RIGHT";
                case 2 -> directionStr = "UP";
                case 3 -> directionStr = "DOWN";
            }
            System.out.println(direction);
            canMove = boardService.move(Direction.valueOf(directionStr));
        }
    }
}
