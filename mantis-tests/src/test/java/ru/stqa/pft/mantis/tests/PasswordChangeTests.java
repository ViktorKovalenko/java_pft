package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.appmanager.HttpSession;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.UserData;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class PasswordChangeTests extends TestBase {

    @BeforeMethod
    public void startMailServer() {
        app.mail().start();
    }


    @Test
    public void testPasswordChange() throws IOException, MessagingException {
        String username = "administrator";
        String password = "root";
        String newpasswd = "newpass";
        app.goTo().loginPage();
        app.user().login(username, password);
        app.goTo().manageUsersPage();

        UserData userChangedPass = app.user().getUserFromDb();
        app.goTo().UserPage(userChangedPass.getId());
        app.user().resetPassword();
        List<MailMessage> mailMessages = app.mail().waitForMail(2, 10000);
        String confirmationLink = findConfirmationLink(mailMessages, userChangedPass.getEmail());

        app.registration().finish(confirmationLink, newpasswd);

        UserData users = app.user().getUserFromDbById(userChangedPass.getId());

        HttpSession session = app.newSession();
        assertTrue(session.login(userChangedPass.getUsername(), newpasswd));
        assertTrue(session.isLoggedInAs(users.getUsername()));
    }






    private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }


    @AfterMethod(alwaysRun = true)

    public void stopMailServer() {
        app.mail().stop();
    }
}
