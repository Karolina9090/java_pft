package pl.stqua.pft.adressbook.tests;

import org.testng.annotations.Test;
import pl.stqua.pft.adressbook.model.GroupData;

public class GroupDeletionTests extends TestBase {

  @Test
  public void testGroupDeletion() {
    app.getNavitagionHelper().goToGroupPage();
    if (! app.getGroupHelpers().isThereAGroup()) {
      app.getGroupHelpers().createGroup(new GroupData("test1", null, null));
    }

    app.getGroupHelpers().selectGroup();
    app.getGroupHelpers().deleteSelectedGroup();
    app.getGroupHelpers().returnToGroupPage();
  }

}
