package pl.stqua.pft.adressbook.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pl.stqua.pft.adressbook.model.ContactData;
import pl.stqua.pft.adressbook.model.Contacts;
import pl.stqua.pft.adressbook.model.GroupData;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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


  @DataProvider
  public Iterator<Object[]> validContacts() {
    List<Object[]> list = new ArrayList<Object[]>();
    list.add(new Object[]{new ContactData().withFirstname("test1").withLastname("test2").withAdress("0000000001").withHome("test3").withEmail("test@test1")});
    list.add(new Object[]{new ContactData().withFirstname("test2").withLastname("test3").withAdress("0000000002").withHome("test4").withEmail("test@test2")});
    list.add(new Object[]{new ContactData().withFirstname("test3").withLastname("test4").withAdress("0000000003").withHome("test5").withEmail("test@test3")});
    return list.iterator();
  }

  @Test(dataProvider = "validContacts")
  public void testBadContactCreation(ContactData contact) {
    app.contact().create(contact, false);
    app.goTo().goToAddNewContact();
    Contacts before = app.contact().all();
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
