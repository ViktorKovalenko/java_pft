package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

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
           Contacts after = app.contact().all();
           assertEquals(after.size(), before.size());


           assertThat(after, CoreMatchers.equalTo(before.without(modifiedContact).withAdded(contact)));
       }

   }
