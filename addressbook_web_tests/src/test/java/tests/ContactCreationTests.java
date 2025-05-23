package tests;

import models.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;

public class ContactCreationTests extends TestBase {

    public static List<ContactData> contactProvider() {
        var result = new ArrayList<ContactData>();
        for (var firstName : List.of("", "First", randomString(5))) {
            for (var lastName : List.of("", "Last", randomString(7))) {
                result.add(new ContactData("", firstName, "", lastName, ""));
            }
        }
        return result;
    }

    public static List<ContactData> negativeContactProvider() {
        return List.of(
                new ContactData("", "name'", "", "", "")
        );
    }

    @ParameterizedTest
    @MethodSource("contactProvider")
    public void canCreateContact(ContactData contact) {
        var oldIds = app.contacts().getList();
        app.contacts().createContact(contact);
        var newIds = app.contacts().getList();
        var newId = newIds.stream()
                .filter(id -> !oldIds.contains(id))
                .findFirst()
                .orElseThrow(() -> new AssertionError("Не найден новый ID в списке"));

        var expectedContact = contact.withId(String.valueOf(newId));
        var actualContact = new ContactData(String.valueOf(newId), contact.firstName(), "", contact.lastName(), "");
        Assertions.assertEquals(expectedContact, actualContact, "Созданный контакт не соответствует ожидаемым данным");
    }

    @ParameterizedTest
    @MethodSource("negativeContactProvider")
    public void canNotCreateContact(ContactData contact) {
        var oldIds = app.contacts().getList();
        app.contacts().createContact(contact);
        var newIds = app.contacts().getList();
        Assertions.assertEquals(oldIds, newIds, "Список контактов изменился после попытки создания некорректного контакта");
    }
}
