package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase{
   @Test
    public void testContactModification(){
        app.getContactHelper().getNavigationHelper().gotoHomepage();
        app.getContactHelper().initContactModification();
        app.getContactHelper().fillContactForm(new ContactData("Viktor", "Kovalenko", "+380993020583", "vsutogan@gmail.com", null), false);
        app.getContactHelper().submitContactModification();
    }
}
