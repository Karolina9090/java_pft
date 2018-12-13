package pl.stqua.pft.adressbook.tests;

import org.testng.annotations.Test;
import pl.stqua.pft.adressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class EmailAdressTests extends TestBase {

  @Test

public void EmailAdress() {
    app.goTo().homePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

    assertThat(contact.getAllEmailAdress(), equalTo(mergeEmailAdress(contactInfoFromEditForm)));
  }

  private String mergeEmailAdress(ContactData contact) {
    return Arrays.asList(contact.getEmailAdress())
            .stream().filter((s) -> ! s.equals(""))
            .map(EmailAdressTests::cleaned)
            .collect(Collectors.joining("\n"));
  }

  public static String cleaned(String emailAdress) {
    return emailAdress.replaceAll("\\s", "").replaceAll("[-()]", "");
  }

}
