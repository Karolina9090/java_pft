package pl.stqua.pft.adressbook.tests;

import org.testng.annotations.Test;

public class ContactDeletionTests extends TestBase {

  @Test
  public void testContactModification() {
    app.getNavitagionHelper().goToHomePage();

    app.getContactHelpers().selectContact();
    app.getContactHelpers().deleteSelectedContact();
    app.getContactHelpers().confirmDeleteSelectedContact();
  }
}
