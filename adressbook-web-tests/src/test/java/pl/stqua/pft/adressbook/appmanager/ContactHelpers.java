package pl.stqua.pft.adressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import pl.stqua.pft.adressbook.model.ContactData;
import pl.stqua.pft.adressbook.model.Contacts;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ContactHelpers extends Helperbase {

  public ContactHelpers(WebDriver wd) {
    super(wd);
  }

  public void returnToHomePage() {
    clickOn(By.linkText("home page"));
  }

  public void submitContactCreation() {
    clickOn(By.xpath("(//input[@name='submit'])[2]"));
  }

  public void fillContactForm(ContactData contactData, boolean creation) {
    typeOn(By.name("firstname"), contactData.getFirstname());
    typeOn(By.name("lastname"), contactData.getLastname());
    typeOn(By.name("address"), contactData.getAdress());
    typeOn(By.name("home"), contactData.getHomePhone());
    typeOn(By.name("email"), contactData.getEmail());

    if (creation) {
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }

  }

  public void selectContactById(int id) {
    wd.findElement(By.cssSelector("input[value= '" + id + "']")).click();
    }

  public void editContactByFirstName(String firstName) {
    wd.findElement(By.xpath("//td[contains(text(), '" + firstName + "')] /../td[8]/a")).click();
  }


  public void submitContactModification() {
    click(By.name("update"));
  }

  public void deleteSelectedContact() {
    click(By.xpath("(//input[@value='Delete'])"));
  }

  public void confirmDeleteSelectedContact() {
    click(By.name("OK"));
  }

  public void homePage() {
    click(By.linkText("home"));
  }

  public void create(ContactData contact, boolean b) {
    fillContactForm(contact, b);
    submitContactCreation();
    contactCache = null;
    returnToHomePage();
  }


  public void modify(ContactData contact) {
    editContactByFirstName(contact.getFirstname());
    fillContactForm(contact, false);
    contactCache = null;
    submitContactModification();
  }

  public void delete(ContactData contact) {
    selectContactById(contact.getId());
    deleteSelectedContact();
    contactCache = null;
    confirmDeleteSelectedContact();
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public int count() {
    return wd.findElements(By.name("selected[]")).size();
  }

  private Contacts contactCache = null;

  public Contacts all() {
    if (contactCache != null) {
      return new Contacts(contactCache);
    }
    contactCache = new Contacts();
    List<WebElement> elements = wd.findElements(By.xpath("/html/body/div[1]/div[4]/form[2]/table/tbody/tr[2]"));
    for (WebElement element : elements) {
      String name = element.getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      contactCache.add(new ContactData().withId(id).withFirstname(name).withLastname(name));
    }
    return new Contacts(contactCache);
  }

  public Set<ContactData> list() {
    Set<ContactData> contacts = new HashSet<ContactData>();
    List<WebElement> rows = wd.findElements(By.name("entry"));
    for (WebElement row : rows) {
      List<WebElement> cells = row.findElements(By.tagName("td"));
      int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value"));
      String lastname = cells.get(1).getText();
      String firstname = cells.get(2).getText();
      String allPhones = cells.get(5).getText();
      String allHomeAdress = cells.get(3).getText();
      String allEmailAdress = cells.get(4).getText();
      contacts.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname)
              .withAllPhones(allPhones).withAllHomeAdress(allHomeAdress).withAllEmailAdress(allEmailAdress));
    }
    return contacts;
  }

  public ContactData infoFromEditForm(ContactData contact) {
    initContactModificationById(contact.getId());
    String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastname = wd.findElement((By.name("lastname"))).getAttribute("value");
    String home = wd.findElement(By.name("home")).getAttribute("value");
    String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
    String work = wd.findElement(By.name("work")).getAttribute("value");
    String homeAdress = wd.findElement(By.name("address")).getAttribute("value");
    String emailAdress = wd.findElement(By.name("email")).getAttribute("value");

    wd.navigate().back();
    return new ContactData().withId(contact.getId()).withFirstname(firstname).withLastname(lastname)
            .withHome(home).withMobilePhone(mobile).withWorkPhone(work).withAllHomeAdress(homeAdress).withAllEmailAdress(emailAdress);
  }

  private void initContactModificationById(int id) {
    WebElement checbox = wd.findElement(By.cssSelector(String.format("input[value='%s']", id)));
    WebElement row = checbox.findElement(By.xpath("./../.."));
    List<WebElement> cells = row.findElements(By.tagName("td"));
    cells.get(7).findElement(By.tagName("a")).click();
  }

  public ContactData infoFromDetailsForm(ContactData contact) {
    initContactModificationByDetails(contact.getId());
    String firstname = wd.findElement(By.xpath("//html/body/div/div[4]/b")).getAttribute("value");
    String lastname = wd.findElement((By.xpath("/html/body/div/div[4]/b"))).getAttribute("value");
    String home = wd.findElement(By.xpath("/html/body/div/div[4]/br[3]")).getAttribute("value");
    String mobile = wd.findElement(By.xpath("/html/body/div/div[4]/br[4]")).getAttribute("value");
    String work = wd.findElement(By.xpath("/html/body/div/div[4]/br[5]")).getAttribute("value");
    String homeAdress = wd.findElement(By.xpath("/html/body/div/div[4]/br[1]")).getAttribute("value");
    String emailAdress = wd.findElement(By.xpath("/html/body/div/div[4]/a")).getAttribute("value");

    wd.navigate().back();
    return new ContactData().withId(contact.getId()).withFirstname(firstname).withLastname(lastname)
            .withHome(home).withMobilePhone(mobile).withWorkPhone(work).withAllHomeAdress(homeAdress).withAllEmailAdress(emailAdress);
  }

  private void initContactModificationByDetails(int id) {
    WebElement checbox = wd.findElement(By.cssSelector(String.format("input[value='%s']", id)));
    WebElement row = checbox.findElement(By.xpath("./../.."));
    List<WebElement> cells = row.findElements(By.tagName("td"));
    cells.get(6).findElement(By.tagName("a")).click();
  }
}