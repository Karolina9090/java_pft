package pl.stqua.pft.adressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pl.stqua.pft.adressbook.model.GroupData;

public class GroupModificationTests extends TestBase {

  @Test
  public void testGroupModification() {
    app.getNavitagionHelper().goToGroupPage();
    int before = app.getGroupHelpers().getGroupCount();
    if (!app.getGroupHelpers().isThereAGroup()) {
      app.getGroupHelpers().createGroup(new GroupData("test1", null, null));
    }

    app.getGroupHelpers().selectGroup(before -1);
    app.getGroupHelpers().initGroupModification();
    app.getGroupHelpers().fillGroupForm(new GroupData("test1", "test1", "test3"));
    app.getGroupHelpers().submitGroupModification();
    app.getGroupHelpers().returnToGroupPage();
    int after = app.getGroupHelpers().getGroupCount();
    Assert.assertEquals(after, before);
  }

}
