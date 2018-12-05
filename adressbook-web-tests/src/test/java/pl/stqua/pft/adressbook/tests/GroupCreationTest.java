package pl.stqua.pft.adressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import pl.stqua.pft.adressbook.model.GroupData;

import java.util.List;

public class GroupCreationTest extends TestBase {

  @Test
  public void testGroupCreation() {
    app.getNavitagionHelper().goToGroupPage();
    List<GroupData> before = app.getGroupHelpers().getGroupList();
    app.getGroupHelpers().createGroup(new GroupData("test1", null, null));
    List<GroupData> after = app.getGroupHelpers().getGroupList();
    Assert.assertEquals(after.size(), before.size() + 1);
  }

}
