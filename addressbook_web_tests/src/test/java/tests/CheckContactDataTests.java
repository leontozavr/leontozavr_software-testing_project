package tests;

import common.CommonFunctions;
import models.ContactData;
import org.junit.jupiter.api.Assertions;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;

public class CheckContactDataTests extends TestBase {

    @Test
    void testPhones() {
        var contacts = app.hbm().getContactList();
        var expected = contacts.stream().collect(Collectors.toMap(ContactData::id, contact ->
                Stream.of(contact.home(), contact.mobile(), contact.work(), contact.fax())
                        .filter(s -> s != null && !s.isEmpty())
                        .collect(Collectors.joining("\n"))
        ));
        var phones = app.contacts().getPhones();
        Assertions.assertEquals(expected, phones);
    }

    @Test
    void testInfo() {
        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(new ContactData()
                    .withFirstName(CommonFunctions.randomString(10))
                    .withLastName(CommonFunctions.randomString(10)));
        }
        var contacts = app.hbm().getContactList();
        var expectedPhones = contacts.stream().collect(Collectors.toMap(ContactData::id, contact ->
                Stream.of(contact.home(), contact.mobile(), contact.work(), contact.fax())
                        .filter(s -> s != null && !s.isEmpty())
                        .collect(Collectors.joining("\n"))
        ));
        var phones = app.contacts().getPhones(contacts.getFirst());
        Assertions.assertEquals(expectedPhones.get(contacts.getFirst().id()), phones, "incorrect phones in main");

        var expectedAddress = contacts.getFirst().address();
        var address = app.contacts().getAddress(contacts.getFirst());
        Assertions.assertEquals(expectedAddress, address, "incorrect address in main");

        var expectedEmails = contacts.stream().collect(Collectors.toMap(ContactData::id, contact ->
                Stream.of(contact.email1(), contact.email2(), contact.email3())
                        .filter(s -> s != null && !s.isEmpty())
                        .collect(Collectors.joining("\n"))
        ));
        var emails = app.contacts().getEmails(contacts.getFirst());
        Assertions.assertEquals(expectedEmails.get(contacts.getFirst().id()), emails, "incorrect emails in main");

        app.contacts().compareRows(contacts.getFirst());

    }
}

