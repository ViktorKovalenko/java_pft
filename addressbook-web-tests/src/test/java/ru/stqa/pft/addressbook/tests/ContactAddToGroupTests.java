package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddToGroupTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().groups().size() == 0) {
            app.goTo().groupsPage();
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
        ContactData contact = app.db().contacts().iterator().next();
        int selectedContact = contact.getId();
        Groups groups = app.db().groups();
        GroupData group = groups.iterator().next();
        String selectedGroup = group.getName();
        Groups after = app.db().groups();
        GroupData contactForGroup = after.iterator().next();
        GroupData contactIngroup = contactForGroup.withName(selectedGroup);
        app.goTo().homepage();
        app.goTo().insideGroup(selectedGroup);

        if (app.contact().isThereAContact()) {
            app.contact().deleteAllContactsFromGroup(contact);
        }

        app.goTo().allContactsPage();
        app.contact().addToGroup(selectedContact, group.withName(selectedGroup));

        assertThat(contactIngroup.withName(selectedGroup).getContacts().stream().findFirst().get().getId(), equalTo(selectedContact));
    }

}