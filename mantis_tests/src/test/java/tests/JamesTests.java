package tests;

import common.CommonFunctions;
import org.junit.jupiter.api.Test;

public class JamesTests extends TestBase {
    @Test
    void canCreateUser() {
        manager.jamesCli().addUser(String.format("%s@localhost", CommonFunctions.randomString(8)), "password");
    }

    @Test
    void apiCreateUser() {
        var email = String.format("%s@localhost", CommonFunctions.randomString(5));
        var password = "password";
        manager.jamesApiHelper().addUser(email, password);
    }
}