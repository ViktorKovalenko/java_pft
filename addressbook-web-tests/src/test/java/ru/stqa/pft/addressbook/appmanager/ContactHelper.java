package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super (wd);
    }

    public void submitContactCreation() {
        click(By.name("submit"));
    }

    public void fillContactForm(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getName());
        type(By.name("lastname"), contactData.getSurname());
        type(By.name("home"), contactData.getPhone());
        type(By.name("email"), contactData.getEmail());

        if (creation) {
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());

        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }

    }


    public void initContactCreation() {
        click(By.linkText("add new"));
    }

    public void selectContact(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
        //click(By.name("selected[]")); //случайный выбор
    }

    public void initContactModification(int index) {
        wd.findElements(By.xpath("//img[@alt='Edit']")).get(index).click();
       // click(By.xpath("//img[@alt='Edit']")); // случайный выбор
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




    public void createContact(ContactData contact) {
        initContactCreation();
        fillContactForm(contact, true);
        submitContactCreation();
    }


    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

public void returnToHomePage(){
    click(By.linkText("home page"));
}

    public int getContactCount() {
        return wd.findElements(By.name("selected[]")).size();
    }

    public List<ContactData> getConactList() {
        {
            List<ContactData> contacts = new ArrayList<ContactData>();
            List<WebElement> elements = wd.findElements(By.name("entry"));
            for (WebElement element : elements){
                String name = element.getText();
                String surname = element.getText();
                String id = element.findElement(By.tagName("input")).getAttribute("value");
                ContactData contact = new ContactData(id, name, surname, null, null, null);
                contacts.add(contact);
            }
            return contacts;
        }
    }
}
