package pl.stqua.pft.adressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.stqua.pft.adressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class GroupModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.getNavitagionHelper().goToGroupPage();
    if (!app.getGroupHelpers().isThereAGroup()) {
      app.getGroupHelpers().createGroup(new GroupData("test1", null, null));
    }
  }

  @Test
  public void testGroupModification() {
    List<GroupData> before = app.getGroupHelpers().getGroupList();
    int index = before.size() - 1;
    GroupData group = new GroupData( before.get(index).getId(0), "test1", "test1", "test3");
    app.getGroupHelpers().modifyGroup(index, group);
    List<GroupData> after = app.getGroupHelpers().getGroupList();
    Assert.assertEquals(after.size(), before.size());

    before.remove(index);
    before.add(group);
    Comparator<? super GroupData> byID = (g1, g2) -> Integer.compare(g1.getId(0), g2.getId(0));
    before.sort(byID);
    after.sort(byID);
    Assert.assertEquals (before, after);
  }


}
