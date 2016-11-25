package computer.games.game;

import computer.games.category.Category;
import computer.games.manufacturer.Manufacturer;
import computer.games.order.Order;
import computer.games.requirements.SystemRequirements;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Ivan on 22.10.2016.
 */

@Entity
@Table(name = "games")
public class Game  implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "year")
    private Long year;

    @ManyToMany(targetEntity = Order.class)
    private Set<Order> orders = new HashSet<>();

    @ManyToMany(targetEntity = Category.class, mappedBy = "games")
    private Set<Category> categories = new HashSet<>();

    @OneToOne
    @MapsId
    private SystemRequirements systemRequirements;

    @ManyToOne
    @JoinColumn(name = "manufacturers_id", nullable = false)
    private Manufacturer manufacturer;

    public Game() {

    }

    public Game(String name, Long year, Set<Order> orders, SystemRequirements systemRequirements, Set<Category> categories, Manufacturer manufacturer) {
        this.name = name;
        this.year = year;
        this.orders = orders;
        this.systemRequirements = systemRequirements;
        this.categories = categories;
        this.manufacturer = manufacturer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getYear() {
        return year;
    }

    public void setYear(Long year) {
        this.year = year;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public SystemRequirements getSystemRequirements() {
        return systemRequirements;
    }

    public void setSystemRequirements(SystemRequirements systemRequirements) {
        this.systemRequirements = systemRequirements;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Game game = (Game) o;

        if (!getId().equals(game.getId())) return false;
        if (getName() != null ? !getName().equals(game.getName()) : game.getName() != null) return false;
        if (getYear() != null ? !getYear().equals(game.getYear()) : game.getYear() != null) return false;
        if (getOrders() != null ? !getOrders().equals(game.getOrders()) : game.getOrders() != null) return false;
        if (getCategories() != null ? !getCategories().equals(game.getCategories()) : game.getCategories() != null)
            return false;
        if (getSystemRequirements() != null ? !getSystemRequirements().equals(game.getSystemRequirements()) : game.getSystemRequirements() != null)
            return false;
        return getManufacturer() != null ? getManufacturer().equals(game.getManufacturer()) : game.getManufacturer() == null;

    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getYear() != null ? getYear().hashCode() : 0);
        result = 31 * result + (getOrders() != null ? getOrders().hashCode() : 0);
        result = 31 * result + (getCategories() != null ? getCategories().hashCode() : 0);
        result = 31 * result + (getSystemRequirements() != null ? getSystemRequirements().hashCode() : 0);
        result = 31 * result + (getManufacturer() != null ? getManufacturer().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", year=" + year +
                ", orders=" + orders +
                ", categories=" + categories +
                ", systemRequirements=" + systemRequirements +
                ", manufacturer=" + manufacturer +
                '}';
    }
}
