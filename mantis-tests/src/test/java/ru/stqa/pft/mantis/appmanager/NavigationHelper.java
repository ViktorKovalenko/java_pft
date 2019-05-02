package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends HelperBase {

    public NavigationHelper(ApplicationManager app) {
        super(app);
    }


    public void loginPage() {
        wd.get(app.getProperty("web.baseUrl") + "login_page.php");
    }



    public void manageUsersPage() {
        click(By.linkText("Manage"));
    }

    public void UserPage(int id) {
        click(By.cssSelector("a[href=\"manage_user_edit_page.php?user_id=" + id +"\"]"));
    }
}