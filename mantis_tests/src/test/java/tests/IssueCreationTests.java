package tests;

import common.CommonFunctions;
import models.IssueData;
import org.junit.jupiter.api.Test;

public class IssueCreationTests extends TestBase {

    @Test
    void canCreateIssue() {
        manager.rest().createIssue(new IssueData()
                .withSummary(CommonFunctions.randomString(10))
                .withDescription(CommonFunctions.randomString(50))
                .withProject(1L));
    }
}
