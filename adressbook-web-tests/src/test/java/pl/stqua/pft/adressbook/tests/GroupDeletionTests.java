package pl.stqua.pft.adressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.stqua.pft.adressbook.model.ContactData;
import pl.stqua.pft.adressbook.model.Contacts;
import pl.stqua.pft.adressbook.model.GroupData;
import pl.stqua.pft.adressbook.model.Groups;


import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().groups().size() == 28) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test1"));
    }
    if (app.db().contacts().size() == 0) {
      app.contact().homePage();
      app.contact().create(new ContactData().withFirstname("Test2").withLastname("Test 3").withAdress("000000000").withEmail("tests@tests.com").withHome("test1"), true);
    }
  }


  @Test
  public void testGroupDeletion2() {
    Groups before = app.group().all();
    GroupData deletedGroup = before.iterator().next();
    app.group().delete(deletedGroup);
    assertThat(app.contact().count(), equalTo(before.size() - 1));
    Groups after = app.group().all();
    assertThat(after, equalTo(before.without(deletedGroup)));
    verifyGroupListDB();
  }

  @Test
  public void testGroupDeletion() {
    Groups before = app.db().groups();
    GroupData deletedGroup = before.iterator().next();
    app.group().delete(deletedGroup);
    assertThat(app.contact().count(), equalTo(before.size() - 1));
    Groups after = app.db().groups();
    assertThat(after, equalTo(before.without(deletedGroup)));
    verifyGroupListUI();
  }

  @Test
  public void testContactDeletionFromGroup() {
    Contacts contacts = app.db().contacts();
    GroupData deletedGroup = new GroupData().withName("test2")
            .inDeletedContacts(contacts.iterator().next());
    app.group().delete(deletedGroup);
    app.group().submitGroupCreation();
    app.group().returnToGroupPage();
  }

  @Test
  public void testDeleteExistingContactToExistingGroup() {
    Contacts before = app.contact().all();
    ContactData addedContact = before.iterator().next();
    Groups groups = app.group().all();
    GroupData deletedContactFromGroup = groups.iterator().next();
    app.group().deleteContact(deletedContactFromGroup);
    assertThat(app.contact().count(), equalTo(before.size() - 1));
    Groups after = app.group().all();
    assertThat(after, equalTo(before.without(addedContact)));
    verifyGroupListDB();
  }


}