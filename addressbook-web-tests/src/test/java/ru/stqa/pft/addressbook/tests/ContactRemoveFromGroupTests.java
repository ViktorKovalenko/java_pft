package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import java.io.File;

public class ContactRemoveFromGroupTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().groups().size() == 0) {
            app.goTo().groupsPage();
            app.group().create(new GroupData().withName("test123"));
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
    public void testContactRemoveFromGroup() {
        ContactData contact = app.db().contacts().iterator().next();
        int selectedContact = contact.getId();
        Groups groups = app.db().groups();
        GroupData group = groups.iterator().next();
        String selectedGroup = group.getName();
        app.goTo().homepage();
        app.goTo().insideGroup(selectedGroup);
        if (!app.contact().isThereAContactByid(selectedContact)) {
            app.goTo().allContactsPage();
            app.contact().addToGroup(selectedContact, group.withName(selectedGroup));




        }
        Groups beforedel = app.db().groupsRefreshed();
        GroupData before = beforedel.iterator().next();
        app.group().groupPage();
        app.contact().deleteContactFromGroup(selectedContact);
        Groups afterdel = app.db().groups();
        GroupData after = afterdel.iterator().next() ;
        assertThat(after.getContacts(), equalTo(before.getContacts().without(contact.withId(selectedContact))));

    }

}




