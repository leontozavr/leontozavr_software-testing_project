package tests;

import common.CommonFunctions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Duration;

public class UserRegistrationTests extends TestBase {

    @Test
    void canRegisterUser() {
        String username = CommonFunctions.randomString(8);
        String password = "password";
        var email = String.format("%s@localhost", username);
        manager.jamesCli().addUser(email, password);
        manager.create().createMantisUser(username, email);
        var inbox = manager.mail().receive(email, password, Duration.ofSeconds(10));
        String url = manager.create().getUrl(inbox);
        manager.driver().get(url);
        manager.create().confirmCreate(username, password);
        manager.http().login(username, password);
        Assertions.assertTrue(manager.http().isLoggedIn());
    }

    @Test
    void canApiRegisterUser() {
        String username = CommonFunctions.randomString(8);
        String password = "password";
        var email = String.format("%s@localhost", username);
        manager.jamesApiHelper().addUser(email, password);
        manager.rest().createUser(username, password);
        var inbox = manager.mail().receive(email, password, Duration.ofSeconds(10));
        String url = manager.create().getUrl(inbox);
        manager.driver().get(url);
        manager.create().confirmCreate(username, password);
        manager.http().login(username, password);
        Assertions.assertTrue(manager.http().isLoggedIn());
    }
}