package pl.stqua.pft.adressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.stqua.pft.adressbook.model.ContactData;


import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.getContactHelpers().goToHomePage();
    if (!app.getContactHelpers().isThereAContact()) {
      app.getContactHelpers().createContact(new ContactData("Test 1", "Test 2", "Test 3", "000000000", "tests@tests.com", "test1"), true);
    }
  }

  @Test(enabled = false)
  public void testContactModification() {
    List<ContactData> before = app.getContactHelpers().getContactList();
    int index = before.size() - 1;
    ContactData contact = new ContactData(before.get(index).getId(0), "Test 1", "Test 2", "Test 3", "000000000", "tests@tests.com", "test1");
    app.getContactHelpers().modifyContact(index, contact);
    List<ContactData> after = app.getContactHelpers().getContactList();
    Assert.assertEquals(after.size(), before.size());

    before.remove(index);
    before.add(contact);
    Comparator<? super ContactData> byID = (g1, g2) -> Integer.compare(g1.getId(0), g2.getId(0));
    before.sort(byID);
    after.sort(byID);
    Assert.assertEquals(before, after);
  }

}


