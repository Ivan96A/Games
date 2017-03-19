package computer.games.game.controller;

import computer.games.game.domain.Game;
import computer.games.game.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Ivan on 25.11.2016.
 */

@RestController
@RequestMapping("/game")
public class GameController {

    @Autowired
    @Qualifier("gameService")
    private GameService gameService;

    @RequestMapping(
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Game>> getAll() {
        return gameService.getAllGames();
    }

    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Game> getOne(@PathVariable("id") Long id) {
        return gameService.getGameById(id);
    }

}
