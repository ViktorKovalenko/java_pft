package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase{


    @BeforeMethod
    public void ensurePreconditions() {
        if (app.contact().list().size() == 0) {
            app.contact().create(new ContactData()
                    .withName("Viktor").withSurname("Kovalenko").withPhone("+380993020583").withEmail("vsutogan@gmail.com").withGroup("test1"));
            app.contact().returnToHomePage(); //if app.timeouts().implicitlyWait is 0
        }
    }
       @Test
       public void testContactModification(){
           app.goTo().homepage();
           List<ContactData> before = app.contact().list();
           int index = before.size() -1;
           ContactData contact = new ContactData()
                   .withId(before.get(index).getId()).withName("Viktor").withSurname("Kovalenko").withPhone("+380993020583").withEmail("vsutogan@gmail.com").withGroup("test1");
           app.contact().modify(index, contact);
           List<ContactData> after = app.contact().list();
           Assert.assertEquals(after.size(), before.size());

           before.remove(index);
           before.add(contact);
           Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
           before.sort(byId);
           after.sort(byId);
           Assert.assertEquals(before, after);
       }

   }
