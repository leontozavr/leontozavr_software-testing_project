package manager;

import models.ContactData;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

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

    private void submitContactCreation() {
        click(By.name("submit"));
    }

    private void initContactCreation() {
        click(By.linkText("add new"));
    }

    private void removeSelectedContacts() {
        click(By.cssSelector("[value=Delete]"));
    }

    private void modifyContact(ContactData contact, ContactData modifiedContact) {
        openContactsPage();
        selectContact(contact);
        initContactModification(contact);
        fillContactForm(modifiedContact);
        submitContactModification();
        returnToContactsPage();
    }

    private void initContactModification(ContactData contact) {
        click(By.cssSelector(String.format("a[href='edit.php?=%s']", contact.id())));
    }

    private void submitContactModification() {
        click(By.name("update"));
    }

    private void returnToContactsPage() {
        click(By.linkText("home"));
    }

    private void fillContactForm(ContactData contact) {
        type(By.name("firstname"), contact.firstName());
        type(By.name("middlename"), contact.middleName());
        type(By.name("lastname"), contact.lastName());
        type(By.name("nickname"), contact.nickName());
    }

    private void selectContact(ContactData contact) {
        click(By.cssSelector(String.format("input[value='%s']", contact.id())));
    }

    public int getCount() {
        openContactsPage();
        return manager.driver.findElements(By.name("selected[]")).size();
    }

    public void removeContact(int id) {
        openContactsPage();
        manager.driver.findElement(By.cssSelector("input[value='" + id + "']")).click();
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

    public List<Integer> getList() {
        openContactsPage();
        var ids = new ArrayList<Integer>();
        var rows = manager.driver.findElements(By.cssSelector("tr[name='entry']"));
        for (var row : rows) {
            String idStr = row.findElement(By.tagName("input")).getAttribute("value");
            ids.add(Integer.parseInt(idStr));
        }
        return ids;
    }
}
