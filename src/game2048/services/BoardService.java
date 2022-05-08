package game2048.services;

import game2048.utils.Direction;
import game2048.models.Board;

import java.util.*;

public class BoardService {
    private final Board board;
    private final Set<String> merged;

    public BoardService() {
        this.board = new Board();
        addTile();
        addTile();
        printBoard();
        merged = new HashSet<>();
    }

    private void addTile(){
        int[][] gameBoard = board.getGameBoard();
        List<int[]> emptyCells = board.getEmptyCells();
        if (emptyCells.isEmpty())
            return;
        int limit = emptyCells.size();
        int i = new Random().nextInt(limit);
        int[] cell = emptyCells.get(i);
        emptyCells.remove(i);
        int x = cell[0], y = cell[1];
        gameBoard[x][y] = 2;
    }

    public boolean move(Direction direction){
        merged.clear();
        int[][] gameBoard = board.getGameBoard();
        String initialGameBoardStr = Arrays.deepToString(gameBoard);
        switch (direction){
            case UP -> moveUp(gameBoard);
            case DOWN -> moveDown(gameBoard);
            case LEFT -> moveLeft(gameBoard);
            case RIGHT -> moveRight(gameBoard);
        }
        board.setEmptyCells();
        if (!initialGameBoardStr.equals(Arrays.deepToString(gameBoard)))
        {
            addTile();
        }
        printBoard();
        boolean canMove = !checkForWin(gameBoard);
        if (checkGameOver(gameBoard))
        {
            canMove = false;
        }
        return canMove;
    }

    private void printBoard(){
        int[][] gameBoard = board.getGameBoard();
        for (int i=0;i<4;i++)
        {
            for (int j=0;j<4;j++)
            {
                System.out.print(gameBoard[i][j] + "\t");
            }
            System.out.println();
        }
    }


    private void moveUp(int[][] gameBoard){
        for (int i=1;i<4;i++){
            for (int j=0;j<4;j++)
            {
                int val = gameBoard[i][j];
                gameBoard[i][j]=0;
                int k=i-1;
                while (k>=0 && gameBoard[k][j]==0)
                    k--;
                if (k>=0 && gameBoard[k][j]==val && !merged.contains(k+","+j))
                {
                    gameBoard[k][j]+=val;
                    merged.add(k+","+j);
                }
                else
                    gameBoard[k+1][j]=val;
            }
        }
    }

    private void moveDown(int[][] gameBoard){
        for (int i=2;i>=0;i--){
            for (int j=0;j<4;j++)
            {
                int val = gameBoard[i][j];
                gameBoard[i][j]=0;
                int k=i+1;
                while (k<4 && gameBoard[k][j]==0)
                    k++;
                if (k<4 && gameBoard[k][j]==val && !merged.contains(k+","+j))
                {
                    gameBoard[k][j]+=val;
                    merged.add(k+","+j);
                }
                else
                    gameBoard[k-1][j]=val;
            }
        }
    }

    private void moveLeft(int[][] gameBoard){
        for (int i=1;i<4;i++){
            for (int j=0;j<4;j++)
            {
                int val = gameBoard[j][i];
                gameBoard[j][i]=0;
                int k=i-1;
                while (k>=0 && gameBoard[j][k]==0)
                    k--;
                if (k>=0 && gameBoard[j][k]==val && !merged.contains(j+","+k))
                {
                    gameBoard[j][k]+=val;
                    merged.add(j+","+k);
                }
                else
                    gameBoard[j][k+1]=val;
            }
        }
    }

    private void moveRight(int[][] gameBoard){
        for (int i=2;i>=0;i--){
            for (int j=0;j<4;j++)
            {
                int val = gameBoard[j][i];
                gameBoard[j][i]=0;
                int k=i+1;
                while (k<4 && gameBoard[j][k]==0)
                    k++;
                if (k<4 && gameBoard[j][k]==val && !merged.contains(j+","+k))
                {
                    gameBoard[j][k]+=val;
                    merged.add(k+","+j);
                }
                else
                    gameBoard[j][k-1]=val;
            }
        }
    }

    private boolean checkGameOver(int[][] gameBoard){
        boolean gameOver = board.getEmptyCells().isEmpty() && !checkForAdjacentTiles(gameBoard);
        if (gameOver)
        {
            System.out.println("Game Over");
        }
        return gameOver;
    }

    private boolean checkForAdjacentTiles(int[][] gameBoard){
        for (int i=0;i<4;i++){
            for (int j=0;j<4;j++)
            {
                if ((j<3 && gameBoard[i][j]==gameBoard[i][j+1]) ||
                        (i<3 && gameBoard[i][j]==gameBoard[i+1][j]))
                    return true;
            }
        }
        return false;
    }

    private boolean checkForWin(int[][] gameBoard){
        for (int i=0;i<4;i++)
        {
            for (int j=0;j<4;j++)
                if (gameBoard[i][j]==2048)
                {
                    System.out.println("Congratulations");
                    return true;
                }
        }
        return false;
    }
}
