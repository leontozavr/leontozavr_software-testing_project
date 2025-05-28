package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.regex.Pattern;

public class MailTests extends TestBase {
    @Test
    void canReceiveEmail() {
        var messages = manager.mail().receive("user1@localhost", "password", Duration.ofSeconds(60));
        Assertions.assertEquals(1, messages.size());
        System.out.println(messages);
    }

    @Test
    void canDrainInbox() {
        manager.mail().drain("user1@localhost", "password");
    }

    @Test
    void canExtractUrl() {
        var messages = manager.mail().receive("user1@localhost", "password", Duration.ofSeconds(10));
        var text = messages.getFirst().content();
        var pattern = Pattern.compile("http://\\S*");
        var matcher = pattern.matcher(text);
        if (matcher.find()) {
            var url = text.substring(matcher.start(), matcher.end());
            System.out.println(url);
        }
    }
}