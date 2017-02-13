package computer.games.order.domain;

/**
 * Created by Ivan on 27.01.2017.
 */
public class SaveOrderDTO {

    private String name;

    private String username;

    public SaveOrderDTO() {

    }

    public SaveOrderDTO(String name, String username) {
        this.name = name;
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SaveOrderDTO that = (SaveOrderDTO) o;

        if (getName() != null ? !getName().equals(that.getName()) : that.getName() != null) return false;
        return getUsername() != null ? getUsername().equals(that.getUsername()) : that.getUsername() == null;

    }

    @Override
    public int hashCode() {
        int result = getName() != null ? getName().hashCode() : 0;
        result = 31 * result + (getUsername() != null ? getUsername().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "SaveOrderDTO{" +
                "name='" + name + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
