package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.BeforeMethod;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

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
}


