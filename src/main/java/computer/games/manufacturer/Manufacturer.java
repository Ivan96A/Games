package computer.games.manufacturer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import computer.games.game.domain.Game;

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

    @JsonIgnore
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Manufacturer that = (Manufacturer) o;

        if (getId() != null ? !getId().equals(that.getId()) : that.getId() != null) return false;
        if (getName() != null ? !getName().equals(that.getName()) : that.getName() != null) return false;
        if (getYear() != null ? !getYear().equals(that.getYear()) : that.getYear() != null) return false;
        if (getNumberOfEmployees() != null ? !getNumberOfEmployees().equals(that.getNumberOfEmployees()) : that.getNumberOfEmployees() != null)
            return false;
        return getGames() != null ? getGames().equals(that.getGames()) : that.getGames() == null;

    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getYear() != null ? getYear().hashCode() : 0);
        result = 31 * result + (getNumberOfEmployees() != null ? getNumberOfEmployees().hashCode() : 0);
        result = 31 * result + (getGames() != null ? getGames().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Manufacturer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", year=" + year +
                ", numberOfEmployees=" + numberOfEmployees +
                ", games=" + games +
                '}';
    }
}
