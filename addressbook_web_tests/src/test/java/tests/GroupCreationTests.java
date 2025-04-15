package tests;

import models.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GroupCreationTests extends TestBase {

    @Test
    public void canCreateGroup() {
        int groupCount = app.groups().getCount();
        app.groups().createGroup(new GroupData("group name", "group header", "group header"));
        int newGroupCount = app.groups().getCount();
        Assertions.assertEquals(groupCount + 1, newGroupCount);
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

    @Test
    public void canCreateMultipleGroups() {
        int n = 2;
        int groupCount = app.groups().getCount();

        for (int i = 0; i < n; i++) {
            app.groups().createGroup(new GroupData(randomString(i), "group header", "group header"));
        }

        int newGroupCount = app.groups().getCount();
        Assertions.assertEquals(groupCount + n, newGroupCount);
    }
}
