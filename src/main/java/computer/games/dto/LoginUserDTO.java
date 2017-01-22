package computer.games.dto;

/**
 * Created by Ivan on 14.01.2017.
 */
public class LoginUserDTO {

    private String username;

    private String password;

    public LoginUserDTO() {
    }

    public LoginUserDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LoginUserDTO loginUserDTO = (LoginUserDTO) o;

        if (getUsername() != null ? !getUsername().equals(loginUserDTO.getUsername()) : loginUserDTO.getUsername() != null)
            return false;
        return getPassword() != null ? getPassword().equals(loginUserDTO.getPassword()) : loginUserDTO.getPassword() == null;

    }

    @Override
    public int hashCode() {
        int result = getUsername() != null ? getUsername().hashCode() : 0;
        result = 31 * result + (getPassword() != null ? getPassword().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "AuthUser{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

}
