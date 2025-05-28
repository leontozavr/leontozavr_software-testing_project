package manager;

import models.ContactData;
import models.GroupData;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContactHelper extends HelperBase {

    public ContactHelper(ApplicationManager manager) {
        super(manager);
    }

    public void openContactsPage() {
        if (!manager.isElementPresent(By.linkText("add new"))) {
            click(By.linkText("home"));
        }
    }

    public void createContact(ContactData contact) {
        openContactsPage();
        initContactCreation();
        fillContactForm(contact);
        submitContactCreation();
        returnToContactsPage();
    }

    public void createContact(ContactData contact, GroupData group) {
        openContactsPage();
        initContactCreation();
        fillContactForm(contact);
        selectGroup(group);
        submitContactCreation();
        returnToContactsPage();
    }

    private void selectGroup(GroupData group) {
        new Select(manager.driver.findElement(By.name("new_group"))).selectByValue(group.id());
    }

    private void submitContactCreation() {
        click(By.name("submit"));
    }

    private void initContactCreation() {
        click(By.linkText("add new"));
    }

    private void removeSelectedContacts() {
        click(By.cssSelector("[value=Delete]"));
    }

    public void modifyContact(ContactData contact, ContactData modifiedContact) {
        openContactsPage();
        selectContact(contact);
        initContactModification(contact);
        fillContactForm(modifiedContact);
        submitContactModification();
        returnToContactsPage();
    }

    private void initContactModification(ContactData contact) {
        click(By.xpath(String.format("//a[@href='edit.php?id=%s']", contact.id())));
    }

    private void submitContactModification() {
        click(By.name("update"));
    }

    private void returnToContactsPage() {
        click(By.linkText("home"));
    }

    private void fillContactForm(ContactData contact) {
        type(By.name("firstname"), contact.firstName());
        type(By.name("lastname"), contact.lastName());
    }

    public void selectContact(ContactData contact) {
        click(By.cssSelector(String.format("input[value='%s']", contact.id())));
    }

    public int getCount() {
        openContactsPage();
        return manager.driver.findElements(By.name("selected[]")).size();
    }

    public void removeContact(ContactData id) {
        openContactsPage();
        selectContact(id);
        removeSelectedContacts();
        returnToContactsPage();
    }

    public void removeAllContacts() {
        openContactsPage();
        selectAllContacts();
        removeSelectedContacts();
    }

    private void selectAllContacts() {
        var checkboxes = manager.driver.findElements(By.name("selected[]"));
        for (var checkbox : checkboxes) {
            checkbox.click();
        }
    }

    public List<ContactData> getList() {
        openContactsPage();
        var contacts = new ArrayList<ContactData>();
        var rows = manager.driver.findElements(By.cssSelector("tr[name='entry']"));
        for (var row : rows) {
            var id = row.findElement(By.tagName("input")).getAttribute("value");
            var firstName = row.findElement(By.xpath(".//td[3]")).getText();
            var lastName = row.findElement(By.xpath(".//td[2]")).getText();
            contacts.add(new ContactData(id, firstName, lastName, "", "", "", "", "", "", "", ""));
        }
        return contacts;
    }


    public void selectGroupFilter(GroupData group) {
        new Select(manager.driver.findElement(By.name("group"))).selectByValue(group.id());
    }

    public void removeFromGroup() {
        click(By.name("remove"));
    }
    public void selectAddToGroup(GroupData group) {
        new Select(manager.driver.findElement(By.name("to_group"))).selectByValue(group.id());
    }

    public void addToGroup() {
        click(By.name("add"));
    }

    public String getPhones(ContactData contact) {
        return manager.driver.findElement(By.xpath(String.format("//*[@id=\"%s\"]/../../td[6]", contact.id()))).getText();
    }

    public String getAddress(ContactData contact) {
        return manager.driver.findElement(By.xpath(String.format("//*[@id=\"%s\"]/../../td[4]", contact.id()))).getText();
    }
    public String getEmails(ContactData contact) {
        return manager.driver.findElement(By.xpath(String.format("//*[@id=\"%s\"]/../../td[5]", contact.id()))).getText();
    }

    public Map<String, String> getPhones() {
        var result = new HashMap<String, String>();
        List<WebElement> rows = manager.driver.findElements(By.name("entry"));
        for (WebElement row : rows) {
            var id = row.findElement(By.tagName("input")).getAttribute("id");
            var phones = row.findElements(By.tagName("td")).get(5).getText();
            result.put(id, phones);
        }
        return result;
    }

    public Map<String, String> getAddress() {
        var result = new HashMap<String, String>();
        List<WebElement> rows = manager.driver.findElements(By.name("entry"));
        for (WebElement row : rows) {
            var id = row.findElement(By.tagName("input")).getAttribute("id");
            var address = row.findElements(By.tagName("td")).get(3).getText();
            result.put(id, address);
        }
        return result;
    }

    public Map<String, String> getEmails() {
        var result = new HashMap<String, String>();
        List<WebElement> rows = manager.driver.findElements(By.name("entry"));
        for (WebElement row : rows) {
            var id = row.findElement(By.tagName("input")).getAttribute("id");
            var address = row.findElements(By.tagName("td")).get(4).getText();
            result.put(id, address);
        }
        return result;
    }

    public void compareRows(ContactData contact) {
        openModifyPage(contact);
       Assertions.assertEquals(contact.home(),manager.driver.findElement(By.name("home")).getAttribute("value"),"incorrect home");
       Assertions.assertEquals(contact.work(),manager.driver.findElement(By.name("work")).getAttribute("value"),"incorrect work");
       Assertions.assertEquals(contact.mobile(),manager.driver.findElement(By.name("mobile")).getAttribute("value"),"incorrect mobile");
       Assertions.assertEquals(contact.fax(),manager.driver.findElement(By.name("fax")).getAttribute("value"),"incorrect fax");
       Assertions.assertEquals(contact.address(),manager.driver.findElement(By.name("address")).getAttribute("value"),"incorrect address");
       Assertions.assertEquals(contact.email1(),manager.driver.findElement(By.name("email")).getAttribute("value"),"incorrect email");
       Assertions.assertEquals(contact.email2(),manager.driver.findElement(By.name("email2")).getAttribute("value"),"incorrect email2");
       Assertions.assertEquals(contact.email3(),manager.driver.findElement(By.name("email3")).getAttribute("value"),"incorrect email3");
    }

    private void openModifyPage(ContactData contact) {
        openContactsPage();
        click(By.xpath(String.format("//*[@id=\"%s\"]/../../td[8]", contact.id())));
    }
}
