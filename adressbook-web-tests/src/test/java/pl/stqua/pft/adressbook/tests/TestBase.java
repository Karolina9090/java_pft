package pl.stqua.pft.adressbook.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pl.stqua.pft.adressbook.appmanager.ApplicationManager;


public class TestBase {

  protected final ApplicationManager app = new ApplicationManager();

  @BeforeMethod(alwaysRun = true)
  public void setUp() throws Exception {
    app.init();
  }

  @AfterMethod(alwaysRun = true)
  public void tearDown() throws Exception {
    app.stop();
  }

}
