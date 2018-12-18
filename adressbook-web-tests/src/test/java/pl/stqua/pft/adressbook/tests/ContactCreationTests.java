package pl.stqua.pft.adressbook.tests;

import org.testng.annotations.Test;
import pl.stqua.pft.adressbook.model.ContactData;
import pl.stqua.pft.adressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

  @Test(enabled = false)
  public void testContactCreation() {
    app.goTo().goToAddNewContact();
    Contacts before = app.contact().all();
    File photo = new File("src/test/resources/bombka2.jpg");
    ContactData contact = new ContactData().withFirstname("test1").withLastname("test2").withAdress("000000000").withHome("test3").withEmail("test@test").withPhoto(photo);
    app.contact().create(contact, false);
    assertThat(app.contact().count(), equalTo(before.size() + 1));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }

  @Test(enabled = false)
  public void testBadContactCreation() {
    app.goTo().goToAddNewContact();
    Contacts before = app.contact().all();
    ContactData contact = new ContactData().withFirstname("test1'").withLastname("test2'").withAdress("000000000").withHome("test3").withEmail("test@test");
    app.contact().create(contact, false);
    assertThat(app.contact().count(), equalTo(before.size()));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before));
  }

  @Test
  public void testContactCreation2() {
    app.goTo().goToAddNewContact();
    File photo = new File("src/test/resources/bombka2.jpg");
    app.contact().fillContactForm(
            new ContactData().withFirstname("test1").withLastname("test2").withAdress("000000000").withHome("test3").withEmail("test@test").withPhoto(photo), true);
    app.contact().submitContactCreation();
    app.contact().returnToHomePage();
  }

}
