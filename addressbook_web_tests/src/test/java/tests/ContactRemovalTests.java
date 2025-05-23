package tests;

import models.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;

public class ContactRemovalTests extends TestBase {

    @Test
    public void canRemovalContact() {
        if (app.contacts().getCount() == 0) {
            app.contacts().createContact(new ContactData("", "First", "Middle", "Last", ""));
        }
        var oldIds = app.contacts().getList();
        var rnd = new Random();
        var index = rnd.nextInt(oldIds.size());
        app.contacts().removeContact(oldIds.get(index));
        var newIds = app.contacts().getList();
        var expected = new ArrayList<>(oldIds);
        expected.remove(index);
        Assertions.assertEquals(newIds, expected);
    }

    @Test
    void canRemoveAllContactsAtOnce() {
        if (app.contacts().getCount() == 0) {
            app.contacts().createContact(new ContactData("", "First", "Middle", "Last", ""));
        }
        app.contacts().removeAllContacts();
        Assertions.assertEquals(0, app.contacts().getCount());
    }
}
