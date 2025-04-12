package tests;

import models.ContactData;
import org.junit.jupiter.api.Test;

public class ContactCreationTests extends TestBase {
    @Test
    public void canCreateContact() {
        app.contacts().createContact(new ContactData("First", "Middle", "Last", "Nick"));
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
}
