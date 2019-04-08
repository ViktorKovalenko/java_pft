package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTests extends TestBase{


    @BeforeMethod
    public void ensurePreconditions() {
        if (app.contact().all().size() == 0) {
            app.contact().create(new ContactData()
                    .withName("Viktor").withSurname("Kovalenko").withPhone("+380993020583").withEmail("vsutogan@gmail.com").withGroup("test1"));
            app.contact().returnToHomePage(); //if app.timeouts().implicitlyWait is 0
        }
    }
       @Test
       public void testContactModification(){
           app.goTo().homepage();
           Contacts before = app.contact().all();
           ContactData modifiedContact = before.iterator().next();
           ContactData contact = new ContactData()
                   .withId(modifiedContact.getId()).withName("Viktor").withSurname("Kovalenko").withPhone("+380993020583").withEmail("vsutogan@gmail.com").withGroup("test1");
           app.contact().modify(contact);
           assertThat(app.contact().count(), equalTo(before.size()));
           Contacts after = app.contact().all();
           assertThat(after, CoreMatchers.equalTo(before.without(modifiedContact).withAdded(contact)));
       }

   }
