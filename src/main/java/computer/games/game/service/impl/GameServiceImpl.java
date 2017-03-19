package computer.games.game.service.impl;

import computer.games.game.domain.Game;
import computer.games.game.repository.GameRepository;
import computer.games.game.service.GameService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Ivan on 25.11.2016.
 */
@Component("gameService")
public class GameServiceImpl implements GameService {

    private static final Logger LOG = LoggerFactory.getLogger(GameServiceImpl.class);

    @Autowired
    private GameRepository gameRepository;

    @Override
    public ResponseEntity<List<Game>> getAllGames() {
        return new ResponseEntity<>(gameRepository.findAll(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Game> getGameById(Long id) {
        Game game = gameRepository.findOne(id);
        if(game == null) {
            LOG.warn("game not found");
            return  new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(game, HttpStatus.OK);
    }
}
