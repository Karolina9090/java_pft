package pl.stqua.pft.adressbook.tests;

import org.testng.annotations.Test;
import pl.stqua.pft.adressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    app.getNavitagionHelper().goToAddNewContact();
    app.getContactHelpers().fillContactForm(new ContactData("Test 1", "Test 2", "Test 3", "000000000", "tests@tests.com", "test1"), "(//option[@value='1'])[3]");
    app.getContactHelpers().submitContactCreation();
    app.getContactHelpers().returnToHomePage();
  }


}
