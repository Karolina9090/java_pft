package pl.stqa.pft.mantis.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.stqa.pft.mantis.appmanager.HttpSession;
import pl.stqa.pft.mantis.model.MailMessage;
import ru.lanwen.verbalregex.VerbalExpression;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ChangePsswordTests extends TestBase {


  public void startMailServer() {
    app.mail().start();
  }

  @BeforeMethod
  public void ensurePreconditions()  throws IOException {
    HttpSession session = app.newSession();
    assertTrue(session.login("administrator", "root"));
    assertTrue(session.isLoggedInAs("administrator"));
  }

  @Test
  public void testChangePasswordTests(String passwords, String user, String email)   throws IOException, MessagingException, InterruptedException {
    app.james().changeUserPassword(passwords);
    app.changingPassword().start(user, email);
    List<MailMessage> mailMessages = app.james().waitForMail(user, passwords, 60000);
    String confirmationLink = findConfirmationLink(mailMessages, email);
    app.changingPassword().finish(confirmationLink, passwords);
    assertTrue(app.newSession().login(user, passwords));
  }

  private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
    MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    return regex.getText(mailMessage.text);
  }

  public void stopMailServer() {
    app.mail().stop();
  }
}

