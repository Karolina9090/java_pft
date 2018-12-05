package pl.stqua.pft.adressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pl.stqua.pft.adressbook.model.ContactData;

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

    int max = 0;
    for (ContactData g : after) {
      if (g.getId(max) > max) {
        max = g.getId(max);
      }
    }
    contact.getId(max);
    before.add(contact);
    before.add(contact);
    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
  }

}
