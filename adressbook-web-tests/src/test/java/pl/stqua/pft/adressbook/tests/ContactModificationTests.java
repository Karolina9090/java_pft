package pl.stqua.pft.adressbook.tests;

import org.testng.annotations.Test;
import pl.stqua.pft.adressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification(){
    app.getContactHelpers().selectContact();
    app.getContactHelpers().initContactModification();
    app.getContactHelpers().fillContactForm(new ContactData("Test 1", "Test 2", "Test 3", "000000000", "tests@tests.com", "test1"), "(//option[@value='1'])[3]");
    app.getContactHelpers().submitContactModification();
  }
}