package use_case.Login;

public class LoginInputData {
    final private String username;
    final private String password;
    final private boolean switchToSignUp;

    public LoginInputData(String username, String password, boolean switchToSignUp) {
        this.username = username;
        this.password = password;
        this.switchToSignUp = switchToSignUp;
    }

    String getUsername() {
        return username;
    }

    String getPassword() {
        return password;
    }

    boolean getSwitchToSignUp() { return switchToSignUp; }
}
