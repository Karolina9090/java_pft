package pl.stqua.pft.adressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pl.stqua.pft.adressbook.model.ContactData;
import pl.stqua.pft.adressbook.model.GroupData;


import java.util.HashSet;
import java.util.List;

public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification() {
    app.getContactHelpers().goToHomePage();
    if (!app.getContactHelpers().isThereAContact()) {
      app.getContactHelpers().createContact(new ContactData("Test 1", "Test 2", "Test 3", "000000000", "tests@tests.com", "test1"), true);
    }
    List<ContactData> before = app.getContactHelpers().getContactList();
    app.getContactHelpers().selectContact(before.size() -1);
    app.getContactHelpers().initContactModification();
    ContactData contact = new ContactData(before.get(before.size() -1).getId(),"Test 1", "Test 2", "Test 3", "000000000", "tests@tests.com", "test1");
    app.getContactHelpers().fillContactForm(contact, false);
    app.getContactHelpers().submitContactModification();
    List<ContactData> after = app.getContactHelpers().getContactList();
    Assert.assertEquals(after.size(), before.size());

    before.remove(before.size() -1);
    before.add(contact);
    Assert.assertEquals (new HashSet<Object>(before), new HashSet<Object>(after));
  }
}


