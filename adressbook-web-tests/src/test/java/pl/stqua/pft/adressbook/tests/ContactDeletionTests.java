package pl.stqua.pft.adressbook.tests;

import org.testng.annotations.Test;
import pl.stqua.pft.adressbook.model.ContactData;

public class ContactDeletionTests extends TestBase {

  @Test
  public void testContactModification() {
    app.getNavitagionHelper().goToHomePage();
    if (!app.getContactHelpers().isThereAContact()) {
      app.getContactHelpers().createContact(new ContactData("Test 1", "Test 2", "Test 3", "000000000", "tests@tests.com", "test1"), true);
    }
    app.getContactHelpers().selectContact();
    app.getContactHelpers().deleteSelectedContact();
    app.getContactHelpers().confirmDeleteSelectedContact();
  }
}
