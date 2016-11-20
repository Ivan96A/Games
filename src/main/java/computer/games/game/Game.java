package computer.games.game;

import computer.games.category.Category;
import computer.games.manufacturer.Manufacturer;
import computer.games.order.CustomOrder;
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

    @ManyToMany(targetEntity = CustomOrder.class)
    private Set<CustomOrder> customOrders = new HashSet<>();

    @ManyToMany(targetEntity = Category.class, mappedBy = "games")
    private Set<Category> categories = new HashSet<>();

    @OneToOne
    @MapsId
    private SystemRequirements systemRequirements;

    @ManyToOne
    @JoinColumn(name = "manufacturersId", nullable = false)
    private Manufacturer manufacturer;

    public Game() {

    }

    public Game(String name, Long year, Set<CustomOrder> customOrders, SystemRequirements systemRequirements, Set<Category> categories, Manufacturer manufacturer) {
        this.name = name;
        this.year = year;
        this.customOrders = customOrders;
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

    public Set<CustomOrder> getCustomOrders() {
        return customOrders;
    }

    public void setCustomOrders(Set<CustomOrder> customOrders) {
        this.customOrders = customOrders;
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

}
