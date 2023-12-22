package com.example.TicTacToeProject.Controller;

import com.example.TicTacToeProject.Model.TicTacToe;
import com.example.TicTacToeProject.Repo.TicTacToeProjectRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static com.example.TicTacToeProject.Model.TicTacToe.tableSize;


/**
 * Controller class for handling HTTP requests and rendering the Tic-Tac-Toe game.
 */
@Controller
@RequestMapping("/")
public class TicTacToeController {
    private final TicTacToeProjectRepo ticTacToeRepository;

    @Autowired
    public TicTacToeController(TicTacToeProjectRepo TicTacToeProjectRepo) {
        this.ticTacToeRepository = TicTacToeProjectRepo;
    }

    /**
     * Renders the Tic-Tac-Toe game page with an empty board.
     */
    @GetMapping
    public ModelAndView index() {
        return ticTacToe();
    }

    /**
     * Renders the Tic-Tac-Toe game page with an empty board.
     */
    @GetMapping("/index")
    public ModelAndView ticTacToe() {
        ModelAndView modelAndView = new ModelAndView("index");
        String[][] board = new String[tableSize][tableSize];
        Arrays.stream(board).forEach(row -> Arrays.fill(row, " "));
        TicTacToe ticTacToe = new TicTacToe("Player1", "Player2");
        ticTacToe.setStartTime(new Date());
        modelAndView.addObject("board", board);
        return modelAndView;
    }

    /**
     * Renders the Tic-Tac-Toe leaderboard page with a table that shows game information from database.
     */
    @GetMapping("/leaderboard")
    public ModelAndView getLeaderboard() {
        List<TicTacToe> leaderboard = ticTacToeRepository.findTop10ByOrderByLastMoveTimeAsc();
        ModelAndView modelAndView = new ModelAndView("leaderboard");
        modelAndView.addObject("leaderboard", leaderboard);
        return modelAndView;
    }
}