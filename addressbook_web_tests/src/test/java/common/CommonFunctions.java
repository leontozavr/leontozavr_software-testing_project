package common;

import java.io.File;
import java.nio.file.Paths;
import java.util.Random;

public class CommonFunctions {
    public static String randomString(int n) {
        var result = "";
        var rnd = new Random();
        for (int i = 0; i < n; i++) {
            result = result + (char)('a' + rnd.nextInt(26));
        }
        return result;
    }
}
