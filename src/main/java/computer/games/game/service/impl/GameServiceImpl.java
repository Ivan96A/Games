package computer.games.game.service.impl;

import computer.games.game.domain.Game;
import computer.games.game.repository.GameRepository;
import computer.games.game.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Ivan on 25.11.2016.
 */
@Component("gameService")
public class GameServiceImpl implements GameService {

    @Autowired
    private GameRepository gameRepository;

    @Override
    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

    @Override
    public Game getGameById(Long id) {
        return gameRepository.findOne(id);
    }
}
