package instagram.core_objects;

import org.brunocvcunha.instagram4j.Instagram4j;

import java.io.IOException;

public class Login {
    private String username;
    private String password;

    public Login(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Instagram4j send() {
        Instagram4j instagram = Instagram4j.builder().username(username).password(password).build();
        instagram.setup();

        try {
            instagram.login();
        }

        catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }

        return instagram;
    }
}