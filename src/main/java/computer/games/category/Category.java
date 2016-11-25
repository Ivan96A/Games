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
        ACTION,
        RACE,
        SIMULATOR,
        FIGHTS
    }

    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Category category = (Category) o;

        if (!getId().equals(category.getId())) return false;
        if (getGameCategory() != category.getGameCategory()) return false;
        return getGames() != null ? getGames().equals(category.getGames()) : category.getGames() == null;

    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + (getGameCategory() != null ? getGameCategory().hashCode() : 0);
        result = 31 * result + (getGames() != null ? getGames().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", gameCategory=" + gameCategory +
                ", games=" + games +
                '}';
    }
}
