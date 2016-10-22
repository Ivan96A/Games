package computer.games.order;

import javax.persistence.*;
import java.io.Serializable;

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

}
