package computer.games.dto;

import java.util.Map;

/**
 * Created by Ivan on 05.01.2017.
 */
public class AuthUserDTO {

    private String firstName;

    private String username;

    private String role;

    private String massage;

    public AuthUserDTO() {

    }

    public AuthUserDTO(String firstName, String username, String role, String massage) {
        this.firstName = firstName;
        this.username = username;
        this.role = role;
        this.massage = massage;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getMassage() {
        return massage;
    }

    public void setMassage(String massage) {
        this.massage = massage;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AuthUserDTO that = (AuthUserDTO) o;

        if (getFirstName() != null ? !getFirstName().equals(that.getFirstName()) : that.getFirstName() != null)
            return false;
        if (getUsername() != null ? !getUsername().equals(that.getUsername()) : that.getUsername() != null)
            return false;
        if (getRole() != null ? !getRole().equals(that.getRole()) : that.getRole() != null) return false;
        return getMassage() != null ? getMassage().equals(that.getMassage()) : that.getMassage() == null;

    }

    @Override
    public int hashCode() {
        int result = getFirstName() != null ? getFirstName().hashCode() : 0;
        result = 31 * result + (getUsername() != null ? getUsername().hashCode() : 0);
        result = 31 * result + (getRole() != null ? getRole().hashCode() : 0);
        result = 31 * result + (getMassage() != null ? getMassage().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "AuthUserDTO{" +
                "firstName='" + firstName + '\'' +
                ", username='" + username + '\'' +
                ", role='" + role + '\'' +
                ", massage='" + massage + '\'' +
                '}';
    }

}
