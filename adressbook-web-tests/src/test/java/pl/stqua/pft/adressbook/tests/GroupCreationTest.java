package pl.stqua.pft.adressbook.tests;

import org.testng.annotations.*;
import pl.stqua.pft.adressbook.model.GroupData;

public class GroupCreationTest extends TestBase {

  @Test
  public void testGroupCreation() throws Exception {
    app.goToGroupPage();
    app.initGroupCreation();
    app.fillGroupForm(new GroupData("test1", "test1", "test3"));
    app.submitGroupCreation();
    app.returnToGroupPage();
  }

}
