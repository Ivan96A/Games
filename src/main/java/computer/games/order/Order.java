package computer.games.order;

import computer.games.game.domain.Game;
import computer.games.user.CustomUser;

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

    @ManyToMany(targetEntity = Game.class, mappedBy = "orders")
    private Set<Game> games = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "users_id", nullable = false)
    private CustomUser user;

    public Order() {

    }

    public Order(CustomUser user, Set<Game> games) {
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

    public CustomUser getUser() {
        return user;
    }

    public void setUser(CustomUser user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (!getId().equals(order.getId())) return false;
        if (getGames() != null ? !getGames().equals(order.getGames()) : order.getGames() != null) return false;
        return getUser() != null ? getUser().equals(order.getUser()) : order.getUser() == null;

    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + (getGames() != null ? getGames().hashCode() : 0);
        result = 31 * result + (getUser() != null ? getUser().hashCode() : 0);
        return result;
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
