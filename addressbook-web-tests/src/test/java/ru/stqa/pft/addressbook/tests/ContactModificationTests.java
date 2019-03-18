package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase{
   @Test
    public void testContactModification(){
        app.getNavigationHelper().gotoHomepage();
       if (! app.getContactHelper().isThereAContact()){
           app.getContactHelper().createContact(new ContactData("Viktor", "Kovalenko", "+380993020583", "vsutogan@gmail.com", "test1"));
           app.getContactHelper().returnToHomePage(); //if app.timeouts().implicitlyWait is 0
       }
        app.getContactHelper().initContactModification();
        app.getContactHelper().fillContactForm(new ContactData("Viktor", null, null, "vsutogan@gmail.com", null), false);
        app.getContactHelper().submitContactModification();
    }
}
