package computer.games.game.service;

import computer.games.game.domain.Game;

import java.util.List;

/**
 * Created by Ivan on 25.11.2016.
 */
public interface GameService {

    List<Game> getAllGames();

    Game getGameById(Long id);

}
