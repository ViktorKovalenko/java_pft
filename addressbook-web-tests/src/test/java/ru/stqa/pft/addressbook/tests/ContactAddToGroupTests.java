package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.File;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddToGroupTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test123").withFooter("test2").withHeader("test3"));
        }
        if (app.db().contacts().size() == 0) {
            Groups groups = app.db().groups();
            app.goTo().homepage();
            File photo = new File("src/test/resources/img.png");
            app.contact().create(new ContactData()
                    .withName("Viktor").withSurname("Kovalenko").withEmail("test@mail.com").withPhoto(photo).ingroup(groups.iterator().next()));
        }



    }


        @Test
        public void testContactAddToGroup() {
            ContactData contact  = app.db().contacts().iterator().next();
            Groups groups = app.db().groups();
            Contacts contacts = app.db().contacts();


            app.goTo().homepage();
            app.goTo().insideGroup(groups.iterator().next().getName());
            if (app.contact().isThereAContact()) {
                ContactData before  = app.db().contacts().iterator().next();
                app.contact().deleteContactFromGroup(contact);
                ContactData after = app.db().contacts().iterator().next();
                assertThat(after.getGroups().size(), equalTo(before.getGroups().size() - 1));
            }
            app.goTo().allContactsPage();
            GroupData group = groups.iterator().next();
            app.contact().addToGroup(contact, group);

            assertThat(groups.withAdded(group), equalTo(contacts.withAdded(contact)));
        }

}
