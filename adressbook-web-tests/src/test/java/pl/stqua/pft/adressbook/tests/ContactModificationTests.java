package pl.stqua.pft.adressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pl.stqua.pft.adressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification() {
    app.getContactHelpers().goToHomePage();
    int before = app.getContactHelpers().getContactCount();
    if (!app.getContactHelpers().isThereAContact()) {
      app.getContactHelpers().createContact(new ContactData("Test 1", "Test 2", "Test 3", "000000000", "tests@tests.com", "test1"), true);
    }
    app.getContactHelpers().selectContact(before -1);
    app.getContactHelpers().initContactModification();
    app.getContactHelpers().fillContactForm(new ContactData("Test 1", "Test 2", "Test 3", "000000000", "tests@tests.com", null), false);
    app.getContactHelpers().submitContactModification();
    int after = app.getContactHelpers().getContactCount();
    Assert.assertEquals(after, before);
  }
}
