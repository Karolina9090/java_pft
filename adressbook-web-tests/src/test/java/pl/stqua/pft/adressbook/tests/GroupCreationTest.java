package pl.stqua.pft.adressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import pl.stqua.pft.adressbook.model.GroupData;

public class GroupCreationTest extends TestBase {

  @Test
  public void testGroupCreation() {
    app.getNavitagionHelper().goToGroupPage();
    int before = app.getGroupHelpers().getGroupCount();
    app.getGroupHelpers().createGroup(new GroupData("test1", null, null));
    int after = app.getGroupHelpers().getGroupCount();
    Assert.assertEquals(after, before + 1);
  }

}
