package com.ske.snakebaddesign.models;

/**
 * Created by exceed on 3/10/16 AD.
 */
public class Game {
    private Player player1;
    private Player player2;
    private Board board;
    public Game(){
        board = new Board();
        player1 = new Player("1");
        player2 = new Player("2");
    }

    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }
}
