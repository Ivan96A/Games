package computer.games.manufacturer;

import computer.games.game.Game;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Ivan on 23.10.2016.
 */

@Entity
@Table(name = "manufacturers")
public class Manufacturer implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "year")
    private Long year;

    @Column(name = "number_of_employees")
    private Long numberOfEmployees;

    @OneToMany(targetEntity = Game.class, cascade = CascadeType.ALL, mappedBy = "manufacturer")
    private Set<Game> games = new HashSet<>();

    public Manufacturer() {

    }

    public Manufacturer(String name, Long year, Long numberOfEmployees) {
        this.name = name;
        this.year = year;
        this.numberOfEmployees = numberOfEmployees;
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

    public Long getNumberOfEmployees() {
        return numberOfEmployees;
    }

    public void setNumberOfEmployees(Long numberOfEmployees) {
        this.numberOfEmployees = numberOfEmployees;
    }

    public Set<Game> getGames() {
        return games;
    }

    public void setGames(Set<Game> games) {
        this.games = games;
    }
}
