package pl.stqa.pft.mantis.tests;

import org.testng.annotations.Test;
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

  @Test

  public void testChanePasswordTests()   throws IOException, MessagingException, InterruptedException {
    long now = System.currentTimeMillis();
    String user  = String.format("user%s" + now);
    String password = "password";
    String email = String.format("user1%s@localhost.localdomain", now);
    app.james().changeUserPassword(password);
    app.changingPassword().start(user, email);
    List<MailMessage> mailMessages = app.james().waitForMail(user, password, 60000);
    String confirmationLink = findConfirmationLink(mailMessages, email);
    app.changingPassword().finish(confirmationLink, password);
    assertTrue(app.newSession().login(user, password));
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

