package pl.stqua.pft.adressbook.tests;

import org.testng.annotations.*;
import pl.stqua.pft.adressbook.model.GroupData;
import pl.stqua.pft.adressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTest extends TestBase {

  @Test
  public void testGroupCreation() {
    app.goTo().groupPage();
    Groups before = app.group().all();
    GroupData group = new GroupData().withName("test2");
    app.group().create(group);
    Groups after = app.group().all();
    assertThat(after.size(), equalTo(before.size() + 1));
    assertThat(after,
            equalTo(before.withAdded(group.withId(after.stream().mapToInt((g) -> g.getId(0)).max().getAsInt()))));

  }

}
