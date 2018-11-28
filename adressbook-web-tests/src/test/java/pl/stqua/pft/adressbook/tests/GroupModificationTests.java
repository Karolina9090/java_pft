package pl.stqua.pft.adressbook.tests;

import org.testng.annotations.Test;
import pl.stqua.pft.adressbook.model.GroupData;

public class GroupModificationTests extends TestBase {

  @Test
  public void testGroupModification() {
    app.getNavitagionHelper().goToGroupPage();
    if (!app.getGroupHelpers().isThereAGroup()) {
      app.getGroupHelpers().createGroup(new GroupData("test1", null, null));
    }

    app.getGroupHelpers().selectGroup();
    app.getGroupHelpers().initGroupModification();
    app.getGroupHelpers().fillGroupForm(new GroupData("test1", "test1", "test3"));
    app.getGroupHelpers().submitGroupModification();
    app.getGroupHelpers().returnToGroupPage();
  }

}
