package pl.stqua.pft.adressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.stqua.pft.adressbook.model.ContactData;

import java.util.Set;

public class ContactDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.contact().homePage();
    if (app.group().all().size() == 0) {
      app.contact().create(new ContactData().withFirstname("test1").withLastname("test2").withAdress("000000000").withHome("test3").withEmail("test@test"), true);
    }
  }

  @Test(enabled = false)
  public void testContactModification() {
    Set<ContactData> before = app.contact().all();
    ContactData deletedContact = before.iterator().next();
    app.contact().delete(deletedContact);
    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(deletedContact);
    Assert.assertEquals(before, after);
  }


}
