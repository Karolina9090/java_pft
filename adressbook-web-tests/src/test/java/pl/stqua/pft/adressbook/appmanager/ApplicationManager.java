package pl.stqua.pft.adressbook.appmanager;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;

import java.io.File;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.io.FileReader;

public class ApplicationManager {
  private final Properties properties;
  WebDriver wd;

  private ContactHelpers contactHelpers;
  private SessionHelper sessionHelper;
  private NavitagionHelper navitagionHelper;
  private GroupHelpers groupHelpers;
  private String browser;

  public ApplicationManager(String browser) {
    this.browser = browser;
    properties = new Properties();
  }

  public void init() throws IOException {
    String target = System.getProperty("target", "local");
    properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));

    if (browser.equals(BrowserType.FIREFOX)) {
      wd = new FirefoxDriver();
    } else if (browser.equals(BrowserType.GOOGLECHROME)) {
      wd = new ChromeDriver();
    } else if (browser.equals(BrowserType.IE)) {
      wd = new InternetExplorerDriver();
    }
    wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    wd.get(properties.getProperty("web.baseUrl"));
    groupHelpers = new GroupHelpers(wd);
    navitagionHelper = new NavitagionHelper(wd);
    sessionHelper = new SessionHelper(wd);
    contactHelpers = new ContactHelpers(wd);
    sessionHelper.login(properties.getProperty("web.adminLogin"), properties.getProperty("web.adminPassword"));
  }

  public void stop() {
    wd.quit();
  }

  public GroupHelpers group() {
    return groupHelpers;
  }

  public NavitagionHelper goTo() {
    return navitagionHelper;
  }


  public ContactHelpers contact() {
    return contactHelpers;
  }
}
