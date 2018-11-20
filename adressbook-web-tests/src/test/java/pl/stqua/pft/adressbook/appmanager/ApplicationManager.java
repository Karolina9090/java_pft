package pl.stqua.pft.adressbook.appmanager;

import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;


public class ApplicationManager {
  FirefoxDriver wd;

  private SessionHelper sessionHelper;
  private NavitagionHelper navitagionHelper;
  private GroupHelpers groupHelpers;

  public void init() {
    wd = new FirefoxDriver();
    wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    wd.get("http://localhost/addressbook/group.php");
    groupHelpers = new GroupHelpers(wd);
    navitagionHelper = new NavitagionHelper(wd);
    sessionHelper = new SessionHelper(wd);
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

}
