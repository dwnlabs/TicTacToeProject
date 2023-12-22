package com.example.TicTacToeProject.Model.Dto;

import com.example.TicTacToeProject.Model.TicTacToe;
import com.example.TicTacToeProject.Enumeration.GameState;
import lombok.Data;
import lombok.Getter;

import java.util.Date;

@Data
public class TicTacToeMessage implements Message {
    private String type;
    private String gameId;
    private String player1;
    private String player2;
    private String winner;
    private String turn;
    private String content;
    private String[][] board;
    private int move;
    private GameState gameState;
    private String sender;
    private Date lastMoveTime;
    private Date startTime;

    public TicTacToeMessage() {
    }

    public TicTacToeMessage(TicTacToe game) {
        this.gameId = game.getGameId();
        this.player1 = game.getPlayer1();
        this.player2 = game.getPlayer2();
        this.winner = game.getWinner();
        this.turn = game.getTurn();
        this.board = game.getBoard();
        this.gameState = game.getGameState();
        this.lastMoveTime = game.getLastMoveTime();
        this.startTime = game.getStartTime();
    }
}