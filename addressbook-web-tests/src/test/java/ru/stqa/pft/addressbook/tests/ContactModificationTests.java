package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase{
   @Test
    public void testContactModification(){
        app.getNavigationHelper().gotoHomepage();
       int before = app.getContactHelper().getContactCount();
       if (! app.getContactHelper().isThereAContact()){
           app.getContactHelper().createContact(new ContactData("Viktor", "Kovalenko", "+380993020583", "vsutogan@gmail.com", "test1"));
           app.getContactHelper().returnToHomePage(); //if app.timeouts().implicitlyWait is 0
       }
        app.getContactHelper().initContactModification(before -1);
        app.getContactHelper().fillContactForm(new ContactData("Viktor", null, null, "vsutogan@gmail.com", null), false);
        app.getContactHelper().submitContactModification();
        app.getContactHelper().returnToHomePage();
       int after = app.getContactHelper().getContactCount();
       Assert.assertEquals(after, before);
    }
}
