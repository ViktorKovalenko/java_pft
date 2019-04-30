package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTests extends TestBase{


    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().contacts().size()==0) {
            File photo = new File("src/test/resources/img.png");
            app.contact().create(new ContactData()
                    .withName("Viktor").withSurname("Kovalenko").withPhone("+380993020583").withEmail("vsutogan@gmail.com").withPhoto(photo));
            app.contact().returnToHomePage(); //if app.timeouts().implicitlyWait is 0
        }
    }
       @Test
       public void testContactModification(){
           app.goTo().homepage();
           Contacts before = app.db().contacts();
           ContactData modifiedContact = before.iterator().next();
           File photo = new File("src/test/resources/img.png");
           ContactData contact = new ContactData()
                   .withId(modifiedContact.getId()).withName("Vik").withSurname("Kov").withWorkPhone("123").withHomePhone("555").withMobilePhone("367").withEmail("vsutogan@gmail.com").withPhoto(photo);
           app.contact().modify(contact);
           assertThat(app.contact().count(), equalTo(before.size()));
           Contacts after = app.db().contacts();
           assertThat(after, CoreMatchers.equalTo(before.without(modifiedContact).withAdded(contact)));
           verifyContactListInUI();
       }

    public void verifyContactListInUI() {
    }

}
