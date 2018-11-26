package pl.stqua.pft.adressbook.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {
  WebDriver wd;

  private ContactHelpers contactHelpers;
  private SessionHelper sessionHelper;
  private NavitagionHelper navitagionHelper;
  private GroupHelpers groupHelpers;
  private String browser;

  public ApplicationManager (String browser) {
    this.browser = browser;
  }

  public void init() {
    if (browser.equals(BrowserType.FIREFOX)) {
      wd = new FirefoxDriver();
    } else if (browser.equals(BrowserType.FIREFOX_CHROME)) {
      wd = new ChromeDriver();
    } else if (browser.equals(BrowserType.IE)) {
      wd = new InternetExplorerDriver();
    }
    wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    wd.get("http://localhost/addressbook/group.php");
    groupHelpers = new GroupHelpers(wd);
    navitagionHelper = new NavitagionHelper(wd);
    sessionHelper = new SessionHelper(wd);
    contactHelpers = new ContactHelpers(wd);
    sessionHelper.login("admin", "secret");
  }

  public void stop() {
    wd.quit();
  }

  public GroupHelpers getGroupHelpers() {
    return groupHelpers;
  }

  public NavitagionHelper getNavitagionHelper() {
    return navitagionHelper;
  }


  public ContactHelpers getContactHelpers() {
    return contactHelpers;
  }
}
