package computer.games.game.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import computer.games.manufacturer.Manufacturer;
import computer.games.order.domain.Order;

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

    public enum Type {
        ACTION,
        RACE,
        SIMULATOR,
        FIGHTS
    }

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "year")
    private Long year;

    @Column(name = "processor")
    private String processor;

    @Column(name = "ram")
    private String RAM;

    @Column(name = "video")
    private String video;

    @Column(name = "hdd")
    private Long HDD;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "game_category")
    private Type gameCategory;

    @Column(name = "price")
    private int price;

    @Column(name = "image")
    private String image;

    @JsonIgnore
    @ManyToMany(targetEntity = Order.class)
    private Set<Order> orders = new HashSet<>();


    @ManyToOne
    @JoinColumn(name = "manufacturers_id", nullable = false)
    private Manufacturer manufacturer;



    public Game() {

    }

    public Game(String name, Long year, String processor, String RAM, String video, Long HDD, Type gameCategory, int price, String image, Manufacturer manufacturer) {
        this.name = name;
        this.year = year;
        this.processor = processor;
        this.RAM = RAM;
        this.video = video;
        this.HDD = HDD;
        this.gameCategory = gameCategory;
        this.price = price;
        this.image = image;
        this.manufacturer = manufacturer;
    }

    public void addOrder(Order order) {
        this.orders.add(order);
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

    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    public String getRAM() {
        return RAM;
    }

    public void setRAM(String RAM) {
        this.RAM = RAM;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public Long getHDD() {
        return HDD;
    }

    public void setHDD(Long HDD) {
        this.HDD = HDD;
    }

    public Type getGameCategory() {
        return gameCategory;
    }

    public void setGameCategory(Type gameCategory) {
        this.gameCategory = gameCategory;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
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

        return getName() != null ? getName().equals(game.getName()) : game.getName() == null;

    }

    @Override
    public int hashCode() {
        return getName() != null ? getName().hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", year=" + year +
                ", processor='" + processor + '\'' +
                ", RAM='" + RAM + '\'' +
                ", video='" + video + '\'' +
                ", HDD=" + HDD +
                ", gameCategory=" + gameCategory +
                ", price=" + price +
                ", image='" + image + '\'' +
                ", orders=" + orders +
                ", manufacturer=" + manufacturer +
                '}';
    }

}
