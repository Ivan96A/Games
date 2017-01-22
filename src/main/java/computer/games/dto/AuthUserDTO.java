package computer.games.dto;

import java.util.Map;

/**
 * Created by Ivan on 05.01.2017.
 */
public class AuthUserDTO {

    private String firstName;

    private String username;

    private Map<String, Boolean> roles;

    private String massage;

    public AuthUserDTO() {

    }

    public AuthUserDTO(String firstName, String username, Map<String, Boolean> roleMap, String massage) {
        this.firstName = firstName;
        this.username = username;
        this.roles = roleMap;
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

    public Map<String, Boolean> getRoles() {
        return roles;
    }

    public void setRoles(Map<String, Boolean> roles) {
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AuthUserDTO that = (AuthUserDTO) o;

        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
        if (username != null ? !username.equals(that.username) : that.username != null) return false;
        if (roles != null ? !roles.equals(that.roles) : that.roles != null) return false;
        return massage != null ? massage.equals(that.massage) : that.massage == null;
    }

    @Override
    public int hashCode() {
        int result = firstName != null ? firstName.hashCode() : 0;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (roles != null ? roles.hashCode() : 0);
        result = 31 * result + (massage != null ? massage.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "AuthUserDTO{" +
                "firstName='" + firstName + '\'' +
                ", username='" + username + '\'' +
                ", roles=" + roles +
                ", massage='" + massage + '\'' +
                '}';
    }
}
