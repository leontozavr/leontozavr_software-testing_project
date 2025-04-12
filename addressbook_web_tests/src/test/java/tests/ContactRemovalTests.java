package tests;

import models.ContactData;
import org.junit.jupiter.api.Test;

public class ContactRemovalTests extends TestBase {
    @Test
    public void canRemovalContact() {
        if (!app.contacts().isContactPresent()) {
            app.contacts().createContact(new ContactData("First", "Middle", "Last", "Nick"));
        }
        app.contacts().removeContact();
    }
}
