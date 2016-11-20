package computer.games.order;

import computer.games.game.Game;
import org.apache.catalina.User;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Ivan on 10.11.2016.
 */
@Entity
@Table(name = "orders")
public class CustomOrder implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToMany(targetEntity = Game.class, mappedBy = "orders")
    private Set<Game> games = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "usersId", nullable = false)
    private User user;

    public CustomOrder() {

    }

    public CustomOrder(User user, Set<Game> games) {
        this.user = user;
        this.games = games;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
