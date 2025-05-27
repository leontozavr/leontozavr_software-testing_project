package tests;

import common.CommonFunctions;
import manager.JdbcHelper;
import models.ContactData;
import models.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LinkBetweenContactsAndGroups extends TestBase {

    @Test
    void addContactToGroup() {
        var contactData = new ContactData()
                .withFirstName(CommonFunctions.randomString(10))
                .withLastName(CommonFunctions.randomString(10));
        var groupData = new GroupData()
                .withName(CommonFunctions.randomString(10))
                .withHeader(CommonFunctions.randomString(10))
                .withFooter(CommonFunctions.randomString(10));
        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(contactData);
        }
        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(groupData);
        }
        var groupList = app.hbm().getGroupList();
        var contactList = app.jdbc().getContactsWithoutGroups();
        if (contactList.isEmpty()){
            app.hbm().createContact(contactData);
            contactList = app.jdbc().getContactsWithoutGroups();
        }
        app.contacts().openContactsPage();
        app.contacts().selectContact(contactList.getFirst());
        app.contacts().selectAddToGroup(groupList.getFirst());
        app.contacts().addToGroup();
        Assertions.assertTrue(JdbcHelper.checkLinkBetweenContactsAndGroups(groupList.getFirst(), contactList.getFirst()), "Произошла ошибка");
    }

    @Test
    void removeContactFromGroup() {
        var contactData = new ContactData()
                .withFirstName(CommonFunctions.randomString(10))
                .withLastName(CommonFunctions.randomString(10));
        var groupData = new GroupData()
                .withName(CommonFunctions.randomString(10))
                .withHeader(CommonFunctions.randomString(10))
                .withFooter(CommonFunctions.randomString(10));
        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(contactData);
        }
        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(groupData);
        }

        var group = app.hbm().getGroupList().getFirst();
        var contact = app.hbm().getContactList().getFirst();
        if (!JdbcHelper.checkLinkBetweenContactsAndGroups(group, contact)) {
            JdbcHelper.createLinkBetweenContactAndGroup(group,contact);
        }
        app.contacts().openContactsPage();
        app.contacts().selectGroupFilter(group);
        app.contacts().selectContact(contact);
        app.contacts().removeFromGroup();
        Assertions.assertFalse(JdbcHelper.checkLinkBetweenContactsAndGroups(group, contact), "Произошла ошибка");
    }
}