package pl.stqua.pft.adressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

public class NavitagionHelper extends Helperbase {

  private FirefoxDriver wd;

  public NavitagionHelper(FirefoxDriver wd) {
    super(wd);
  }

  public void goToGroupPage() {
    click(By.linkText("groups"));
  }

  public void goToAddNewContact() {
    click(By.linkText("add new"));
  }

  public void goToHomePage() {
      click(By.linkText("home"));
    }
}
