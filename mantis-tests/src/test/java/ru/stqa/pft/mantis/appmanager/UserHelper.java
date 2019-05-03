package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import ru.stqa.pft.mantis.model.UserData;
import ru.stqa.pft.mantis.model.Users;


public class UserHelper extends HelperBase{


    public UserHelper(ApplicationManager app) {

        super(app);
    }

    public void login(String username, String password) {
        type(By.name("username"), username);
        type(By.name("password"), password);
       click(By.xpath("//input[@value='Войти']"));
    }


    public void resetPassword() {
            click(By.cssSelector("input[value=\"Reset Password\"]"));
    }

    public UserData getUserFromDb() {
        Users users = app.db().users();
        return users.stream().filter((u) ->u.getId()  != 1).iterator().next();
    }


}
