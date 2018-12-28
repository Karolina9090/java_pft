package pl.stqua.pft.adressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pl.stqua.pft.adressbook.model.ContactData;
import pl.stqua.pft.adressbook.model.Contacts;
import pl.stqua.pft.adressbook.model.GroupData;

import java.io.*;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

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
  public Iterator<Object[]> validContactsFromXML() throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.xml")))) {
      String xml = "";
      String line = reader.readLine();
      while (line != null) {
        xml += line;
        line = reader.readLine();
      }
      XStream xstream = new XStream();
      xstream.processAnnotations(GroupData.class);
      List<ContactData> contacts = (List<ContactData>) xstream.fromXML(xml);
      return contacts.stream().map((c) -> new Object[]{c}).collect(Collectors.toList()).iterator();
    }
  }

  @DataProvider
  public Iterator<Object[]> validContactsFromJson() throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.json")))) {
      String json = "";
      String line = reader.readLine();
      while (line != null) {
        json += line;
        line = reader.readLine();
      }
      Gson gson = new Gson();
      List<ContactData> contacts = gson.fromJson(json, new TypeToken<List<ContactData>>() {
      }.getType());
      return contacts.stream().map((c) -> new Object[]{c}).collect(Collectors.toList()).iterator();
    }
  }


  @Test(dataProvider = "validContactsFromJson")
  public void testBadContactCreation(ContactData contact) {
    app.contact().create(contact, false);
    app.goTo().goToAddNewContact();
    Contacts before = app.contact().all();
    assertThat(app.contact().count(), equalTo(before.size()));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before));
  }

  @Test(enabled = false)
  public void testContactCreation2() {
    app.goTo().goToAddNewContact();
    File photo = new File("src/test/resources/bombka2.jpg");
    app.contact().fillContactForm(
            new ContactData().withFirstname("test1").withLastname("test2").withAdress("000000000").withHome("test3").withEmail("test@test").withPhoto(photo), true);
    app.contact().submitContactCreation();
    app.contact().returnToHomePage();
  }

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().contacts().size() == 0) {
      app.goTo().goToAddNewContact();
      app.contact().create(new ContactData().withFirstname("test1").withLastname("test2").withAdress("000000000").withHome("test3").withEmail("test@test"), true);
    }
  }

  @Test
  public void testContactCreation3() {
    app.goTo().goToAddNewContact();
    Contacts before = app.db().contacts();
    File photo = new File("src/test/resources/bombka2.jpg");
    ContactData contact = new ContactData().withFirstname("test1").withLastname("test2").withAdress("000000000").withHome("test3").withEmail("test@test").withPhoto(photo);
    app.contact().create(contact, false);
    assertThat(app.contact().count(), equalTo(before.size() + 1));
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }
}