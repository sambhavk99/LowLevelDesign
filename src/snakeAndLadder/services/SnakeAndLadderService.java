package snakeAndLadder.services;

import snakeAndLadder.models.Ladder;
import snakeAndLadder.models.Player;
import snakeAndLadder.models.Snake;
import snakeAndLadder.models.SnakeAndLadderBoard;

import java.util.*;

public class SnakeAndLadderService {
    private SnakeAndLadderBoard snakeAndLadderBoard;
    private int noOfPlayers;
    private Queue<Player> players;

    private static final int DEFAULT_BOARD_SIZE = 100; //The board will have 100 cells numbered from 1 to 100.
    private static final int DEFAULT_NO_OF_DICES = 1;

    public SnakeAndLadderService(int boardSize) {
        this.snakeAndLadderBoard = new SnakeAndLadderBoard(boardSize);
        this.players = new LinkedList<>();
    }

    public SnakeAndLadderService(){
        this(SnakeAndLadderService.DEFAULT_BOARD_SIZE);
    }

    public SnakeAndLadderBoard getSnakeAndLadderBoard() {
        return snakeAndLadderBoard;
    }

    public void setSnakeAndLadderBoard(SnakeAndLadderBoard snakeAndLadderBoard) {
        this.snakeAndLadderBoard = snakeAndLadderBoard;
    }

    public int getNoOfPlayers() {
        return noOfPlayers;
    }

    public void setNoOfPlayers(int noOfPlayers) {
        this.noOfPlayers = noOfPlayers;
    }

    public Queue<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.noOfPlayers =players.size();
        this.players = new LinkedList<>();
        this.players.addAll(players);
        snakeAndLadderBoard.setPlayers(this.players);
    }

    public void setSnakes(List<Snake> snakes){
        snakeAndLadderBoard.setSnakes(snakes);
    }

    public void setLadders(List<Ladder> ladders){
        snakeAndLadderBoard.setLadders(ladders);
    }


    private int getNewPositionAfterMove(int newPosition){
        int oldPosition = -1;
        while (oldPosition!=newPosition)
        {
            oldPosition = newPosition;
            for (Snake snake: snakeAndLadderBoard.getSnakes())
                if (snake.getStart()==newPosition)
                    newPosition = snake.getEnd();

            for (Ladder ladder: snakeAndLadderBoard.getLadders())
                if (ladder.getStart()==newPosition)
                    newPosition = ladder.getEnd();
        }
        return newPosition;
    }

    private void movePlayer(Player player, int positions){
        int oldPosition = player.getPosition();
        int newPosition = oldPosition + positions;

        int boardSize =snakeAndLadderBoard.getSize();

        if (newPosition>boardSize)
        {
            newPosition = oldPosition;
        }
        else {
            newPosition = getNewPositionAfterMove(newPosition);
        }
        player.setPosition(newPosition);
        printMove(player.getName(),positions,oldPosition,newPosition);
    }



    private void printMove(String playerName, int diceValue, int initialPosition, int finalPosition){
        String move = playerName + " rolled a " + diceValue + " and moved from " + initialPosition + " to " + finalPosition;
        System.out.println(move);
    }

    private int getTotalValueAfterDiceRolls() {
        // Can use noOfDices and setShouldAllowMultipleDiceRollOnSix here to get total value (Optional requirements)
        return DiceService.roll();
    }

    private boolean hasPlayerWon(Player player) {
        // Can change the logic a bit to handle special cases when there are more than one dice (Optional requirements)
        int playerPosition = player.getPosition();
        int winningPosition = snakeAndLadderBoard.getSize();
        return playerPosition == winningPosition; // A player wins if it exactly reaches the position 100 and the game ends there.
    }

    private boolean isGameCompleted() {
        // Can use shouldGameContinueTillLastPlayer to change the logic of determining if game is completed (Optional requirements)
        int currentNumberOfPlayers = players.size();
        return currentNumberOfPlayers < noOfPlayers;
    }

    public void startGame() {
        while (!isGameCompleted()) {
            Player currentPlayer = players.poll();
//            System.out.println("Type 'R' to roll the dice - "+ currentPlayer.getName() + "'s turn");
            int totalDiceValue = getTotalValueAfterDiceRolls(); // Each player rolls the dice when their turn comes.
//            Scanner scanner = new Scanner(System.in);
//            String roll = scanner.next();
//            if ("R".equals(roll))
//            {
//                movePlayer(currentPlayer, totalDiceValue);
//                players.poll();
//            }
//            else
//            {
//                continue;
//            }
            movePlayer(currentPlayer, totalDiceValue);
            if (hasPlayerWon(currentPlayer)) {
                System.out.println(currentPlayer.getName() + " wins the game");
            } else {
                players.add(currentPlayer);
            }
        }
    }

}
