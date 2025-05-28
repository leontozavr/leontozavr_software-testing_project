package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LoginTests extends TestBase {

    @Test
    void canLogin() {
        manager.session().login("administrator", "root");
        Assertions.assertTrue(manager.session().isLoggedIn());
    }
}