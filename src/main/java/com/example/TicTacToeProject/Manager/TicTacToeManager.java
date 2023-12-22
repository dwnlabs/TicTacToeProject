package com.example.TicTacToeProject.Manager;

import com.example.TicTacToeProject.Enumeration.GameState;
import com.example.TicTacToeProject.Model.TicTacToe;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static com.example.TicTacToeProject.Model.TicTacToe.tableSize;


public class TicTacToeManager {


    private final Map<String, TicTacToe> games;


    protected final Map<String, String> waitingPlayers;


    public TicTacToeManager() {
        games = new ConcurrentHashMap<>();
        waitingPlayers = new ConcurrentHashMap<>();
    }


    public synchronized TicTacToe joinGame(String player) {
        if (games.values().stream().anyMatch(game -> game.getPlayer1().equals(player) || (game.getPlayer2() != null && game.getPlayer2().equals(player)))) {
            return games.values().stream().filter(game -> game.getPlayer1().equals(player) || game.getPlayer2().equals(player)).findFirst().get();
        }

        for (TicTacToe game : games.values()) {
            if (game.getPlayer1() != null && game.getPlayer2() == null) {
                game.setPlayer2(player);
                game.setGameState(GameState.PLAYER1_TURN);
                return game;
            }
        }

        TicTacToe game = new TicTacToe(player, null);
        games.put(game.getGameId(), game);
        waitingPlayers.put(player, game.getGameId());
        return game;
    }

    /**
     * Removes a player from their Tic-Tac-Toe game.
     */
    public synchronized TicTacToe leaveGame(String player) {
        String gameId = getGameByPlayer(player) != null ? getGameByPlayer(player).getGameId() : null;
        if (gameId != null) {
            waitingPlayers.remove(player);
            TicTacToe game = games.get(gameId);
            if (player.equals(game.getPlayer1())) {
                if (game.getPlayer2() != null) {
                    game.setPlayer1(game.getPlayer2());
                    game.setPlayer2(null);
                    game.setGameState(GameState.WAITING_FOR_PLAYER);
                    game.setBoard(new String[tableSize][tableSize]);
                    waitingPlayers.put(game.getPlayer1(), game.getGameId());
                } else {
                    games.remove(gameId);
                    return null;
                }
            } else if (player.equals(game.getPlayer2())) {
                game.setPlayer2(null);
                game.setGameState(GameState.WAITING_FOR_PLAYER);
                game.setBoard(new String[tableSize][tableSize]);
                waitingPlayers.put(game.getPlayer1(), game.getGameId());
            }
            return game;
        }
        return null;
    }

    /**
     * Returns the Tic-Tac-Toe game with the given game ID.
     */
    public TicTacToe getGame(String gameId) {
        return games.get(gameId);
    }

    /**
     * Returns the Tic-Tac-Toe game the given player is in.
     */
    public TicTacToe getGameByPlayer(String player) {
        return games.values().stream().filter(game -> game.getPlayer1().equals(player) || (game.getPlayer2() != null &&
                game.getPlayer2().equals(player))).findFirst().orElse(null);
    }

    /**
     * Removes the Tic-Tac-Toe game with the given game ID.
     */
    public void removeGame(String gameId) {
        games.remove(gameId);
    }
}
