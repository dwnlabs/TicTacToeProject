package com.example.TicTacToeProject.Service;

import com.example.TicTacToeProject.Repo.TicTacToeProjectRepo;
import com.example.TicTacToeProject.Model.TicTacToe;
import com.example.TicTacToeProject.Repo.TicTacToeProjectRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicTacToeService {
    private final TicTacToeProjectRepo gameRepository;

    @Autowired
    public TicTacToeService(TicTacToeProjectRepo gameRepository) {
        this.gameRepository = gameRepository;
    }

    public TicTacToe saveGame(TicTacToe game) {
        return gameRepository.save(game);
    }

    public TicTacToe findGameById(Long gameId) {
        return gameRepository.findById(gameId).orElse(null);
    }
}