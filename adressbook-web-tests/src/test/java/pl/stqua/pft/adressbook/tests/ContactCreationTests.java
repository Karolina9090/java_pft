package pl.stqua.pft.adressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pl.stqua.pft.adressbook.model.ContactData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    app.getNavitagionHelper().goToAddNewContact();
    List<ContactData> before = app.getContactHelpers().getContactList();
    ContactData contact = new ContactData(0, "Test 2", "Test 3", "000000000", "tests@tests.com", "test1", "test1");
    app.getContactHelpers().createContact(contact, true);
    List<ContactData> after = app.getContactHelpers().getContactList();
    Assert.assertEquals(after.size(), before.size() + 1);

    before.add(contact);
    Comparator<? super ContactData> byID = (g1, g2) -> Integer.compare(g1.getId(0), g2.getId(0));
    before.sort(byID);
    after.sort(byID);
    Assert.assertEquals(before, after);
  }

}
