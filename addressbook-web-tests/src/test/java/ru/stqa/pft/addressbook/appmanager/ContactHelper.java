package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void submitContactCreation() {
        click(By.name("submit"));
    }

    public void fillContactForm(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getName());
        type(By.name("lastname"), contactData.getSurname());
        type(By.name("home"), contactData.getPhone());
        type(By.name("email"), contactData.getEmail());
        type(By.name("home"), contactData.getHomePhone());
        type(By.name("mobile"), contactData.getMobilePhone());
        type(By.name("work"), contactData.getWorkPhone());
        type(By.name("email2"), contactData.getEmail2());
        type(By.name("email3"), contactData.getEmail3());
        type(By.name("address"), contactData.getAddress());
        attach(By.name("photo"), contactData.getPhoto() );





        if (creation) {
            if (contactData.getGroups().size() > 0)
                Assert.assertTrue(contactData.getGroups().size() == 1);
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroups().iterator().next().getName());

        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }

    }


    public void initContactCreation() {
        click(By.linkText("add new"));
    }

    public void selectContact(int index) {
       // wd.findElements(By.name("selected[]")).get(index).click();
        click(By.name("selected[]")); //случайный выбор
    }

    public void selectContactById(int id) {
        wd.findElement(By.cssSelector("input[value = '" + id + "']")).click();

        /* варианты поиска локаторов:
        WebElement checkbox = wd.findElement(By.cssSelector(String.format("input [value = '%s']", id)));
        WebElement row = checkbox.findElement(By.xpath(".//../.."));
        List<WebElement> cells = row.findElements(By.tagName("td"));
        cells.get(7).findElement(By.tagName("a")).click();*/
       // wd.findElement(By.xpath(String.format("//input[@value = '%s']/../../td[8]/a", id))).click();
        //wd.findElement(By.xpath(String.format("//tr[.//input[value='%s']]/td[8]/a",id))).click();
        //wd.findElement(By.cssSelector(String.format("a [href ='edit.php?id=%s']",id))).click();
        //click(By.name("selected[]")); //случайный выбор

    }


    public void initContactModificationById(int id) {
        wd.findElement(By.xpath("//a[@href='edit.php?id=" + id + "']")).click();
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

    public void returnToHomePage() {
        click(By.linkText("home page"));
    }


    public void create(ContactData contact) {
        initContactCreation();
        fillContactForm(contact, true);
        submitContactCreation();
        contactCashe = null;
    }

    public void modify(ContactData contact) {
        initContactModificationById(contact.getId());
        fillContactForm(contact, false);
        submitContactModification();
        contactCashe = null;
        returnToHomePage();
    }

    public void delete(ContactData contact) {
        selectContactById(contact.getId());
        initContactDeletion();
        submitContactDeletion();
        contactCashe = null;
    }

    public ContactData infoFromEditForm(ContactData contact) {
        initContactModificationById(contact.getId());
        String name = wd.findElement(By.name("firstname")).getAttribute("value");
        String surname = wd.findElement(By.name("lastname")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        String email = wd.findElement(By.name("email")).getAttribute("value");
        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = wd.findElement(By.name("email3")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getText();
        wd.navigate().back();
        return new ContactData().withId(contact.getId()).withName(name).withSurname(surname).withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work)
                .withEmail(email).withEmail2(email2).withEmail3(email3).withAddress(address);
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }


    public int count() {
        return wd.findElements(By.name("selected[]")).size();
    }


    private Contacts contactCashe = null;

    public Contacts all() {
        if (contactCashe != null) {
            return new Contacts(contactCashe);
        }
        contactCashe = new Contacts();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element : elements) {
            String name = element.findElement(By.xpath(".//td[3]")).getText();
            String surname = element.findElement(By.xpath(".//td[2]")).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            ContactData contact = new ContactData().withId(id).withName(name).withSurname(surname);
            contactCashe.add(contact);
        }
        return new Contacts(contactCashe);
    }

    public Set<ContactData> allPhonz() {
        Set<ContactData> contacts = new HashSet<ContactData>();
        List<WebElement> rows = wd.findElements(By.name("entry"));
        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value"));
            String name = cells.get(2).getText();
            String surname = cells.get(1).getText();
            String allPhones = cells.get(5).getText();
            contacts.add(new ContactData().withId(id).withName(name).withSurname(surname)
                    .withAllPhones(allPhones));
        }
        return contacts;
    }


    public Set<ContactData> allEmails() {
        Set<ContactData> contacts = new HashSet<ContactData>();
        List<WebElement> rows = wd.findElements(By.name("entry"));
        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value"));
            String name = cells.get(2).getText();
            String surname = cells.get(1).getText();
            String allEmails = cells.get(4).getText();
            contacts.add(new ContactData().withId(id).withName(name).withSurname(surname)
                    .withAllEmails(allEmails));
        }
        return contacts;
    }
    public Set<ContactData> addressFromHomePage() {
        Set<ContactData> contacts = new HashSet<ContactData>();
        List<WebElement> rows = wd.findElements(By.name("entry"));
        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value"));
            String name = cells.get(2).getText();
            String surname = cells.get(1).getText();
            String address = cells.get(3).getText();
            contacts.add(new ContactData().withId(id).withName(name).withSurname(surname)
                    .withAddressFromHomePage(address));
        }
        return contacts;
    }
    public void deleteContactFromGroup(ContactData contact) {
        selectContactById(contact.getId());
        wd.findElement(By.name("remove")).click();
    }

    public void addToGroup(ContactData contact, GroupData group) {
        selectContactById(contact.getId());
        new Select(wd.findElement(By.name("to_group"))).selectByValue(Integer.toString(group.getId()));
        wd.findElement(By.name("add")).click();
    }
}

