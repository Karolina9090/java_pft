package pl.stqua.pft.adressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import pl.stqua.pft.adressbook.model.GroupData;

import java.util.HashSet;
import java.util.List;

public class GroupCreationTest extends TestBase {

  @Test
  public void testGroupCreation() {
    app.getNavitagionHelper().goToGroupPage();
    List<GroupData> before = app.getGroupHelpers().getGroupList();
    GroupData group = new GroupData("test1", null, null);
    app.getGroupHelpers().createGroup(group);
    List<GroupData> after = app.getGroupHelpers().getGroupList();
    Assert.assertEquals(after.size(), before.size() + 1);

    int max = 0;
    for (GroupData g : after) {
      if (g.getId(max) > max) {
        max = g.getId(max);
      }
    }

    group.getId(max);
    before.add(group);
    before.add(group);
    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
  }

}
