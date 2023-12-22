package com.example.TicTacToeProject.Repo;

import com.example.TicTacToeProject.Model.TicTacToe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicTacToeProjectRepo extends JpaRepository<TicTacToe, Long> {
    List<TicTacToe> findTop10ByOrderByLastMoveTimeAsc();
}