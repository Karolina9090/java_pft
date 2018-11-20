package pl.stqua.pft.adressbook.tests;

import org.testng.annotations.Test;

public class GroupDeletionTests extends TestBase {

  @Test
  public void testGroupDeletion() throws Exception {
    app.getNavitagionHelper().goToGroupPage();
    app.getGroupHelpers().selectGroup();
    app.getGroupHelpers().deleteSelectedGroup();
    app.getGroupHelpers().returnToGroupPage();
  }

}
