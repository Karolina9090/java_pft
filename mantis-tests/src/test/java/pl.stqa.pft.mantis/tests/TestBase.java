package pl.stqa.pft.mantis.tests;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.BrowserType;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import pl.stqa.pft.mantis.appmanager.ApplicationManager;

import java.io.File;
import java.io.IOException;

public class TestBase {
  protected WebDriver wd;


  protected static final ApplicationManager app
          = new ApplicationManager(System.getProperty("browser", BrowserType.FIREFOX));

  @BeforeSuite
  public void setUp() throws Exception {
    app.init();
    app.ftp().upload(new File("src/test/resources/config_inc.php"), "config_inc.php", "config_inc.php.bak");
  }

  @AfterSuite(alwaysRun = true)
  public void tearDown() throws IOException {
    app.ftp().restore("config_inc.php.bak", "config_inc.php");
    app.stop();
  }
}
