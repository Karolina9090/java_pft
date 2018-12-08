package pl.stqua.pft.adressbook.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import pl.stqua.pft.adressbook.appmanager.ApplicationManager;


public class TestBase {

  protected static final ApplicationManager app = new ApplicationManager(BrowserType.GOOGLECHROME);

  @BeforeSuite
  public void setUp() throws Exception {
    app.init();
  }

  @AfterSuite
  public void tearDown() {
    app.stop();
  }

}
