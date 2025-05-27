package manager;

import models.GroupData;
import models.ContactData;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcHelper extends HelperBase{
    public JdbcHelper (ApplicationManager manager) { super (manager); }

    public List<GroupData> getGroupList() {
        var groups = new ArrayList<GroupData>();
        try (var conn = DriverManager.getConnection("jdbc:mysql://localhost/addressbook", "root", "");
             var statement = conn.createStatement();
             var result = statement.executeQuery("SELECT group_id, group_name, group_header, group_footer FROM group_list")) {

            while (result.next()) {
                groups.add(new GroupData()
                        .withId(result.getString("group_id"))
                        .withName(result.getString("group_name"))
                        .withHeader(result.getString("group_header"))
                        .withFooter(result.getString("group_footer")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return groups;
    }

    public void checkConsistency() {
        try (var conn = DriverManager.getConnection("jdbc:mysql://localhost/addressbook", "root", "");
             var statement = conn.createStatement();
             var result = statement.executeQuery("SELECT * FROM `address_in_groups` ag LEFT JOIN addressbook ab ON ab.id = ag.id WHERE ab.id IS NULL")) {

            if (result.next()) {
                throw new IllegalStateException("DB consistency error");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<ContactData> getContactsWithoutGroups() {
        var contactsWithoutGroups = new ArrayList<ContactData>();
        try (var conn = DriverManager.getConnection("jdbc:mysql://localhost/addressbook", "root", "");
             var statement = conn.createStatement();
             var result = statement.executeQuery("SELECT *, ab.id as contactId FROM address_in_groups aig right join addressbook ab on ab.id = aig.id where aig.id is null")) {
            while (result.next()) {
                contactsWithoutGroups.add(new ContactData()
                        .withId(result.getString("contactId")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return contactsWithoutGroups;
    }

    public static boolean checkLinkBetweenContactsAndGroups(GroupData group, ContactData contact) {
        try (var conn = DriverManager.getConnection("jdbc:mysql://localhost/addressbook", "root", "");
             var statement = conn.createStatement();
             var result = statement.executeQuery(String.format("SELECT * FROM address_in_groups WHERE id = \"%s\" AND group_id = \"%s\"", contact.id(), group.id()))) {
            return result.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void createLinkBetweenContactAndGroup(GroupData group, ContactData contact) {
        try (var conn = DriverManager.getConnection("jdbc:mysql://localhost/addressbook","root",""))
        {
            String query = String.format("INSERT INTO address_in_groups VALUES (0,\"%s\",\"%s\",\"%s\",\"%s\",\"%s\")",
                    contact.id(), group.id(),java.time.LocalDateTime.now(),java.time.LocalDateTime.now(),java.time.LocalDateTime.now());
            conn.createStatement().executeUpdate(query);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
