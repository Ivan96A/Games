package computer.games.requirements;

import computer.games.game.Game;

import javax.annotation.Generated;
import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Ivan on 23.10.2016.
 */

@Entity
@Table(name = "system_requirements")
public class SystemRequirements implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "processor")
    private String processor;

    @Column(name = "ram")
    private String RAM;

    @Column(name = "video")
    private String video;

    @Column(name = "hdd")
    private Long HDD;

    @ManyToOne
    @JoinColumn(name = "games_id", nullable = false)
    private Game game;

    public SystemRequirements() {

    }

    public SystemRequirements(String processor, String RAM, String video, Long HDD) {
        this.processor = processor;
        this.RAM = RAM;
        this.video = video;
        this.HDD = HDD;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SystemRequirements that = (SystemRequirements) o;

        if (!getId().equals(that.getId())) return false;
        if (getProcessor() != null ? !getProcessor().equals(that.getProcessor()) : that.getProcessor() != null)
            return false;
        if (getRAM() != null ? !getRAM().equals(that.getRAM()) : that.getRAM() != null) return false;
        if (getVideo() != null ? !getVideo().equals(that.getVideo()) : that.getVideo() != null) return false;
        if (getHDD() != null ? !getHDD().equals(that.getHDD()) : that.getHDD() != null) return false;
        return getGame() != null ? getGame().equals(that.getGame()) : that.getGame() == null;

    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + (getProcessor() != null ? getProcessor().hashCode() : 0);
        result = 31 * result + (getRAM() != null ? getRAM().hashCode() : 0);
        result = 31 * result + (getVideo() != null ? getVideo().hashCode() : 0);
        result = 31 * result + (getHDD() != null ? getHDD().hashCode() : 0);
        result = 31 * result + (getGame() != null ? getGame().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "SystemRequirements{" +
                "id=" + id +
                ", processor='" + processor + '\'' +
                ", RAM='" + RAM + '\'' +
                ", video='" + video + '\'' +
                ", HDD=" + HDD +
                ", game=" + game +
                '}';
    }
}
