package pl.stqua.pft.adressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.stqua.pft.adressbook.model.GroupData;

import java.util.List;

public class GroupDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.getNavitagionHelper().goToGroupPage();
    if (!app.getGroupHelpers().isThereAGroup()) {
      app.getGroupHelpers().createGroup(new GroupData("test1", null, null));
    }
  }

  @Test
  public void testGroupDeletion() {
    List<GroupData> before = app.getGroupHelpers().getGroupList();
    app.getGroupHelpers().selectGroup(before.size() - 1);
    app.getGroupHelpers().deleteSelectedGroup();
    app.getGroupHelpers().returnToGroupPage();
    List<GroupData> after = app.getGroupHelpers().getGroupList();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(before.size() - 1);
    {
      Assert.assertEquals(before, after);
    }
  }
}
