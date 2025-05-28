package manager;
import org.openqa.selenium.By;
import models.MailMessage;
import java.util.List;
import java.util.regex.Pattern;

public class CreationHelper extends HelperBase {
    public CreationHelper(ApplicationManager manager) {
        super(manager);
    }

    public void createMantisUser(String username, String email) {
        click(By.cssSelector("a[href='signup_page.php']"));
        type(By.name("username"), username);
        type(By.name("email"), email);
        click(By.cssSelector("input[value='Signup']"));
    }
    public void confirmCreate(String username, String password) {
        type(By.name("realname"), username);
        type(By.name("password"), password);
        type(By.name("password_confirm"), password);
        click(By.xpath("//fieldset/span/button"));
    }    public String getUrl(List<MailMessage> inbox) {
        var text = inbox.getFirst().content();
        var pattern = Pattern.compile("http://\\S*");
        var matcher = pattern.matcher(text);
        String url = "";
        if (matcher.find()) {
            url = text.substring(matcher.start(), matcher.end());
        }
        return url;
    }
}
