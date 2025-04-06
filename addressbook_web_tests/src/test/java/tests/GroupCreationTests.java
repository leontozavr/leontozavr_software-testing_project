package tests;

import models.GroupData;
import org.junit.jupiter.api.Test;

public class GroupCreationTests extends TestBase {

    @Test
    public void canCreateGroup() {
        app.groups().createGroup(new GroupData("group name", "group header", "group header"));
    }

    @Test
    public void canCreateGroupWithEmptyName() {
        app.groups().createGroup(new GroupData());
    }

    @Test
    public void canCreateGroupWithNameOnly() {
        var emptyGroup = new GroupData();
        var groupWithName = emptyGroup.withName("name");
        app.groups().createGroup(groupWithName);
    }

    @Test
    public void canCreateGroupWithHeaderOnly() {
        var emptyGroup = new GroupData();
        var groupWithName = emptyGroup.withHeader("name");
        app.groups().createGroup(groupWithName);
    }

    @Test
    public void canCreateGroupWithFooterOnly() {
        app.groups().createGroup(new GroupData().withFooter("name"));
    }
}
