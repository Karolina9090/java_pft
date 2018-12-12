package pl.stqua.pft.adressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pl.stqua.pft.adressbook.model.ContactData;
import java.util.Set;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    app.goTo().goToAddNewContact();
    Set<ContactData> before = app.contact().all();
    ContactData contact = new ContactData().withFirstname("test1").withLastname("test2").withAdress("000000000").withHome("test3").withEmail("test@test");
    app.contact().create(contact, false);
    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size() + 1);

    contact.withId(after.stream().mapToInt((g) -> g.getId(0)).max().getAsInt());
    before.add(contact);
    Assert.assertEquals(before, after);
  }

}
