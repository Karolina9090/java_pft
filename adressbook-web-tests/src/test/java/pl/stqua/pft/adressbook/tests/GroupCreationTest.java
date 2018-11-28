package pl.stqua.pft.adressbook.tests;

import org.testng.annotations.*;
import pl.stqua.pft.adressbook.model.GroupData;

public class GroupCreationTest extends TestBase {

  @Test
  public void testGroupCreation() {
    app.getNavitagionHelper().goToGroupPage();
    app.getGroupHelpers().createGroup(new GroupData("test1", null, null));
  }

}
