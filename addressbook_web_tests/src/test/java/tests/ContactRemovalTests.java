package tests;

import models.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;

public class ContactRemovalTests extends TestBase {

    @Test
    public void canRemovalContact() {
        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(new ContactData("", "First", "Last", "", "", "", "", "", "", "", ""));
        }
        var oldIds = app.hbm().getContactList();
        var rnd = new Random();
        var index = rnd.nextInt(oldIds.size());
        app.contacts().removeContact(oldIds.get(index));
        var newIds = app.hbm().getContactList();
        var expected = new ArrayList<>(oldIds);
        expected.remove(index);
        Assertions.assertEquals(newIds, expected);
    }

    @Test
    void canRemoveAllContactsAtOnce() {
        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(new ContactData("", "First", "Last", "", "", "", "", "", "", "", ""));
        }
        app.contacts().removeAllContacts();
        Assertions.assertEquals(0, app.hbm().getContactCount());
    }
}
