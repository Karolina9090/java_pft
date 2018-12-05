package pl.stqua.pft.adressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pl.stqua.pft.adressbook.model.ContactData;
import pl.stqua.pft.adressbook.model.GroupData;

import java.util.List;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    app.getNavitagionHelper().goToAddNewContact();
    List<ContactData> before = app.getContactHelpers().getContactList();
    app.getContactHelpers().createContact((new ContactData("Test 1", "Test 2", "Test 3", "000000000", "tests@tests.com", "test1")), true);
    List<ContactData> after = app.getContactHelpers().getContactList();
    Assert.assertEquals(after.size(), before.size() + 1);
  }

}
