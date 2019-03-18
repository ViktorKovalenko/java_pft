package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.tests.TestBase;

public class ContactHelper extends TestBase {

    private final NavigationHelper navigationHelper;

    public ContactHelper(WebDriver wd) {
        navigationHelper = new NavigationHelper(wd);
    }

    public void submitContactCreation() {
        navigationHelper.click(By.name("submit"));
    }

    public void fillContactForm(ContactData contactData, boolean creation) {
        navigationHelper.type(By.name("firstname"), contactData.getName());
        navigationHelper.type(By.name("lastname"), contactData.getSurname());
        navigationHelper.type(By.name("home"), contactData.getPhone());
        navigationHelper.type(By.name("email"), contactData.getEmail());

        if (creation) {
            new Select(navigationHelper.wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());

        } else {
            Assert.assertFalse(navigationHelper.isElementPresent(By.name("new_group")));
        }

    }


    public void initContactCreation() {
        navigationHelper.click(By.linkText("add new"));
    }

    public void selectContact() {
        navigationHelper.click(By.name("selected[]"));
    }

    public void initContactModification() {
        navigationHelper.click(By.xpath("(//img[@alt='Edit'])[2]"));
    }

    public void submitContactModification() {
        navigationHelper.click(By.name("update"));
    }

    public void submitContactDeletion() {
        navigationHelper.wd.switchTo().alert().accept();
    }

    public void initContactDeletion() {
        navigationHelper.click(By.xpath("//input[@value = 'Delete']"));
    }

    public NavigationHelper getNavigationHelper() {
        return navigationHelper;
    }
}
