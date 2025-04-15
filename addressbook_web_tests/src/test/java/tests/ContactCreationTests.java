package tests;

import models.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ContactCreationTests extends TestBase {
    @Test
    public void canCreateContact() {
        int contactCount = app.contacts().getCount();
        app.contacts().createContact(new ContactData("First", "Middle", "Last", "Nick"));
        int newContactCount = app.contacts().getCount();
        Assertions.assertEquals(contactCount + 1, newContactCount);
    }

    @Test
    public void canCreateContactWithEmptyData() {
        app.contacts().createContact(new ContactData());
    }

    @Test
    public void canCreateContactWithFirstNameOnly() {
        var emptyContact = new ContactData();
        var contactWithFirstName = emptyContact.withFirstName("First");
        app.contacts().createContact(contactWithFirstName);
    }

    @Test
    public void canCreateContactWithMiddleNameOnly() {
        var emptyContact = new ContactData();
        var contactWithMiddleName = emptyContact.withMiddleName("Middle");
        app.contacts().createContact(contactWithMiddleName);
    }

    @Test
    public void canCreateContactWithLastNameOnly() {
        app.contacts().createContact(new ContactData().withLastName("Last"));
    }

    @Test
    public void canCreateContactWithNickNameOnly() {
        app.contacts().createContact(new ContactData().withNickName("Nick"));
    }

    @Test
    public void canCreateMultipleContacts() {
        int n = 5;
        int contactCounts = app.contacts().getCount();

        for (int i = 0; i < n; i++) {
            app.contacts().createContact(new ContactData(randomString(i), "Middle", randomString(i), "Nick"));
        }

        int newContactCount = app.contacts().getCount();
        Assertions.assertEquals(contactCounts + n, newContactCount);
    }
}
