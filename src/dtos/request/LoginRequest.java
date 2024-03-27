package dtos.request;

public class LoginRequest {
    private  String Username;
    private  String password;
    public LoginRequest(){

    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        this.Username = username.toLowerCase();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
