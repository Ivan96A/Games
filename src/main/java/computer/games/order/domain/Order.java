package computer.games.order.domain;

import computer.games.game.domain.Game;
import computer.games.user.domain.CustomUser;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Ivan on 10.11.2016.
 */
@Entity
@Table(name = "orders")
public class Order implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToMany(targetEntity = Game.class, mappedBy = "orders", cascade = CascadeType.ALL)
    private Set<Game> games = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "users_id", nullable = false)
    private CustomUser user;

    public Order() {

    }

    public Order(CustomUser user) {
        this.user = user;
    }

    public void addGame(Game game) {
        this.games.add(game);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Game> getGames() {
        return games;
    }

    public void setGames(Set<Game> games) {
        this.games = games;
    }

    public CustomUser getUser() {
        return user;
    }

    public void setUser(CustomUser user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", games=" + games +
                ", user=" + user +
                '}';
    }
}
