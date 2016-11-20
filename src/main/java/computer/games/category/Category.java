package computer.games.category;

import computer.games.game.Game;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Ivan on 23.10.2016.
 */

@Entity
@Table(name = "categories")
public class Category implements Serializable {

    public enum GameCategory {
        ACTION("ACTION"),
        RACE("RACE"),
        SIMULATOR("SIMULATOR"),
        FIGHTS("FIGHTS");

        private final String gameCategory;

        GameCategory(String gameCategory) {
            this.gameCategory = gameCategory;
        }

        @Override
        public String toString() {
            return gameCategory;
        }
    }

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "game_category")
    private GameCategory gameCategory;

    @ManyToMany(targetEntity = Game.class)
    private Set<Game> games = new HashSet<>();

    public Category() {

    }

    public Category(Long id, GameCategory gameCategory) {
        this.id = id;
        this.gameCategory = gameCategory;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public GameCategory getGameCategory() {
        return gameCategory;
    }

    public void setGameCategory(GameCategory gameCategory) {
        this.gameCategory = gameCategory;
    }

    public Set<Game> getGames() {
        return games;
    }

    public void setGames(Set<Game> games) {
        this.games = games;
    }

}
