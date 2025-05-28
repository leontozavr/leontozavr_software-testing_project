package tests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import common.CommonFunctions;
import models.ContactData;
import models.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ContactCreationTests extends TestBase {

    public static List<ContactData> contactProvider() throws IOException {
        var result = new ArrayList<ContactData>();
        ObjectMapper mapper = new ObjectMapper();
        var value = mapper.readValue(new File("contacts.json"), new TypeReference<List<ContactData>>() {});
        result.addAll(value);
        return result;
    }

    public static List<ContactData> negativeContactProvider() {
        return List.of(
                new ContactData("", "name'", "", "", "", "", "", "", "", "", "")
        );
    }

    public static List<ContactData> singleRandomContact(){
        return List.of(new ContactData()
                .withFirstName(CommonFunctions.randomString(10))
                .withLastName(CommonFunctions.randomString(10)));
    }

    @ParameterizedTest
    @MethodSource("singleRandomContact")
    public void canCreateContact(ContactData contact) {
        var oldIds = app.hbm().getContactList();
        app.contacts().createContact(contact);
        var newIds = app.hbm().getContactList();
        var newId = newIds.stream()
                .filter(id -> !oldIds.contains(id))
                .findFirst()
                .orElseThrow(() -> new AssertionError("Не найден новый ID в списке"));

        var expectedContact = contact.withId(String.valueOf(newId));
        var actualContact = new ContactData(String.valueOf(newId),
                contact.firstName(),
                contact.lastName(),
                contact.address(),
                contact.home(),
                contact.mobile(),
                contact.work(),
                contact.fax(),
                contact.email1(),
                contact.email2(),
                contact.email3());
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

    @Test
    void canCreateContactInGroup() {
        var contact = new ContactData()
                .withFirstName(CommonFunctions.randomString( 10))
                .withLastName(CommonFunctions.randomString( 10));
        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(new GroupData("", "group name", "group header", "group footer"));
        }
        var group = app.hbm().getGroupList().getFirst();
        var oldRelated = app.hbm().getContactsInGroup(group);
        app.contacts().createContact(contact, group);
        var newRelated = app.hbm().getContactsInGroup(group);
        Assertions.assertEquals(oldRelated.size() + 1, newRelated.size());
    }
}
