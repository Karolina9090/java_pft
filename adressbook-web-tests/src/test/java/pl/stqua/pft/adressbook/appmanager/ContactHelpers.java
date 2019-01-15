package pl.stqua.pft.adressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import pl.stqua.pft.adressbook.model.ContactData;
import pl.stqua.pft.adressbook.model.Contacts;
import pl.stqua.pft.adressbook.model.GroupData;

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
    attach(By.name("photo"), contactData.getPhoto());

    if (creation) {
      if (contactData.getGroups().size() > 0) {
        Assert.assertTrue(contactData.getGroups().size() == 1);
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroups().iterator().next().getName());
      }
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }

  public void selectContactById(int id) {
    wd.findElement(By.cssSelector("input[value= '" + id + "']")).click();
    }

  public void editContactById(int id) {
    wd.findElement(By.xpath("//input[@id= '" + id + "']/../../td[8]/a")).click();
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
    editContactById(contact.getId());
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

  public void deleteContact(GroupData contact) {
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

  public ContactData infoFromEditForm(ContactData contact) {
    initContactModificationById(contact.getId());
    String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastname = wd.findElement((By.name("lastname"))).getAttribute("value");
    String home = wd.findElement(By.name("home")).getAttribute("value");
    String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
    String work = wd.findElement(By.name("work")).getAttribute("value");
    String homeAdress = wd.findElement(By.name("address")).getAttribute("value");
    String emailAdress = wd.findElement(By.name("email")).getAttribute("value");
    String emailAdress2 = wd.findElement(By.name("email2")).getAttribute("value");
    String emailAdress3 = wd.findElement(By.name("email3")).getAttribute("value");


    wd.navigate().back();
    return new ContactData().withId(contact.getId()).withFirstname(firstname).withLastname(lastname)
            .withHome(home).withMobilePhone(mobile).withWorkPhone(work).withAllHomeAdress(homeAdress).withAllEmailAdress(emailAdress).withEmailAdress(emailAdress).withEmailAdress2(emailAdress2).withEmailAdress3(emailAdress3);
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