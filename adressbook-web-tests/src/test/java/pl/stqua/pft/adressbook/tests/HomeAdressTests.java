package pl.stqua.pft.adressbook.tests;

import org.testng.annotations.Test;
import pl.stqua.pft.adressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class HomeAdressTests extends TestBase {

  @Test
  public void HomeAdress() {
    app.goTo().homePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

    assertThat(contact.getAllHomeAdress(), equalTo(mergeAdress(contactInfoFromEditForm)));
  }

  private String mergeAdress(ContactData contact) {
    return Arrays.asList(contact.getHomeAdress())
            .stream().filter((s) -> !s.equals(""))
            .map(HomeAdressTests::cleaned)
            .collect(Collectors.joining("\n"));
  }

  public static String cleaned(String homeAdress) {
    return homeAdress.replaceAll("\\s", "").replaceAll("[-()]", "");
  }

  @Test
  public void HomeAdressOnDetailsContactPage() {
    app.goTo().homePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromDetailsForm = app.contact().infoFromEditForm(contact);

    assertThat(contact.getAllHomeAdress(), equalTo(mergeAdress(contactInfoFromDetailsForm)));
  }

}
