package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactCreationTests extends TestBase {


    @Test
    public void testContactCreation() throws Exception {
        app.goTo().homepage();
        Contacts before = app.contact().all();
        File photo = new File("src/test/resources/img.png");
        ContactData contact = new ContactData()
                .withName("Viktor").withSurname("Kovalenko").withPhone("+380993020583").withEmail("vsutogan@gmail.com").withGroup("test1").withPhoto(photo);
        app.contact().create(contact);
        app.contact().returnToHomePage();
        assertThat(app.contact().count(), equalTo(before.size() + 1));
        Contacts after = app.contact().all();
        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    }

    @Test (enabled = false) //проверка на наличие файла
    public void testCurentDir(){
        File curentDir = new File("");
        System.out.println(curentDir.getAbsolutePath());
        File photo = new File("src/test/resources/img.png");
        System.out.println(photo.getAbsolutePath());
        System.out.println(photo.exists());


    }


}
