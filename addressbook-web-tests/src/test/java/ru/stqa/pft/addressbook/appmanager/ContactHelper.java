package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactHelper extends HelperBase{

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void submitContactCreation() {
      click(By.name("submit"));
    }

    public void fillContactForm(ContactData contactData) {
      type(By.name("firstname"), contactData.getName());
      type(By.name("lastname"), contactData.getSurname());
      type(By.name("home"), contactData.getPhone());
      type(By.name("email"), contactData.getEmail());


    }

    public void initContactCreation() {
      click(By.linkText("add new"));
    }

    public void gotoHomepage() {
        click(By.linkText("home"));
    }

    public void selectContact() {
        click(By.id("3")) ;
    }

    public void initContactModification() {
        click(By.xpath("//tr[3]/td/input"));
    }

    public void submitContactModification() {
        click(By.name("update"));
    }

    public void submitContactDeletion() {
        wd.switchTo().alert().accept();
    }

    public void initContactDeletion() {
        click(By.xpath("//input[@value = 'Delete']"));
    }
}
