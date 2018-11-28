package pl.stqua.pft.adressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import pl.stqua.pft.adressbook.model.ContactData;

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
    clickOn(By.name("middlename"));
    clickOn(By.name("middlename"));
    typeOn(By.name("lastname"), contactData.getLastname());
    typeOn(By.name("address"), contactData.getAdress());
    typeOn(By.name("home"), contactData.getHome());
    typeOn(By.name("email"), contactData.getEmail());

    if (creation) {
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }

  }


  public void selectContact() {
    click(By.name("selected[]"));
  }

  public void initContactModification() {
    click(By.xpath("//img[@alt='Edit']"));
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

  public void goToHomePage() { click(By.linkText("home")); }
}

