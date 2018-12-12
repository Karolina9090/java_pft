package pl.stqua.pft.adressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.stqua.pft.adressbook.model.ContactData;
import java.util.Set;

public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.contact().homePage();
    if (app.group().all().size() == 0) {
      app.contact().create(new ContactData().withFirstname("Test2").withLastname("Test 3").withAdress("000000000").withEmail("tests@tests.com").withHome("test1"), true);
    }
  }

  @Test(enabled = false)
  public void testContactModification() {
    Set<ContactData> before = app.contact().all();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData()
            .withId(modifiedContact.getId(0)).withFirstname("Test 2").withLastname("Test 3").withAdress("000000000").withEmail("tests@tests.com").withHome("test1");
    app.contact().modify(contact);
    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size());

    before.remove(modifiedContact);
    before.add(contact);
    Assert.assertEquals(before, after);
  }

}


