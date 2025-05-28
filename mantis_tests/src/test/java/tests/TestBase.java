package tests;

import org.junit.jupiter.api.BeforeEach;
import manager.ApplicationManager;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class TestBase {

    protected static ApplicationManager manager;

    @BeforeEach
    public void setUp() throws IOException {
        if (manager == null) {
            var properties = new Properties();
            properties.load(new FileReader(System.getProperty("target","local.properties") ));
            manager = new ApplicationManager();
            manager.init(System.getProperty("browser", "chrome"),properties);
        }
    }
}