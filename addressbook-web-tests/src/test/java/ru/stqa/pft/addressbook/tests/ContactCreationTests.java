package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactCreationTests extends TestBase {


    @Test
    public void testContactCreation() throws Exception {
        app.goTo().homepage();
        Contacts before = app.contact().all();
        app.contact().initContactCreation();
        ContactData contact = new ContactData()
                .withName("Viktor").withSurname("Kovalenko").withPhone("+380993020583").withEmail("vsutogan@gmail.com").withGroup("test1");
        app.contact().fillContactForm(contact, true);
        app.contact().submitContactCreation();
        app.contact().returnToHomePage();
        Contacts after = app.contact().all();
        assertThat(after.size(), equalTo(before.size() + 1));

        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    }


}
