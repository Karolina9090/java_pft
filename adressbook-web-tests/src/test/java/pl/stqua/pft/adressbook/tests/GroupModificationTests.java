package pl.stqua.pft.adressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pl.stqua.pft.adressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class GroupModificationTests extends TestBase {

  @Test
  public void testGroupModification() {
    app.getNavitagionHelper().goToGroupPage();
    if (!app.getGroupHelpers().isThereAGroup()) {
      app.getGroupHelpers().createGroup(new GroupData("test1", null, null));
    }
    List<GroupData> before = app.getGroupHelpers().getGroupList();
    app.getGroupHelpers().selectGroup(before.size() -1);
    app.getGroupHelpers().initGroupModification();
    GroupData group = new GroupData( before.get(before.size() -1).getId(0), "test1", "test1", "test3");
    app.getGroupHelpers().fillGroupForm(group);
    app.getGroupHelpers().submitGroupModification();
    app.getGroupHelpers().returnToGroupPage();
    List<GroupData> after = app.getGroupHelpers().getGroupList();
    Assert.assertEquals(after.size(), before.size());

    before.remove(before.size() -1);
    before.add(group);
    Comparator<? super GroupData> byID = (g1, g2) -> Integer.compare(g1.getId(0), g2.getId(0));
    before.sort(byID);
    after.sort(byID);
    Assert.assertEquals (before, after);
  }

}
