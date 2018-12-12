package pl.stqua.pft.adressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import pl.stqua.pft.adressbook.model.GroupData;
import java.util.Set;

public class GroupCreationTest extends TestBase {

  @Test
  public void testGroupCreation() {
    app.goTo().groupPage();
    Set<GroupData> before = app.group().all();
    GroupData group = new GroupData().withName("test2");
    app.group().create(group);
    Set<GroupData> after = app.group().all();
    Assert.assertEquals(after.size(), before.size() + 1);

    group.withId(after.stream().mapToInt((g) -> g.getId(0)).max().getAsInt());
    before.add(group);
    Assert.assertEquals(before, after);

  }

}
