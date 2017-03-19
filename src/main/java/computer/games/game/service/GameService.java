package computer.games.game.service;

import computer.games.game.domain.Game;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * Created by Ivan on 25.11.2016.
 */
public interface GameService {

    ResponseEntity<List<Game>> getAllGames();

    ResponseEntity<Game> getGameById(Long id);

}
