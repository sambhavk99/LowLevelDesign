package game2048.models;

import java.util.*;

public class Board {
    private int[][] gameBoard;
    private List<int[]> emptyCells;

    public Board(){
        this.gameBoard = new int[4][4];
        setEmptyCells();
    }

    public int[][] getGameBoard() {
        return gameBoard;
    }

    public void setGameBoard(int[][] gameBoard) {
        this.gameBoard = gameBoard;
    }

    public List<int[]> getEmptyCells() {
        return emptyCells;
    }

    public void setEmptyCells(){
        this.emptyCells = new ArrayList<>();
        for (int i=0;i<4;i++)
        {
            for (int j=0;j<4;j++)
                if (gameBoard[i][j]==0)
                    emptyCells.add(new int[]{i,j});
        }
    }

}
